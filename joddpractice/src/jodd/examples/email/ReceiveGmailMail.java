// Copyright (c) 2003-2012, Jodd Team (jodd.org). All Rights Reserved.

package jodd.examples.email;

import jodd.mail.EmailMessage;
import jodd.mail.Pop3Server;
import jodd.mail.Pop3SslServer;
import jodd.mail.ReceiveMailSession;
import jodd.mail.ReceivedEmail;

import java.util.LinkedList;

/**
 * Receive GMail messages using POP3 SSL.
 */
public class ReceiveGmailMail {

	public static void main(String[] args) {
		Pop3Server popServer = new Pop3SslServer("pop.gmail.com", "remadagain", "");
		ReceiveMailSession session = popServer.createSession();
		session.open();
		System.out.println(session.getMessageCount());
		ReceivedEmail[] emails = session.receiveEmail(false);
		if (emails != null) {
			for (ReceivedEmail email : emails) {
				System.out.println("\n\n===[" + email.getMessageNumber() + "]==================================");
				System.out.println(email.getFrom());
				System.out.println(email.getTo()[0]);
				System.out.println(email.getSubject());
				System.out.println(email.getSentDate());
				System.out.println(email.getReceiveDate());
				LinkedList<EmailMessage> messages = email.getAllMessages();
				for (EmailMessage msg : messages) {
					System.out.println("------------------------------------");
					System.out.println(msg.getEncoding());
					System.out.println(msg.getMimeType());
					System.out.println(msg.getContent());
				}
			}
		}
		session.close();

	}
}
