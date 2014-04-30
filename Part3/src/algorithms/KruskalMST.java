package algorithms;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import graph.Edge;
import graph.Graph;
import graph.Vertex;

/**
 * @author Aneesh Garg
 */
public class KruskalMST {
	public Graph findMST(Graph g) {

		List<Edge> edges = g.edges();
		List<Vertex> vertices = g.vertices();
		/**
		 * partition of the vertices of G, where each vertex forms a separate
		 * set
		 */
		Partition p = new Partition(vertices);
		/**
		 * priority queue storing the edges of G, sorted by their weights
		 */
		PriorityQueue<Edge> q = new PriorityQueue<Edge>(edges);
		/**
		 * an initially-empty tree
		 */
		Graph mst = new Graph();

		while (!q.isEmpty()) {
			Edge minEdge = q.remove();
			System.out.println("Removed Edge: " + minEdge);
			Vertex u = minEdge.getStartVertex();
			Vertex v = minEdge.getEndVertex();
			int weight = minEdge.getWeight();

			if (p.find(u) != p.find(v)) {
				mst.insertEdge(u.getKey(), v.getKey(), weight);
				p.union(u, v, weight);
			}
		}

		return mst;
	}

	private static class Partition {
		Set<Graph> partitionSet;

		public Partition(List<Vertex> vertices) {
			this.partitionSet = new HashSet<Graph>();
			for (Vertex vertex : vertices) {
				Graph g = new Graph();
				g.insertVertex(vertex.getKey());
				partitionSet.add(g);
			}
		}

		public void union(Vertex u, Vertex v, int weight) {
			Graph partitionU = find(u);
			Graph partitionV = find(v);

			partitionSet.remove(partitionU);
			partitionSet.remove(partitionV);

			Graph partition = new Graph();
			List<Edge> uEdgeList = partitionU.edges();
			if (uEdgeList != null)
				for (Edge edge : uEdgeList)
					partition.insertEdge(edge.getStartVertex().getKey(), edge
							.getEndVertex().getKey(), edge.getWeight());

			List<Edge> vEdgeList = partitionV.edges();
			if (vEdgeList != null)
				for (Edge edge : vEdgeList)
					partition.insertEdge(edge.getStartVertex().getKey(), edge
							.getEndVertex().getKey(), edge.getWeight());

			partition.insertEdge(u.getKey(), v.getKey(), weight);
			partitionSet.add(partition);

		}

		public Graph find(Vertex u) {
			for (Graph partition : partitionSet) {
				if (partition.vertices().contains(u))
					return partition;
			}
			return null;
		}
	}
}
