package it.polito.tdp.porto.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		System.out.println("TODO: write a Model class and test it!");
		
		System.out.println(model.map.getAuthorsMap().values());
		
		model.createGraph();
		
		System.out.println(model.findCoauthors(model.map.get(719)));
		
		System.out.println(model.findPaperPath(model.map.get(15455), model.map.get(4111)));
		
		
	}

}
