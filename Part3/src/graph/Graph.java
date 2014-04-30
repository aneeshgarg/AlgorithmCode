package graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Adjacency List Graph Structure
 * 
 * @author Aneesh Garg
 */
public class Graph {
	public enum Type {
		DIRECTED, UNDIRECTED
	}

	private Type graphType;
	private ArrayList<VertexListObject> vertexList;
	private ArrayList<EdgeListObject> edgeList;
	private Vertex source;
	private int noOfVertices;
	private int noOfEdges;

	public void insertVertex(String key) {
		Vertex vertex = new Vertex(key);

		VertexListObject vlo = new VertexListObject(vertex);
		if (this.vertexList == null)
			this.vertexList = new ArrayList<VertexListObject>();
		this.vertexList.add(vlo);

		vertex.setVertexListObject(vlo);

		System.out.println("Inserted vertex: " + vertex);
	}

	public void insertEdge(String startKey, String endKey, int weight) {
		Vertex startVertex = getVertex(startKey);
		Vertex endVertex = getVertex(endKey);

		if (startVertex == null) {
			insertVertex(startKey);
			startVertex = getVertex(startKey);
		}
		if (endVertex == null) {
			insertVertex(endKey);
			endVertex = getVertex(endKey);
		}

		Edge edge = new Edge(startVertex, endVertex, weight);

		EdgeListObject elo = new EdgeListObject(edge);
		if (this.edgeList == null)
			this.edgeList = new ArrayList<EdgeListObject>();
		this.edgeList.add(elo);
		edge.setEdgeListObject(elo);
		if (graphType == Type.DIRECTED) {
			if (startVertex.getOutEdges() == null)
				startVertex.setOutEdges(new ArrayList<EdgeListObject>());
			startVertex.getOutEdges().add(elo);

			if (endVertex.getInEdges() == null)
				endVertex.setInEdges(new ArrayList<EdgeListObject>());
			endVertex.getInEdges().add(elo);
		}

		EdgeListObject startAugmentedEdgeObject = new EdgeListObject(edge);
		if (startVertex.getIncidenceList() == null)
			startVertex.setIncidenceList(new ArrayList<EdgeListObject>());
		startVertex.getIncidenceList().add(startAugmentedEdgeObject);
		edge.setStartAugmentedEdgeObject(startAugmentedEdgeObject);

		EdgeListObject endAugmentedEdgeObject = new EdgeListObject(edge);
		if (endVertex.getIncidenceList() == null)
			endVertex.setIncidenceList(new ArrayList<EdgeListObject>());
		endVertex.getIncidenceList().add(endAugmentedEdgeObject);
		edge.setEndAugmentedEdgeObject(endAugmentedEdgeObject);

		System.out.println("Inserted edge: " + startKey + "--" + weight + "--"
				+ endKey);

	}

	public List<Edge> incidentEdges(Vertex vertex) {
		ArrayList<Edge> edgeList = null;
		if (graphType == Type.UNDIRECTED) {
			for (EdgeListObject elo : vertex.getIncidenceList()) {
				if (edgeList == null)
					edgeList = new ArrayList<Edge>();
				edgeList.add(elo.getEdge());
			}
		} else {
			List<EdgeListObject> outEdges = vertex.getOutEdges();
			if (outEdges != null) {
				for (EdgeListObject elo : outEdges) {
					if (edgeList == null)
						edgeList = new ArrayList<Edge>();
					edgeList.add(elo.getEdge());
				}
			}
		}
		return edgeList;

	}

	public List<Edge> edges() {
		ArrayList<Edge> edgeList = null;
		if (this.edgeList != null) {
			for (EdgeListObject elo : this.edgeList) {
				if (edgeList == null)
					edgeList = new ArrayList<Edge>();
				edgeList.add(elo.getEdge());
			}
		}
		return edgeList;
	}

	public List<Vertex> vertices() {
		ArrayList<Vertex> vertexList = null;
		if (this.vertexList != null) {
			for (VertexListObject elo : this.vertexList) {
				if (vertexList == null)
					vertexList = new ArrayList<Vertex>();
				vertexList.add(elo.getVertex());
			}
		}
		return vertexList;
	}

	public void printGraph() {
		System.out.println();
		System.out.println("Vertices: " + vertices().toString());
		System.out.println("Edges: " + edges().toString());
		System.out.println("Source: [" + source + "]");
		System.out.println("Type: ["
				+ ((graphType == Type.DIRECTED) ? "Directed" : "Undirected")
				+ "]");
	}

	public Vertex opposite(Vertex u, Edge edge) {
		if (edge.getStartVertex().equals(u))
			return edge.getEndVertex();
		return edge.getStartVertex();
	}

	/**
	 * This method calculates the cost of the graph i.e. sum of weights of all
	 * edges of the graph.
	 * 
	 * @return
	 */
	public int calculateCost() {
		int cost = 0;

		for (Edge edge : edges())
			cost += edge.getWeight();

		return cost;
	}

	public Type getGraphType() {
		return graphType;
	}

	public void setGraphType(Type graphType) {
		this.graphType = graphType;
	}

	public ArrayList<VertexListObject> getVertexList() {
		return vertexList;
	}

	public void setVertexList(ArrayList<VertexListObject> vertexList) {
		this.vertexList = vertexList;
	}

	public ArrayList<EdgeListObject> getEdgeList() {
		return edgeList;
	}

	public void setEdgeList(ArrayList<EdgeListObject> edgeList) {
		this.edgeList = edgeList;
	}

	public Vertex getSource() {
		return source;
	}

	public void setSource(Vertex source) {
		this.source = source;
	}

	public int getNoOfVertices() {
		return noOfVertices;
	}

	public void setNoOfVertices(int noOfVertices) {
		this.noOfVertices = noOfVertices;
	}

	public int getNoOfEdges() {
		return noOfEdges;
	}

	public void setNoOfEdges(int noOfEdges) {
		this.noOfEdges = noOfEdges;
	}

	public Vertex getVertex(String key) {
		Vertex vertex = null;
		if (vertexList != null) {
			for (VertexListObject vo : vertexList)
				if (vo != null && vo.getVertex() != null
						&& vo.getVertex().getKey().equals(key)) {
					vertex = vo.getVertex();
				}
			return vertex;
		}
		return null;
	}

}
