package graph;

import java.util.List;

/**
 * @author Aneesh Garg
 */
public class Vertex {
	public enum VertexType {
		UNEXPLORED, VISITED
	}

	private String key;
	/**
	 * reference to position in vertex sequence
	 */
	private VertexListObject vertexListObject;
	/**
	 * sequence of references to edge objects of incident edges
	 */
	private List<EdgeListObject> incidenceList;
	/**
	 * List of edges ending on this vertex. Used for directed graphs only
	 */
	private List<EdgeListObject> inEdges;
	/**
	 * List of edges starting from this vertex. Used for directed graphs only
	 */
	private List<EdgeListObject> outEdges;

	private VertexType vertexType;
	private int topologicalNumber;
	private int distance;
	/**
	 * represents the parent edge which led to this vertex in shortest path. Used for printing shortest path.
	 */
	private Edge parent;

	public Vertex(String key) {
		this.setKey(key);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public VertexListObject getVertexListObject() {
		return vertexListObject;
	}

	public void setVertexListObject(VertexListObject vertexListObject) {
		this.vertexListObject = vertexListObject;
	}

	public List<EdgeListObject> getIncidenceList() {
		return incidenceList;
	}

	public void setIncidenceList(List<EdgeListObject> incidenceList) {
		this.incidenceList = incidenceList;
	}

	public VertexType getVertexType() {
		return vertexType;
	}

	public void setVertexType(VertexType vertexType) {
		this.vertexType = vertexType;
	}

	public List<EdgeListObject> getInEdges() {
		return inEdges;
	}

	public void setInEdges(List<EdgeListObject> inEdges) {
		this.inEdges = inEdges;
	}

	public List<EdgeListObject> getOutEdges() {
		return outEdges;
	}

	public void setOutEdges(List<EdgeListObject> outEdges) {
		this.outEdges = outEdges;
	}

	public int getTopologicalNumber() {
		return topologicalNumber;
	}

	public void setTopologicalNumber(int topologicalNumber) {
		this.topologicalNumber = topologicalNumber;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Edge getParent() {
		return parent;
	}

	public void setParent(Edge parent) {
		this.parent = parent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vertex))
			return false;
		Vertex other = (Vertex) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return key;
	}
}
