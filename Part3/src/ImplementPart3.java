import graph.Edge;
import graph.Graph;
import graph.Graph.Type;
import graph.Vertex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import algorithms.DAGDistance;
import algorithms.KruskalMST;

/**
 * @author Aneesh Garg
 */
public class ImplementPart3 {

	private static final String SEPARATOR = " ";

	public static void main(String[] args) throws Exception {

		int noOfVertices, noOfEdges;

		Graph graph = new Graph();
		Set<String> insertedVertices = new HashSet<String>();

		InputStream inputStream = ImplementPart3.class
				.getResourceAsStream("input.txt");

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputStream));

		String line = "";
		boolean first = true;
		long startTime = System.currentTimeMillis();
		try {
			while ((line = reader.readLine()) != null) {
				System.out.println("Reading: " + line);
				if (first) {
					String[] split = line.split(SEPARATOR);
					noOfVertices = Integer.parseInt(split[0]);
					noOfEdges = Integer.parseInt(split[1]);
					graph.setNoOfVertices(noOfVertices);
					graph.setNoOfEdges(noOfEdges);

					String type = split[2];
					if ("D".equals(type))
						graph.setGraphType(Graph.Type.DIRECTED);
					else
						graph.setGraphType(Graph.Type.UNDIRECTED);
					first = false;
				} else if (line.contains("Source")) {
					String split[] = line.split(SEPARATOR);
					String source = split[1]
							.substring(0, split[1].length() - 1);
					graph.setSource(graph.getVertex(source));
					System.out.println("Source: " + graph.getSource());
				} else {
					String[] split = line.split(SEPARATOR);
					String start = split[0];
					String end = split[1];
					int weight = Integer.parseInt(split[2]);

					if (!insertedVertices.contains(start)) {
						graph.insertVertex(start);
						insertedVertices.add(start);
					}
					if (!insertedVertices.contains(end)) {
						graph.insertVertex(end);
						insertedVertices.add(end);
					}
					graph.insertEdge(start, end, weight);
				}

			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		long graphCreationTime = System.currentTimeMillis();
		graph.printGraph();

		/**
		 * If the graph id Undirected graph then calculating MST using kruskal's
		 * algorithm.<br/>
		 * 
		 * If the graph is Directed graph then calculating and printing shortest
		 * paths from source vertex.
		 */
		if (graph.getGraphType() == Type.UNDIRECTED) {
			long kruskalStartTime = System.currentTimeMillis();
			KruskalMST kruskal = new KruskalMST();
			Graph mst = kruskal.findMST(graph);
			long kruskalEndTime = System.currentTimeMillis();

			// Printing Output
			System.out.println();
			System.out.println("----- Actual Graph -----");
			graph.printGraph();
			System.out.println();
			System.out.println("----- Minimum Spanning Tree Kruskal -----");
			mst.printGraph();
			System.out.println("Cost: " + mst.calculateCost());
			System.out.println();
			System.out.println("---- Performance ----");
			System.out.println("Time taken for graph creation(ms): "
					+ (graphCreationTime - startTime));
			System.out.println("Time taken to implement kruskal(ms): "
					+ (kruskalEndTime - kruskalStartTime));
			System.out.println("Total time taken(ms): "
					+ (kruskalEndTime - startTime));
		} else {
			long dagStartTime = System.currentTimeMillis();
			DAGDistance dag = new DAGDistance();
			dag.dagDistances(graph, graph.getSource());
			long dagEndTime = System.currentTimeMillis();

			System.out.println("Vertex\tTopological Order\tPathCost");
			for (Vertex v : graph.vertices())
				System.out.println("   " + v.toString() + "  \t"
						+ v.getTopologicalNumber() + "\t\t" + v.getDistance());

			// Printing Output
			System.out.println();
			System.out.println("----- Actual Graph -----");
			graph.printGraph();
			System.out.println();
			System.out.println("----- Shortest Path from " + graph.getSource()
					+ " -----");
			for (Vertex v : graph.vertices()) {
				System.out.println("Vertex: " + v);
				List<Edge> pathList = new ArrayList<Edge>();
				Edge parent = v.getParent();
				Vertex end = v;
				while (parent != null) {
					pathList.add(parent);
					end = graph.opposite(end, parent);
					parent = end.getParent();
					;
				}
				Collections.reverse(pathList);
				System.out.println("Path: " + pathList);
				System.out.println("Cost: " + v.getDistance());
				System.out.println();
			}
			System.out.println("---- Performance ----");
			System.out.println("Time taken for graph creation(ms): "
					+ (graphCreationTime - startTime));
			System.out.println("Time taken to find shortest paths(ms): "
					+ (dagEndTime - dagStartTime));
			System.out.println("Total time taken(ms): "
					+ (dagEndTime - startTime));
		}

	}

}
