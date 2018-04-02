import java.io.*;
import java.util.HashMap;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class Parser {

	static int startId;
	static Network net;
	static int numberOfNodeTrueGraphs=0;
	static Graph visualGraph;

	public static HashMap<Integer,NodeTrueGraph> parse(Network network, int start, Graph Vgraph)throws IOException
	{
		//IO
		File file = new File("graph.txt");
		FileReader fin = new FileReader(file);
		BufferedReader br = new BufferedReader(fin);
		String line;
		String[] splitLine;
		String[] neighbors;
		startId =start;
		net = network;
		visualGraph = Vgraph;

		//Simulator
		HashMap <Integer,NodeTrueGraph> graph= new HashMap<Integer,NodeTrueGraph>();

		while((line = br.readLine()) != null)
		{
			splitLine = line.split(":");
			int id= Integer.parseInt(splitLine[0]);

			NodeTrueGraph root;

			if(graph.containsKey(id)){
				root = graph.get(id);
			}
			else
			{
				graph.put(id, createNodeTrueGraph(id));
				root =graph.get(id);
			}

			neighbors = splitLine[1].split(",");
			for(String neighborID : neighbors)
			{
				NodeTrueGraph neighbor;
				int Nid = Integer.parseInt(neighborID);

				if(graph.containsKey(Nid)){
					neighbor = graph.get(Nid);
				}
				else
				{
					graph.put(Nid, createNodeTrueGraph(Nid));
					neighbor= graph.get(Nid);
				}

				//Add to list of neighbors
				root.addNeighbors(neighbor);

			}

		}

		br.close();

		return graph;
	}


	public synchronized static NodeTrueGraph createNodeTrueGraph(int id){
		numberOfNodeTrueGraphs++;
		Node a = visualGraph.addNode(String.valueOf(id));

		if(startId==id){
			return new NodeTrueGraph(net,id,true,a,visualGraph);
		}
		return new NodeTrueGraph(net,id,false,a,visualGraph);
	}
}
