import java.util.*;
import java.io.*;
public class Network implements Runnable
{
	LinkedList<NodeTrueGraph> observers;
	LinkedList<Message> messages;

	File output;
	FileWriter fout;

	public Network()throws IOException
	{
		this.observers = new LinkedList<NodeTrueGraph>();
		this.messages = new LinkedList<Message>();
		output =new File("/afs/inf.ed.ac.uk/user/s14/s1443575/DS/DS/log","output.txt");
		fout =new FileWriter(output);
	}

	public void addObserver(NodeTrueGraph NodeTrueGraph)
	{
		observers.add(NodeTrueGraph);
	}

	//Start the network
	public void start()
	{
		Thread newThread= new Thread(this);
		newThread.start();

		for(NodeTrueGraph node: observers)
		{
			newThread= new Thread(node);
			newThread.start();
		}
	}


	public void run() {

		//System.out.println("Network Running");
		Thread link;
		Message message;

		while(!NodeTrueGraph.timeToTerminate())
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

	public synchronized void write(NodeTrueGraph NodeTrueGraph)throws IOException
	{
		fout.write(NodeTrueGraph.id+"\n");
		fout.flush();
	}

}
