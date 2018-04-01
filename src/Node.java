import java.util.*;

public class Node implements Runnable
{
	int id;
	Network network;
	LinkedList<Node> neighbors;
	LinkedList <Message> newMessage;
	
	public Node(Network network,int id)
	{
		this.network = network;
		this.id =id;
		this.neighbors = new LinkedList<Node>();
		
		network.addObserver(this);
		newMessage =new LinkedList<Message>();

	}
	
	
	public void run() 
	{
		System.out.println("Node "+id+" Running");
		for(Node node : neighbors){
			sendMessage("Broadcast",node);
			System.out.println("Sent message "+this.id+"->"+node.id);
		}
		
		while(true)
		{
			Message message = this.readNewMessage();
			if(message!=null){
				System.out.println("ReceivedBroadcast "+message.sender.id+"->"+id);
			}
				
		}
		
	}

	private synchronized void sendMessage(String message,Node reciever)
	{
		network.addMessages(new Message(message,this,reciever));
	}
	
	public synchronized void receive(Message message)
	{
		newMessage.add(message);
	}
	
	public synchronized Message readNewMessage()
	{
		if(newMessage.size()>0){
			return newMessage.removeFirst();
		}
		
		return null;
	}
}
