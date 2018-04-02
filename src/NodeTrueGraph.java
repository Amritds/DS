import java.util.*;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class NodeTrueGraph implements Runnable
{
	int id;
	Network network;
	Graph visualGraph;
	Node selfVisual;

	LinkedList<NodeTrueGraph> neighbors;
	LinkedList <Message> newMessage;
	boolean haveMessage;
	static int countNodeTrueGraphs_receivedRumor;
	static int numberOfNodeTrueGraphs;
	static String question;

	public NodeTrueGraph(Network network,int id, boolean haveMessage,Node selfVisual,Graph visualGraph)
	{

		this.network = network;
		this.id =id;
		this.neighbors = new LinkedList<NodeTrueGraph>();
		this.haveMessage=haveMessage;
		this.selfVisual = selfVisual;
		this.visualGraph = visualGraph;

		network.addObserver(this);
		newMessage =new LinkedList<Message>();
		//Print if this NodeTrueGraph starts with the message.
		if(haveMessage){
			selfVisual.addAttribute("ui.class", "green");
			try {
				network.write(this);
			}
			catch(Exception e) {
				System.out.println("IO error");
			}
		}
		else{
			selfVisual.addAttribute("ui.class", "red");
		}
	}

  // Run the NodeTrueGraph.
	public void run(){
		//if(question.equalsIgnoreCase("q1")){
			//	runQ1();
		//}

		runQ1();

	}

	public void runQ1()
	{
		//System.out.println("NodeTrueGraph "+id+" Running");
		//Receive rumor
		while(!haveMessage)
		{
			Message message = this.readNewMessage();
			if(message!=null){
			  //System.out.println("ReceivedBroadcast "+message.sender.id+"->"+id);
				haveMessage =true;
				try {
					network.write(this);
				}
				catch(Exception e) {
					System.out.println("IO error");
				}
			}

		}

		//NodeTrueGraph no longer trying to receive rumor.
		selfVisual.setAttribute("ui.class", "green");
		updateCount();

		Random rand = new Random();
		int choice, randomDelay, max=1100, min =900;
		while(!timeToTerminate()){

			//Select random neighbor and push the rumor.
			choice =rand.nextInt(neighbors.size());
			sendMessage("Rumor",neighbors.get(choice));
			//System.out.println("Sent message "+this.id+"->"+neighbors.get(choice).id);


			//Delay before attempting next transmission
			randomDelay = min + rand.nextInt(max -min +1);
			try {
				Thread.sleep(randomDelay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private synchronized void sendMessage(String message,NodeTrueGraph reciever)
	{
		network.addMessages(new Message(message,this,reciever));
	}

	public synchronized void receive(Message message)
	{
		//Avoid message buildup
		if(!haveMessage){
				newMessage.add(message);
		}
	}

	public synchronized Message readNewMessage()
	{
		if(newMessage.size()>0){
			return newMessage.removeFirst();
		}

		return null;
	}

	public synchronized static boolean timeToTerminate(){
		if(countNodeTrueGraphs_receivedRumor==numberOfNodeTrueGraphs){
			return true;
		}
		return false;
	}

	private synchronized static void updateCount(){
		countNodeTrueGraphs_receivedRumor++;
	}
	private synchronized static void print(){
		System.out.println(countNodeTrueGraphs_receivedRumor+" "+numberOfNodeTrueGraphs);
	}

	public synchronized void addNeighbors(NodeTrueGraph node){
		visualGraph.addEdge(String.valueOf(this.id)+String.valueOf(node.id), String.valueOf(this.id), String.valueOf(node.id),true);
		this.neighbors.add(node);
	}

}
