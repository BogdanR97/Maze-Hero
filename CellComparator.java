//package magicMaze;
import java.util.*;

/**
 * Comparatorul folosit pentru ordonarea celulelor conform
 * specificatiilor din enunt
 * @author bogdan
 *
 */
public class CellComparator implements Comparator<EmptyCell>{
	
	public int compare(EmptyCell cell1, EmptyCell cell2){
	
		int comp1 = cell1.getNoOfVisits() - cell2.getNoOfVisits();
		
		if(comp1 != 0)
			return comp1;
		
		else{
			String direction1 = cell1.getPosition();
			String direction2 = cell2.getPosition();
			
			if(direction1.equals("right") && direction2.matches("front|left|back"))
				return -1;
			else if(direction1.equals("front") && direction2.matches("left|back"))
				return -1;
			else if(direction1.equals("left") && direction2.equals("back"))
				return -1;
			else return 1;
		}
			
	}
}
