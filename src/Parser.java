import java.io.*;
import java.util.HashMap;
public class Parser {
	
	public static HashMap<Integer,Node> parse(Network net)throws IOException
	{
		//IO
		File file = new File("graph.txt");
		FileReader fin = new FileReader(file);
		BufferedReader br = new BufferedReader(fin);
		String line;
		String[] splitLine;
		String[] neighbors;
		
		//Simulator
		HashMap <Integer,Node> graph= new HashMap<Integer,Node>();
		
		while((line = br.readLine()) != null)
		{
			splitLine = line.split(":");
			int id= Integer.parseInt(splitLine[0]);
			
			Node root;
			
			if(graph.containsKey(id)){
				root = graph.get(id);
			}
			else
			{
				graph.put(id, new Node(net,id));
				root =graph.get(id);
			}
			
			neighbors = splitLine[1].split(",");
			for(String neighborID : neighbors)
			{
				Node neighbor;
				int Nid = Integer.parseInt(neighborID);
				
				if(graph.containsKey(Nid)){
					neighbor = graph.get(Nid);
				}
				else
				{
					graph.put(Nid, new Node(net,Nid));
					neighbor= graph.get(Nid);
				}
				
				//Add to list of neighbors
				root.neighbors.add(neighbor);
				
			}
			
		}
		
		br.close();

		return graph;
	}
}
