//package magicMaze;

/**
 * 
 * @author bogdan
 *
 */
public class Main {
	
	/**
	 * @param args indicativul taskului ce trebuie indeplinit, fisierul de input si fisierul de output
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		Maze magicMaze = new Maze(args[1]);
		Output out = new Output(args[2]);
		if(args[0].equals("1"))
			magicMaze.FindThePortal(out);
		else
			magicMaze.ShortestPathToPortal(out);
		
		
	}
	

}
