import java.io.*;
import java.util.*;
public class Simulation 
{
	public static void main(String[] args)throws IOException
	{
		//Create network object.
		Network net = new Network();
		
		//Parse Graph, creating all nodes of the graph, with access to the network.
		HashMap<Integer,Node> graph = Parser.parse(net);
		
		//Start the network and all nodes in the graph.
		net.start();
		
	}
	
}
