package graph;

/**
 * @author Aneesh Garg
 */
public class EdgeListObject {
	private Edge edge;

	public EdgeListObject(Edge edge) {
		this.edge = edge;
	}

	public Edge getEdge() {
		return edge;
	}

	public void setEdge(Edge edge) {
		this.edge = edge;
	}

	@Override
	public String toString() {
		return edge.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((edge == null) ? 0 : edge.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof EdgeListObject))
			return false;
		EdgeListObject other = (EdgeListObject) obj;
		if (edge == null) {
			if (other.edge != null)
				return false;
		} else if (!edge.equals(other.edge))
			return false;
		return true;
	}

}
