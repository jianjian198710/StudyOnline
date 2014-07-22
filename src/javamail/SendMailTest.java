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
    // �ʼ�����Э�� 
    private final static String PROTOCOL = "smtp"; 
     
    // SMTP�ʼ������� 
    private final static String HOST = "smtp.163.com"; 
     
    // SMTP�ʼ�������Ĭ�϶˿� 
    private final static String PORT = "25"; 
     
    // �Ƿ�Ҫ�������֤ 
    private final static String IS_AUTH = "true"; 
     
    // �Ƿ����õ���ģʽ�����õ���ģʽ�ɴ�ӡ�ͻ������������������ʱһ��һ�����Ӧ��Ϣ�� 
    private final static String IS_ENABLED_DEBUG_MOD = "true"; 
     
    // ������ 
    private static final String from = "jianjian198710@163.com"; 
 
    // �ռ��� 
    private static final String to = "51488384@qq.com"; 
     
    // ��ʼ�������ʼ��������ĻỰ��Ϣ 
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
