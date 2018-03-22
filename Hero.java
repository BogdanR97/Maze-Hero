//package magicMaze;
import java.util.*;

/**
 *Clasa ce implementeaza functionalitatile eroului: 
 *acesta isi va alege pasul urmator in functie de ordinea specificata
 *in enunt, raportandu-se la orientarea acestuia
 * @author bogdan
 *
 */
public class Hero {
	
	/* Lista contine celulele ce urmeaza a fi sortate conform algoritmului
	 * din enunt */
	private ArrayList<EmptyCell> cellsToVisit;
	private String orientation;
	
	/**
	 * Constructorul clasei
	 */
	public Hero(){
		cellsToVisit = new ArrayList<EmptyCell>();
		orientation = "front";
	}
	
	/**
	 * Eroul isi schimba orientarea in functie de pasul executat
	 * @param orientation noua orientare a eroului
	 */
	public void changeOrientation(String orientation){
		this.orientation = orientation;
	}
	
	/**
	 * Metoda introduce in lista eroului o noua celula valida
	 * @param cell celula ce urmeaza a fi examinata
	 * @param direction pozitionarea celulei, IN RAPORT CU ORIENTAREA LABIRINTULUI,
	 * NU A EROULUI!
	 */
	public void add(EmptyCell cell, String direction){
		
		/* se va decide pozitionarea celulei, raportata la orientarea actuala a eroului */
		
		if(orientation.equals("right")){
			
			if(direction.equals("right"))
				direction="front";
			else if(direction.equals("left"))
				direction="back";
			else if(direction.equals("front"))
				direction="left";
			else if(direction.equals("back"))
				direction="right";
		}
		
		else if(orientation.equals("left")){
			
			if(direction.equals("left"))
				direction="front";
			else if(direction.equals("right"))
				direction="back";
			else if(direction.equals("back"))
				direction="left";
			else if(direction.equals("front"))
				direction="right";
		}
		
		else if(orientation.equals("back")){
			
			if(direction.equals("back"))
				direction="front";
			else if(direction.equals("front"))
				direction="back";
			else if(direction.equals("right"))
				direction="left";
			else if(direction.equals("left"))
				direction="right";
		}
		
		cell.updatePosition(direction);
		cellsToVisit.add(cell);
	}
	
	/**
	 * Sterge continutul listei 
	 */
	private void clear(){
		cellsToVisit.clear();
	}
	
	/**
	 * @return directia in care se va deplasa eroul,
	 * raportata la orientarea labirintului
	 */
	public String GetNextMove(){
		
		/* se efectueaza sortarea listei, conform criteriilor specificate in enunt,
		 * dupa care se decide directia deplasarii in functie de orientarea labirintului */
		Collections.sort(cellsToVisit, new CellComparator());
		String direction = cellsToVisit.get(0).getPosition();
		
		if(orientation.equals("right")){
			
			if(direction.equals("front"))
				direction="right";
			else if(direction.equals("back"))
				direction="left";
			else if(direction.equals("left"))
				direction="front";
			else if(direction.equals("right"))
				direction="back";
		}
		
		else if(orientation.equals("left")){
			
			if(direction.equals("front"))
				direction="left";
			else if(direction.equals("back"))
				direction="right";
			else if(direction.equals("left"))
				direction="back";
			else if(direction.equals("right"))
				direction="front";
		}
		
		else if(orientation.equals("back")){
			
			if(direction.equals("front"))
				direction="back";
			else if(direction.equals("back"))
				direction="front";
			else if(direction.equals("left"))
				direction="right";
			else if(direction.equals("right"))
				direction="left";
		}
		
		orientation=direction;
		clear();
		return direction;
		
	}
	
}
