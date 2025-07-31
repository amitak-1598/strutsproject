package com.project.strutsamit.common.dao;

import java.io.File;

//import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.project.model.HelpTicket;
import com.project.model.TicketComment;
import com.project.strutsamit.common.Account;
import com.project.strutsamit.common.AccountCurrency;
import com.project.strutsamit.common.ChargingDetails;
import com.project.strutsamit.common.Mop;
import com.project.strutsamit.common.MopTransaction;
import com.project.strutsamit.common.Payment;
import com.project.strutsamit.common.Permissions;
import com.project.strutsamit.common.Roles;
import com.project.strutsamit.common.User;
import com.project.strutsamit.util.Constants;
import com.project.strutsamit.util.PropertiesManager;


/**
 * @author neeraj
 *
 */
public class HibernateSessionProvider {
	
//	private static Logger logger = Logger.getLogger(HibernateSessionProvider.class
//			.getName());
	private SessionFactory factory;

	private static final String hbmddlAutoSettingName = "hibernate.hbm2ddl.auto";
	private static final String hbmddlAutoSetting = "update";

	private static class SessionHelper {
		private static final HibernateSessionProvider provider = new HibernateSessionProvider();
	}
	private HibernateSessionProvider() {

	    // configures settings from hibernate.cfg.xml
        File xmlConfigFile = new File(new PropertiesManager().getSystemProperty(
                Constants.HIBERNATE_CONFIG_FILE.getValue()));
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure(xmlConfigFile).applySetting(hbmddlAutoSettingName, hbmddlAutoSetting)
                .build();
	try {
			factory = new MetadataSources(registry).addAnnotatedClass(User.class)
												//   .addAnnotatedClass(UserRecords.class)
												   .addAnnotatedClass(Roles.class)
												   .addAnnotatedClass(Permissions.class)
											//	   .addAnnotatedClass(LoginHistory.class)
												   .addAnnotatedClass(Account.class)
												   .addAnnotatedClass(Payment.class)
												   .addAnnotatedClass(Mop.class)
												   .addAnnotatedClass(MopTransaction.class)
												   .addAnnotatedClass(ChargingDetails.class)
										//		   .addAnnotatedClass(MerchantComments.class)
										//		   .addAnnotatedClass(Invoice.class)
										//		   .addAnnotatedClass(Remittance.class)
										//		   .addAnnotatedClass(DynamicPaymentPage.class)
									//			   .addAnnotatedClass(Token.class)
												   .addAnnotatedClass(AccountCurrency.class)
								//				   .addAnnotatedClass(Settlement.class)
								//				   .addAnnotatedClass(CitrusPaySubscription.class)
								//				   .addAnnotatedClass(FraudPrevention.class)
								//				   .addAnnotatedClass(Chargeback.class)
												   .addAnnotatedClass(HelpTicket.class)
												   .addAnnotatedClass(TicketComment.class)
								//				   .addAnnotatedClass(ChargebackComment.class)
								//				   .addAnnotatedClass(SurchargeDetails.class)
								//				   .addAnnotatedClass(Surcharge.class)
								//				   .addAnnotatedClass(ServiceTax.class)
								//				   .addAnnotatedClass(PendingUserApproval.class)
											//	   .addAnnotatedClass(NotificationDetail.class)
								//				   .addAnnotatedClass(BinRange.class)
								//				   .addAnnotatedClass(PendingResellerMappingApproval.class)
								//				   .addAnnotatedClass(NotificationEmailer.class)
								//				   .addAnnotatedClass(NotificationDetail.class )
								//				   .addAnnotatedClass(PendingMappingRequest.class)
								//				   .addAnnotatedClass(RouterRule.class)
								//				   .addAnnotatedClass(WhitelableBranding.class)
								//				   .addAnnotatedClass(PayoutBalanceSheet.class)
												   .buildMetadata().buildSessionFactory();

		} catch (Exception exception) {
		//	logger.error("Error creating hibernate session" + exception);
			StandardServiceRegistryBuilder.destroy(registry);
			throw exception;
		}
	}

	private SessionFactory getFactory() {
		return factory;
	}

	public static SessionFactory getSessionFactory() {
		return SessionHelper.provider.getFactory();
	}

	public static Session getSession() {
		return getSessionFactory().openSession();
	}

	public static void closeSession(Session session) {
		if(null != session && session.isOpen()) {
			session.close();
			session = null;
		}
	}
}
