package it.polito.tdp.porto.model;

import java.util.HashMap;
import java.util.Map;

import it.polito.tdp.porto.db.PortoDAO;

public class AuthorIdMap {
	
	Map<Integer, Author> autori;
	private PortoDAO dao;

	public AuthorIdMap(PortoDAO dao) {
		this.dao = dao;
		autori = new HashMap<Integer, Author>();
	}
	
	public AuthorIdMap() {
		autori = new HashMap<Integer, Author>();
	}

	public Author get(int id){
		return autori.get(id);
	}
	
	public Author put(int id, Author autore){
		if(autori.containsKey(id))
			return autori.get(id);
		else{
			autori.put(id, autore);
			return autori.get(id);
		}
	}
	
	public Map<Integer, Author> getAuthorsMap(){
		if(autori.size() == 0){
			for(Author a: dao.getAutori().values()){
				autori.put(a.getId(), a);
			}
			return autori;
		}
		return autori;
	}

}
