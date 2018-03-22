//package magicMaze;

/**
 * @author bogdan
 *
 */
public class CannotMoveIntoWallsException extends Exception {
	
	/**
	 *In cazul in care eroul incearca sa paseasca pe o celula zid,
	 *se va arunca aceasta exceptie si va fii afisat urmatorul mesaj 
	 */
	public CannotMoveIntoWallsException(){
		System.out.println("Hero can not move into a wall !");
	}
}
