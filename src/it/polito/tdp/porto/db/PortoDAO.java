package it.polito.tdp.porto.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.AuthorIdMap;
import it.polito.tdp.porto.model.Collaborazione;
import it.polito.tdp.porto.model.Paper;

public class PortoDAO {
	
	AuthorIdMap map = new AuthorIdMap();


	/*
	 * Dato l'id ottengo l'autore.
	 */
	public Author getAutore(int id) {

		final String sql = "SELECT * FROM author where id=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				return autore;
			}

			return null;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato l'id ottengo l'articolo.
	 */
	public Paper getArticolo(int eprintid) {

		final String sql = "SELECT * FROM paper where eprintid=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, eprintid);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				return paper;
			}

			return null;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	
	public Map<Integer, Author> getAutori() {

		final String sql = "SELECT * FROM author";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				map.put(rs.getInt("id"), autore);
			}

			return map.getAuthorsMap();

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public List<Collaborazione> getCollaborazioni(){
		
		List<Collaborazione> collaborazioni = new ArrayList<Collaborazione>();
		
		final String sql = "SELECT DISTINCT a1.id AS id1, a2.id AS id2, c1.eprintid "+
                        "FROM creator c1, creator c2, author a1, author a2 "+
                        "WHERE c1.eprintid=c2.eprintid AND c1.authorid<>c2.authorid "+
                        "AND c1.authorid=a1.id AND c2.authorid=a2.id "+
                        "ORDER BY c1.eprintid";
			
			try {
				Connection conn = DBConnect.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				
				ResultSet rs = st.executeQuery();
	
				while (rs.next()) {
					Collaborazione coll = new Collaborazione(rs.getInt("id1"), rs.getInt("id2"), rs.getInt("eprintid"));
					collaborazioni.add(coll);
				}
	
				return collaborazioni;
	
			} catch (SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException("Errore Db");
			}

	}

	public Paper findCommonPaper(Author a1, Author a2) {
		
		final String sql = "SELECT DISTINCT p.* "+
				"FROM paper p, creator c1, creator c2 "+
				"WHERE c1.eprintid=c2.eprintid AND c1.authorid=? AND c2.authorid=?";
	
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, a1.getId());
			st.setInt(2, a2.getId());
			
			ResultSet rs = st.executeQuery();
	
			if (rs.next()) {
				Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				return paper;
			}
	
			return null;
	
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}