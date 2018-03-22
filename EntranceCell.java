//package magicMaze;

/**
 * Intrarea in labirint
 * @author bogdan
 *
 */
public class EntranceCell extends EmptyCell {
	
	private int enterX;
	private int enterY;
	
	/**
	 * @param enterX indexul liniei
	 * @param enterY indexul coloanei
	 */
	public EntranceCell(int enterX, int enterY){
		this.enterX = enterX;
		this.enterY = enterY;
	}
	
	/**
	 * @return indexul liniei
	 */
	public int getX(){
		return enterX;
	}
	
	/**
	 * @return indexul coloanei
	 */
	public int getY(){
		return enterY;
	}
}
