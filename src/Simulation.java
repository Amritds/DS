import java.io.*;
import java.util.*;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class Simulation
{
	public static void main(String[] args)throws IOException
	{
		//Visualization code.
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		Graph visualGraph = new SingleGraph("test");
		visualGraph.addAttribute("ui.antialias");
		visualGraph.addAttribute("stylesheet", "graph {padding : 50px;}"
						+ "node {size: 100px; fill-mode: plain;}"
						+ "node.red {fill-color: red;}"
						+ "node.green {fill-color: green;}");

		//Parse arguments
		String question = args[0];
		int startId =Integer.parseInt(args[1]);

		//Create network object.
		Network net = new Network();

		//Parse Graph, creating all NodeTrueGraphs of the graph, with access to the network.
		HashMap<Integer,NodeTrueGraph> graph = Parser.parse(net,startId,visualGraph);

		//Set question type for correct run.
		NodeTrueGraph.question = question;

		//Set variables for terimination condition.
		NodeTrueGraph.numberOfNodeTrueGraphs = Parser.numberOfNodeTrueGraphs;
		NodeTrueGraph.countNodeTrueGraphs_receivedRumor =0;

		//Visualize if Q2
		if(question.equalsIgnoreCase("q2")){
			visualGraph.display();
		}

		//Start the network and all NodeTrueGraphs in the graph.
		net.start();

	}

}
