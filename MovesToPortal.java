//package magicMaze;

/**
 * Clasa ajuta la stocarea indecsilor celulelor
 * prin care trece eroul in drumul acestuia catre portal
 * @author bogdan
 *
 */
public class MovesToPortal {
	
	private int x;
	private int y;
	private int dist;
	
	/**
	 * Acest constructor este folosit pentru al doilea task
	 * unde ne intereseaza distanta dintre celula curenta
	 * si intrarea in labirint
	 * @param x indexul randului din labirint
	 * @param y indexul coloanei din labirint
	 * @param dist distanta fata de intrarea in labirint
	 */
	public MovesToPortal(int x, int y, int dist){
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
	
	/**
	 * Acest constructor este folosit pentru primul task
	 * @param x indexul randului din labirint
	 * @param y indexul coloanei din labirint
	 */
	public MovesToPortal(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return indexul randului
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * @return indexul coloanei
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * @return distanta dintre celula curenta si intrare
	 */
	public int getDist(){
		return dist;
	}
}
