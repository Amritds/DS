
public class Message
{
	String str;
	NodeTrueGraph sender, receiver;
	public Message(String str,NodeTrueGraph sender,NodeTrueGraph receiver)
	{
		this.str = str;
		this.sender = sender;
		this.receiver= receiver;
	}

}
