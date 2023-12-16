package vn.iotstar.utils;

import java.util.Properties;
import java.util.Random;

import javax.mail.internet.*;
import javax.mail.*;
import vn.iotstar.Entity.Users;

public class Email {
	
	public String getRandom() {
		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		return String.format("%06d", number);
	}

    public boolean sendEmail(Users user) {
        boolean test = false;
        
        String toEmail = user.getEmail();
        String fromEmail = "hoangphuc0417@gmail.com"; // Thay bằng email của bạn
        String password = "zvdt kvnc vwco wshl"; // Sử dụng mật khẩu ứng dụng đã tạo
        
        try {
            Properties pr = configEmail(new Properties());
            
            Session session = Session.getInstance(pr, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });
            
            Message mess = new MimeMessage(session);
            mess.setHeader("Content-Type", "text/plain; charset=UTF-8");
            mess.setFrom(new InternetAddress(fromEmail));
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            mess.setSubject("Confirm Code");
            mess.setText("Your code is: " + user.getCode());
            
            Transport.send(mess);
            test = true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }

    public Properties configEmail(Properties pr) {
        pr.setProperty("mail.smtp.host", "smtp.gmail.com");
        pr.setProperty("mail.smtp.port", "587");
        pr.setProperty("mail.smtp.auth", "true");
        pr.setProperty("mail.smtp.starttls.enable", "true");
        pr.put("mail.smtp.socketFactory.port", "587");
        pr.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
        return pr;
    }
}
