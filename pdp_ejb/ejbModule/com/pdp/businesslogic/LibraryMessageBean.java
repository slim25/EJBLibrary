package com.pdp.businesslogic;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.ejb.MessageDriven;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import com.pdp.business.LibraryPersistentBeanRemote;
import com.pdp.model.Book;

@MessageDriven(name = "BookMessageHandler", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/BookQueue") })
public class LibraryMessageBean implements MessageListener {

	@Resource
	private MessageDrivenContext mdctx;

	@EJB
	LibraryPersistentBeanRemote libraryBean;

	public LibraryMessageBean() {
	}

	public void onMessage(Message messageBookName) {
		TextMessage objectMessage = null;
		try {
			objectMessage = (TextMessage) messageBookName;
			String bookName = (String) objectMessage.getText();
			libraryBean.addBook(new Book(bookName));

		} catch (JMSException ex) {
			mdctx.setRollbackOnly();
		}
	}
}
