package app.com.jms_example;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyListener implements MessageListener {

	public void onMessage(Message message) {
		TextMessage msg = (TextMessage) message;
		try {
			System.out.println("following message is received:" + msg.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
