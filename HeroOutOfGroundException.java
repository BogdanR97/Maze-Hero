//package magicMaze;

/**
 * @author bogdan
 *
 */
public class HeroOutOfGroundException extends Exception {
	
	/**
	 *In cazul in care eroul incearca sa paseasca in afara labirintului,
	 *se va arunca aceasta exceptie si va fii afisat urmatorul mesaj 
	 */
	public HeroOutOfGroundException(){
		System.out.println("Hero stepped out of the maze!");
	}

}
