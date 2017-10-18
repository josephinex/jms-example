package app.com.jms_example;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;

public class MyReceiver {

	public static void main(String[] args) throws NamingException, JMSException, InterruptedException {
		
		// Create and start connection  
		InitialContext context = new InitialContext();
		QueueConnectionFactory factory = (QueueConnectionFactory)context.lookup("myQueueConnectionFactory");
		
		QueueConnection connection = factory.createQueueConnection();
		
		connection.start();
		
		//Create Queue session
		QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		
		//Get the queue object
		Queue queue = (Queue) context.lookup("myQueue");
		
		//Create QueueReceiver 
		QueueReceiver receiver = session.createReceiver(queue);
		
		//Create listener object
		MyListener listener = new MyListener();
		
		//Register the listener object with receiver  
		receiver.setMessageListener(listener);
		
		 System.out.println("Receiver1 is ready, waiting for messages...");  
         System.out.println("press Ctrl+c to shutdown...");  
         
         while(true){                  
             Thread.sleep(1000);  
         }  

	}

}
