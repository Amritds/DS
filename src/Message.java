
public class Message 
{
	String str;
	Node sender, receiver;
	public Message(String str,Node sender,Node receiver)
	{
		this.str = str;
		this.sender = sender;
		this.receiver= receiver;
	}
	
}