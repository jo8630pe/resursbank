package dfs_with_visitors_applications;
import graph.Graph;
import dfs_with_visitor.*;

public class DFS_With_Visitors_Applications {
	
	
	
	public static <V,E> boolean isConnected(Graph<V,E> g) {
		g.unvisit();
		DFS.dfs(g.iterator().next(), new VertexVisitor<V,E>());
		for (Graph.Vertex<V,E> v : g) {
			if (! v.isVisited()) {
				return false;
			}
		}
		return true;
	}
	
	public static <V,E> int componentSize(Graph<V,E> g,
								Graph.Vertex<V,E> v) {
		g.unvisit();
		CountingVisitor<V,E> cv = new CountingVisitor<V,E>();
		DFS.dfs(v,cv);
		return cv.count;
	}
	
	public static <V,E> boolean pathExists(Graph<V,E> g,
							Graph.Vertex<V,E> v,
							Graph.Vertex<V,E> u) {
		g.unvisit();
		TestVisitor<V,E> tv = new TestVisitor<V,E>(u);
		DFS.dfs(v,tv);
		
		if(u.isVisited()) {
			return true;
		}
		
		return false;
	}
	
	static class CountingVisitor<V,E> extends VertexVisitor<V,E> {
		private int count;
		
		public CountingVisitor() {
			super();
			count = 0;
		}
		
		public void preVisit(Graph.Vertex<V,E> v) {
			count++;
		}		
	}
	
	static class TestVisitor<V,E> extends VertexVisitor<V,E> {
		Graph.Vertex<V,E> u;

		
		public TestVisitor(Graph.Vertex<V,E> u) {
			super();
			this.u = u;
		}
		
		public boolean isDone(Graph.Vertex<V,E> v) {
			return v.equals(u);
		}
		
		
	}
	
}
