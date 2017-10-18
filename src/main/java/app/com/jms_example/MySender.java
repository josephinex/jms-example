package app.com.jms_example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class MySender {
	public static void main(String[] args) throws NamingException, JMSException, IOException {

		// Create and start connection

		InitialContext ctx = new InitialContext();
		QueueConnectionFactory f = (QueueConnectionFactory) ctx.lookup("myQueueConnectionFactory");

		QueueConnection connection = f.createQueueConnection();
		connection.start();

		// Create queue session
		QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

		// Get the Queue object
		Queue queue = (Queue) ctx.lookup("myQueue");

		// Create QueueSenderObject
		QueueSender sender = session.createSender(queue);

		// Create TextMessage object
		TextMessage message = session.createTextMessage();

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		
		
		while (true) {
			System.out.println("Enter Msg, end to terminate:");
			String s = reader.readLine();
			
			if (s.equals("end")) {
				break;
			}
			
			message.setText(s);
			
			
			// send message
			sender.send(message);
			System.out.println("Message successfully sent.");
			
			 connection.close();  
		}
		

	}
}
