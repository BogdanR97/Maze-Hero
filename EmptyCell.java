//package magicMaze;

/**
 * Clasa corespunzatoare celulelor libere
 * @author bogdan
 *
 */
public class EmptyCell extends Cell {
	
	private int noOfVisits;
	private String position;
	
	/**
	 * Actualizeaza pozitia celulei, raportata la orientarea eroului
	 * @param position pozitia relativa a celulei raportata la
	 * orientarea eroului
	 */
	public void updatePosition(String position){
		this.position = position;
	}
	
	/**
	 * @return pozitia celulei
	 */
	public String getPosition(){
		return position;
	}
	
	/**
	 * @return numarul vizitelor eroului
	 * prin celula curenta
	 */
	public int getNoOfVisits(){
		return noOfVisits;
	}
	
	/**
	 *Actualizeaza numarul de vizite din celula curenta
	 */
	public void visit(){
		noOfVisits++;
	}
}
