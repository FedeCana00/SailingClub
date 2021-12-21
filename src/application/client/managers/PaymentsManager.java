/**
 * 
 */
package application.client.managers;

import java.util.ArrayList;
import java.util.List;

import application.models.MembershipFee;
import application.models.StorageFee;
import application.models.SubscriptionFee;
import application.models.ToPay;

/**
 * @author Federico Canali
 *
 */
public class PaymentsManager {
	/* VARIABLES */
	private List<ToPay> toPays;
	private List<MembershipFee> membershipFees;
	private List<StorageFee> storageFees;
	private List<SubscriptionFee> subscriptionFees;
	
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
	   toPays = new ArrayList<ToPay>();
	   membershipFees = new ArrayList<MembershipFee>();
	   storageFees = new ArrayList<StorageFee>();
	   subscriptionFees = new ArrayList<SubscriptionFee>();
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
   
   /***
    * 
    */
   public void logout() {
	   toPays.clear();
	   membershipFees.clear();
	   storageFees.clear();
	   subscriptionFees.clear();
   }
   
   /**
    * Used to refresh toPay and membership fee list. 
    */
   public void refreshAfterPaidMembershipFee() {
	   setToPays();
	   setMembershipFees();
   }
   
   /**
    * Used to refresh subscription fee list. 
    */
   public void refreshAfterPaidSubscriptionFee() {
	   setSubscriptionFees();
   }
   
   /**
    * Used to refresh toPay and storage fee list. 
    */
   public void refreshAfterPaidStorageFee() {
	   setToPays();
	   setStorageFees();
   }

	/**
	 * @return the toPays
	 */
	public List<ToPay> getToPays() {
		if(toPays.isEmpty())
			setToPays();
		
		return toPays;
	}
	
	/**
	 * Used to set to pay list with data from server.
	 */
	public void setToPays() {
		this.toPays = ClientManager.getInstance().getToPaysOfPartner(UserManager.getInstance().getPerson().getId());
	}
	
	/**
	 * @return the membershipFees
	 */
	public List<MembershipFee> getMembershipFees() {
		if(membershipFees.isEmpty())
			setMembershipFees();
		
		return membershipFees;
	}
	
	/**
	 * Used to set membership fee list with data from server.
	 */
	public void setMembershipFees() {
		this.membershipFees = ClientManager.getInstance().getMembershipFees(UserManager.getInstance().getPerson().getId());
	}
	
	/**
	 * @return the storageFees
	 */
	public List<StorageFee> getStorageFees() {
		if(storageFees.isEmpty())
			setStorageFees();
		
		return storageFees;
	}
	
	/**
	 * Used to set storage fee list with data from server.
	 */
	public void setStorageFees() {
		this.storageFees = ClientManager.getInstance().getStorageFees(UserManager.getInstance().getPerson().getId());
	}
	
	/**
	 * @return the subscriptionFees
	 */
	public List<SubscriptionFee> getSubscriptionFees() {
		if(subscriptionFees.isEmpty())
			setSubscriptionFees();
		
		return subscriptionFees;
	}
	
	/**
	 * Used to set subscription fee list with data from server.
	 */
	public void setSubscriptionFees() {
		this.subscriptionFees = ClientManager.getInstance().getSubscriptionFees(UserManager.getInstance().getPerson().getId());
	}
}
