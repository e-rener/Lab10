package it.polito.tdp.porto.model;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;

import it.polito.tdp.porto.db.PortoDAO;

public class Model {
	
	AuthorIdMap map ;
	PortoDAO dao;
	UndirectedGraph<Author, DefaultEdge> graph;

	public Model() {
		dao = new PortoDAO();
		map = new AuthorIdMap(dao);
		graph = new SimpleGraph<Author, DefaultEdge>(DefaultEdge.class);
	}
	
	public void createGraph(){
		
		Graphs.addAllVertices(graph, map.getAuthorsMap().values());
		
		for(Collaborazione c: dao.getCollaborazioni()){
			graph.addEdge(map.getAuthorsMap().get(c.getId1()), map.getAuthorsMap().get(c.getId2()));
		}
		
	}
	
	public List<Author> findCoauthors(Author author){
		return Graphs.neighborListOf(graph, author);
	}
	
	
	public List<Paper> findPaperPath(Author a1, Author a2){
		List<Paper> paperPath = new ArrayList<Paper>();
		DijkstraShortestPath<Author, DefaultEdge> path = new DijkstraShortestPath<Author, DefaultEdge>(graph, a1, a2);
		for(DefaultEdge e: path.getPathEdgeList()){
			Paper p = dao.findCommonPaper(graph.getEdgeSource(e), graph.getEdgeTarget(e));
			paperPath.add(p);
		}
		return paperPath;
	}
	
	

	
	

}
