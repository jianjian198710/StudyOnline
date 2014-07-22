package javamail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailTest{
    // 邮件发送协议 
    private final static String PROTOCOL = "smtp"; 
     
    // SMTP邮件服务器 
    private final static String HOST = "smtp.163.com"; 
     
    // SMTP邮件服务器默认端口 
    private final static String PORT = "25"; 
     
    // 是否要求身份认证 
    private final static String IS_AUTH = "true"; 
     
    // 是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息） 
    private final static String IS_ENABLED_DEBUG_MOD = "true"; 
     
    // 发件人 
    private static final String from = "jianjian198710@163.com"; 
 
    // 收件人 
    private static final String to = "51488384@qq.com"; 
     
    // 初始化连接邮件服务器的会话信息 
    private static Properties props = null; 
    
    static { 
        props = new Properties(); 
        props.setProperty("mail.transport.protocol", PROTOCOL); 
        props.setProperty("mail.smtp.host", HOST); 
        props.setProperty("mail.smtp.port", PORT); 
        props.setProperty("mail.smtp.auth", IS_AUTH); 
        props.setProperty("mail.debug",IS_ENABLED_DEBUG_MOD); 
        //Proxy
//        props.setProperty("proxySet", "true");
//        props.setProperty("http.proxyHost", "10.10.0.96");   
//        props.setProperty("http.proxyPort", "8080");  
    }
    
    public void SendTextMail() throws MessagingException{
    	Session session = Session.getDefaultInstance(props); 
    	Message msg = new MimeMessage(session);
    	msg.setSubject("Hello JavaMail!");
    	msg.setText("This is test email!");
    	msg.setSentDate(new Date());
    	msg.setFrom(new InternetAddress(from));
    	Transport transport = session.getTransport();
    	transport.connect("jianjian198710@163.com", "198710");
    	transport.sendMessage(msg, new Address[] {new InternetAddress(to)});
    	transport.close();
    }
     
	public static void main(String[] args) throws MessagingException{
		new SendMailTest().SendTextMail();
	}
}
