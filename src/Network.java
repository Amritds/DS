import java.util.*;
public class Network implements Runnable 
{
	LinkedList<Node> observers; 
	LinkedList<Message> messages;
	
	public Network()
	{
		this.observers = new LinkedList<Node>();
		this.messages = new LinkedList<Message>();
	}
	
	public void addObserver(Node node)
	{
		observers.add(node);
	}
	
	//Start the network
	public void start()
	{
		Thread newThread= new Thread(this);
		newThread.start();
		
		for(Node node: observers)
		{
			newThread= new Thread(node);
			newThread.start();
		}
	}
	
	
	public void run() {
	
		System.out.println("Network Running");
		Thread link;
		Message message;
		
		while(true)
		{
			message = this.getMessage();
			if(message!=null){
			link =new Thread(new Link(message));
			link.start();
			}
		}

	}
	
	public synchronized void addMessages(Message message)
	{
		messages.add(message);
	}
	
	public synchronized Message getMessage()
	{
		if(messages.size()>0){
			return messages.removeFirst();
		}
		else{
			return null;
		}
	}
}