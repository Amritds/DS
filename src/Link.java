
public class Link implements Runnable
{
	Message message;
	
	public Link(Message message)
	{
		this.message = message;
	}
	public void run() {
		//Delay in transmission
		
		
		message.receiver.receive(message);
	}
	
}
