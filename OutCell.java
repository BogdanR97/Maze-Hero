//package magicMaze;

/**
 * Portalul de iesire din labirint
 * @author bogdan
 *
 */
public class OutCell extends Cell {
	
	private int outX;
	private int outY;
	
	/**
	 * @param outX indexul liniei
	 * @param outY indexul coloanei
	 */
	public OutCell(int outX, int outY){
		this.outX = outX;
		this.outY = outY;
	}
	
	/**
	 * @return indexul liniei
	 */
	public int getX(){
		return outX;
	}
	
	/**
	 * @return indexul coloanei
	 */
	public int getY(){
		return outY;
	}
}
