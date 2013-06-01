// Copyright (c) 2003-2012, Jodd Team (jodd.org). All Rights Reserved.

package jodd.examples.email;

import jodd.io.FileUtil;
import jodd.mail.Email;
import jodd.mail.EmailAttachment;
import jodd.mail.SendMailSession;
import jodd.mail.SmtpServer;
import jodd.mail.att.InputStreamAttachment;

import java.io.FileInputStream;
import java.io.IOException;

import static jodd.mail.Email.PRIORITY_HIGHEST;

/**
 * Send message using SMTP.
 */
public class SendMail {

	public static void main(String[] args) throws IOException {
		SmtpServer smtpServer = new SmtpServer("mail.beotel.rs", "weird", "...");
		SendMailSession session = smtpServer.createSession();

		session.open();

		Email email;

		email = Email.create()
				.from("weird@beotel.rs")
				.to("info@jodd.org")
				.subject("test1")
				.addText("a plain text message čtf");
		session.sendMail(email);
		System.out.println("email #1 sent");

		email = Email.create()
				.from("weird@beotel.rs")
				.to("info@jodd.org")
				.subject("test2")
				.addHtml("a <b>test 2</b> message");
		session.sendMail(email);
		System.out.println("email #2 sent");

		email = Email.create()
				.from("weird@beotel.rs")
				.to("info@jodd.org")
				.addText("and text3 message!")
				.subject("test3")
				.addHtml("a <b>test 3</b> message");
		session.sendMail(email);
		System.out.println("email #3 sent");

		email = Email.create()
				.from("weird@beotel.rs")
				.to("info@jodd.org")
				.subject("test4")
				.addText("text 4")
				.attach(EmailAttachment.attachment().file("d:\\huh2.jpg"))
				.priority(PRIORITY_HIGHEST);
		session.sendMail(email);
		System.out.println("email #4 sent");


		byte[] bytes = FileUtil.readBytes("d:\\summer.jpg");

		FileInputStream fis = new FileInputStream("d:\\summer.jpg");
		InputStreamAttachment isa = new InputStreamAttachment(fis, "image/jpeg", "summer2.jpg", null);

		email = Email.create()
				.from("weird@beotel.rs")
				.to("info@jodd.org")
				.subject("test5")
				.addText("Здраво!")
				.addHtml("<html><META http-equiv=Content-Type content=\"text/html; charset=utf-8\"><body><h1>Здраво!</h1><img src='cid:huh2.jpg'></body></html>")
				.embed(EmailAttachment.attachment().file("d:\\huh2.jpg").setInline(true))
				.attach(EmailAttachment.attachment().file("d:\\cover.jpg"))
				.attach(EmailAttachment.attachment().bytes(bytes).setContentType("image/jpeg").setName("sum.jpg"))
				.attach(isa)
		;
		session.sendMail(email);
		System.out.println("email #5 sent");

		session.close();

		System.out.println("done.");
	}
}
