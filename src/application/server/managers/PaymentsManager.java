package application.server.managers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.classes.Price;
import application.models.MembershipFee;
import application.models.Notification;
import application.models.Payment;
import application.models.StorageFee;
import application.models.SubscriptionFee;
import application.models.ToPay;

/**
 * This class deals with the management of the partners' payments.
 * 
 * @see application.models.Payment
 * @see application.models.MembershipFee
 * @see application.models.SubscriptionFee
 * @see application.models.StorageFee
 * 
 * @author Federico Canali
 *
 */
public class PaymentsManager {
	/* VARIABLES */
	private List<SubscriptionFee> subscriptionFees;
	private List<MembershipFee> membershipFees;
	private List<StorageFee> storageFees;
	private Map<ToPay, Notification> toPays;
	
	/* 
	 * The instance is static so it is shared among all instances of the class. It is also private
    * so it is accessible only within the class.
    */
   private static PaymentsManager instance = null;

   /*
    * The constructor is private so it is accessible only within the class.
    */
   private PaymentsManager() {
	   //init variables lists
	   subscriptionFees = new ArrayList<SubscriptionFee>();
	   membershipFees = new ArrayList<MembershipFee>();
	   storageFees = new ArrayList<StorageFee>();
	   toPays = new HashMap<ToPay, Notification>();
   }

   /**
    * The constructor is called only if the static instance is null, so only the first time
    * that the getInstance() method is invoked.
    * All the other times the same instance object is returned.
    * @return the instance object is returned.
    **/
   public static PaymentsManager getInstance() {
       if (instance == null)
           instance = new PaymentsManager();
       return instance;
   }
   
   /**
    * Used to refresh all list of this manager.
    **/
   public void refresh() {
	   SqlManager.getInstance().getAllPayments();
	   checkMembershipPayments();
	   checkStoragePayments();
   }

	/**
	 * @return the subscriptionFees
	 */
	public List<SubscriptionFee> getSubscriptionFees() {
		return subscriptionFees;
	}

	/**
	 * @param subscriptionFees the subscriptionFees to set
	 */
	public void setSubscriptionFees(List<SubscriptionFee> subscriptionFees) {
		this.subscriptionFees = subscriptionFees;
	}
	
	/**
	 * @return the membershipFees
	 */
	public List<MembershipFee> getMembershipFees() {
		return membershipFees;
	}
	
	/**
	 * @param membershipFees the membershipFees to set
	 */
	public void setMembershipFees(List<MembershipFee> membershipFees) {
		this.membershipFees = membershipFees;
	}
	
	/**
	 * @return the storageFees
	 */
	public List<StorageFee> getStorageFees() {
		return storageFees;
	}
	
	/**
	 * @param storageFees the storageFees to set
	 */
	public void setStorageFees(List<StorageFee> storageFees) {
		this.storageFees = storageFees;
	}
	
	/**
	 * @return the toPays
	 */
	public Map<ToPay, Notification> getToPays() {
		return toPays;
	}

	/**
	 * @param toPays the toPays to set
	 */
	public void setToPays(Map<ToPay, Notification> toPays) {
		this.toPays = toPays;
	}

	/**
	 * Insert new payment in database.
	 * @param payment Represents the payment to insert.
	 **/
	public void insertPayment(Payment payment) {
		SqlManager.getInstance().insertPayment(payment);
	}
	
	/**
	 * Check if membership payments are all ok else create another one.
	 **/
	public void checkMembershipPayments() {
		try {
			PersonManager.getInstance().getAllPartners().forEach(partner -> {
				MembershipFee membershipFeeMostRecent = null;
				
				//search inside list and assign that value to variable
				for(MembershipFee m: membershipFees)
					if(m.getPartnerId() == partner.getId()) {
						if(membershipFeeMostRecent == null)
							membershipFeeMostRecent = m;
						//if is not the most recent, it'll replace it
						else if(membershipFeeMostRecent.getDate().before(m.getDate()))
							membershipFeeMostRecent = m;
					}
				
				if(membershipFeeMostRecent != null) {
					Calendar calendar = Calendar.getInstance();
					//set date to today - 1 year
					calendar.set(calendar.get(Calendar.YEAR) - 1, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
					//If the tax payment date is earlier then it has expired
					if(membershipFeeMostRecent.getDate().before(new Date(calendar.getTimeInMillis()))) {
						
						boolean exist = false;
						
						for (Map.Entry<ToPay, Notification> entry : toPays.entrySet()) {
							if(entry.getKey().getPartnerId() == partner.getId()) {
								exist = true;
								break;
							}
						}
						
						//if not exist create new ToPay
						if(!exist) {
							insertToPay(new ToPay(partner.getId(), Price.MEMBERSHIP_FEE, true));
						}
					}
				} else { //If it does not exist and not exist even the to pay associated , it creates request of payment
					
					boolean exist = false;
					
					for (Map.Entry<ToPay, Notification> entry : toPays.entrySet()) {
						if(entry.getKey().getPartnerId() == partner.getId()) {
							exist = true;
							break;
						}
					}
					
					//if not exist create new ToPay
					if(!exist) {
						insertToPay(new ToPay(partner.getId(), Price.MEMBERSHIP_FEE, true));
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	/**
	 * Check if storage payments are all ok else create another one.
	 **/
	public void checkStoragePayments() {
		try {
			BoatsManager.getInstance().getBoats().forEach(boat -> {
				StorageFee storageFeeMostRecent = null;
				
				//search inside list and assign that value to variable
				for(StorageFee s: storageFees)
					if(s.getBoatId() == boat.getId()) {
						if(storageFeeMostRecent == null)
							storageFeeMostRecent = s;
						//if is not the most recent, it'll replace it
						else if(storageFeeMostRecent.getDate().before(s.getDate()))
							storageFeeMostRecent = s;
					}
				
				if(storageFeeMostRecent != null) {
					Calendar calendar = Calendar.getInstance();
					//set date to today - 1 year
					calendar.set(calendar.get(Calendar.YEAR) - 1, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
					//If the tax payment date is earlier then it has expired
					if(storageFeeMostRecent.getDate().before(new Date(calendar.getTimeInMillis()))) {
						
						boolean exist = false;
						
						for (Map.Entry<ToPay, Notification> entry : toPays.entrySet()) {
							if(entry.getKey().getBoatId() == boat.getId()) {
								exist = true;
								break;
							}
						}
						
						//if not exist create new ToPay
						if(!exist) {
							insertToPay(new ToPay(boat.getPartnerId(), Price.STORAGE_METRE_FEE * boat.getLength(), false, boat.getId()));
						}
					}
				} else { //If it does not exist and not exist even the to pay associated , it creates request of payment
					
					boolean exist = false;
					
					for (Map.Entry<ToPay, Notification> entry : toPays.entrySet()) {
						if(entry.getKey().getBoatId() == boat.getId()) {
							exist = true;
							break;
						}
					}
					
					//if not exist create new ToPay
					if(!exist) {
						insertToPay(new ToPay(boat.getPartnerId(), Price.STORAGE_METRE_FEE * boat.getLength(), false, boat.getId()));
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}			
	}
	
	/* create and new toPay inside DB */
	private void insertToPay(ToPay toPay) {
		SqlManager.getInstance().insertToPay(toPay);
	}
	
	/**
	 * Send notification to partner.
	 * @param toPay notification associated to this param object.
	 */
	public void sendNotification(ToPay toPay) {
		Notification notification = new Notification();
		notification.setMessage(toPay.isMembershipFee() ? "You have to renew the membership fee. Price = " 
				+ toPay.getPrice() + "€" : "You have to pay for the storage of your boat. Price = " + toPay.getPrice() + "€");
		notification.setPartnerId(toPay.getPartnerId());
		notification.setToPayId(toPay.getId());
		
		SqlManager.getInstance().insertNotification(notification);
		
		refresh();
	}
	
	/**
	 * Get all toPay that a club staff can send to partner.
	 * @return the list of to pay.
	 */
	public List<ToPay> getAllToPayNotYetSent(){
		List<ToPay> result = new ArrayList<ToPay>();
		
		for (Map.Entry<ToPay, Notification> entry : toPays.entrySet()) {
			if(entry.getValue() == null)
				result.add(entry.getKey());
		}
		
		return result;
	}
	
	/**
	 * Get all toPay sent by the club staff to a partner.
	 * @param partnerId represents the partner id.
	 * @return list of to pay.
	 */
	public List<ToPay> getToPayOfPartner(int partnerId){
		List<ToPay> result = new ArrayList<ToPay>();
		
		for (Map.Entry<ToPay, Notification> entry : toPays.entrySet()) {
			if(entry.getValue() != null)
				if(entry.getKey().getPartnerId() == partnerId)
					result.add(entry.getKey());
		}
		
		return result;
	}
	
	/**
	 * Get all membership fees paid of partner.
	 * @param partnerId represents the partner id..
	 * @return list of to membership fees.
	 */
	public List<MembershipFee> getMembershipFeesOfPartner(int partnerId){
		List<MembershipFee> result = new ArrayList<MembershipFee>();
		
		membershipFees.forEach(m -> {
			if(m.getPartnerId() == partnerId)
				result.add(m);
		});
		
		return result;
	}
	
	/**
	 * Get all subscription fees paid of partner.
	 * @param partnerId represents the partner id..
	 * @return list of to subscription fees.
	 */
	public List<SubscriptionFee> getSubscriptionFees(int partnerId){
		List<SubscriptionFee> result = new ArrayList<SubscriptionFee>();
		
		subscriptionFees.forEach(s -> {
			if(s.getPartnerId() == partnerId)
				result.add(s);
		});
		
		return result;
	}
	
	/**
	 * Get all storage fees paid of partner.
	 * @param partnerId represents the partner id..
	 * @return list of to storage fees.
	 */
	public List<StorageFee> getStorageFees(int partnerId){
		List<StorageFee> result = new ArrayList<StorageFee>();
		
		storageFees.forEach(s -> {
			if(s.getPartnerId() == partnerId)
				result.add(s);
		});
		
		return result;
	}
	
	/**
	 * Get all notifications of a partner.
	 * @param partnerId represents the partner id.
	 * @return list of notifications.
	 */
	public List<Notification> getNotifications(int partnerId){
		List<Notification> result = new ArrayList<Notification>();
		
		for (Map.Entry<ToPay, Notification> entry : toPays.entrySet()) {
			if(entry.getKey().getPartnerId() == partnerId)
				if(entry.getValue() != null)
					result.add(entry.getValue());
		}
		
		return result;
	}
	
	/**
	 * Used to delete to pay in database.
	 * @param toPay is the to pay to delete.
	 */
	public void deleteToPay(ToPay toPay) {
		SqlManager.getInstance().deleteToPayInDB(toPay);
		refresh();
	}
}
