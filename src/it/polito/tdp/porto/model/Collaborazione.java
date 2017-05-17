package it.polito.tdp.porto.model;

public class Collaborazione {

	private int id1;
	private int id2;
	private int idArticolo;

	public Collaborazione(int id1, int id2, int idArticolo) {
		this.id1 = id1;
		this.id2 = id2;
		this.idArticolo = idArticolo;
	}

	public int getId1() {
		return id1;
	}

	public void setId1(int id1) {
		this.id1 = id1;
	}

	public int getId2() {
		return id2;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}

	public int getIdArticolo() {
		return idArticolo;
	}

	public void setIdArticolo(int idArticolo) {
		this.idArticolo = idArticolo;
	}
	

}
