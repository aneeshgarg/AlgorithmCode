package graph;

/**
 * @author Aneesh Garg
 */
public class Edge implements Comparable<Edge>{
	
	public enum EdgeType {UNEXPLORED, DISCOVERY, FORWARD, CROSS}

	private int weight;
	private Vertex startVertex;
	private Vertex endVertex;
	/**
	 * reference to position in edge sequence
	 */
	private EdgeListObject edgeListObject;
	/**
	 * references to associated positions in incidence sequences of start
	 * vertices
	 */
	private EdgeListObject startAugmentedEdgeObject;
	/**
	 * references to associated positions in incidence sequences of end vertices
	 */
	private EdgeListObject endAugmentedEdgeObject;
	
	private EdgeType edgeType;

	public Edge(Vertex startVertex, Vertex endVertex, int weight) {
		this.startVertex = startVertex;
		this.endVertex = endVertex;
		this.weight = weight;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Vertex getStartVertex() {
		return startVertex;
	}

	public void setStartVertex(Vertex startVertex) {
		this.startVertex = startVertex;
	}

	public Vertex getEndVertex() {
		return endVertex;
	}

	public void setEndVertex(Vertex endVertex) {
		this.endVertex = endVertex;
	}

	public EdgeListObject getEdgeListObject() {
		return edgeListObject;
	}

	public void setEdgeListObject(EdgeListObject edgeListObject) {
		this.edgeListObject = edgeListObject;
	}

	public EdgeListObject getStartAugmentedEdgeObject() {
		return startAugmentedEdgeObject;
	}

	public void setStartAugmentedEdgeObject(
			EdgeListObject startAugmentedEdgeObject) {
		this.startAugmentedEdgeObject = startAugmentedEdgeObject;
	}

	public EdgeListObject getEndAugmentedEdgeObject() {
		return endAugmentedEdgeObject;
	}

	public void setEndAugmentedEdgeObject(EdgeListObject endAugmentedEdgeObject) {
		this.endAugmentedEdgeObject = endAugmentedEdgeObject;
	}

	public EdgeType getEdgeType() {
		return edgeType;
	}

	public void setEdgeType(EdgeType edgeType) {
		this.edgeType = edgeType;
	}

	@Override
	public String toString() {
		return startVertex +" "+ endVertex +" "+ weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((endVertex == null) ? 0 : endVertex.hashCode());
		result = prime * result
				+ ((startVertex == null) ? 0 : startVertex.hashCode());
		result = prime * result + weight;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Edge))
			return false;
		Edge other = (Edge) obj;
		if (endVertex == null) {
			if (other.endVertex != null)
				return false;
		} else if (!endVertex.equals(other.endVertex))
			return false;
		if (startVertex == null) {
			if (other.startVertex != null)
				return false;
		} else if (!startVertex.equals(other.startVertex))
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

	@Override
	public int compareTo(Edge o) {
		return (new Integer(weight)).compareTo(new Integer(o.getWeight()));
	}
}
