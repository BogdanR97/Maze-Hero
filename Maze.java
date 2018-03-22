//package magicMaze;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Clasa in care este implementat labirintul si
 *metodele corespunzatoare rezolvarii celor 2 taskuri
 * @author bogdan
 *
 */
public class Maze {
	
	Cell[][] mazeCells;
	EntranceCell enter;
	OutCell portal;
	
	/**
	 * Constructorul clasei initializeaza campurile acesteia
	 * in functie de datele din fisierul de input
	 * @param filename fisierul de input, din care
	 * se citesc datele necesare initializarii labirintului
	 */
	public Maze(String filename){
		
		try{
			BufferedReader in = new BufferedReader(new FileReader(new File(filename)));
			
			String[] dimensions = in.readLine().trim().split(" ");
			int height =  Integer.parseInt(dimensions[0]);
			int width = Integer.parseInt(dimensions[1]);
			mazeCells = new Cell[height][width];
			
			for(int i=0; i<height; i++){
				String[] cells = in.readLine().trim().split("");
				
				/*in functie de input, se va initializa corespunzator fiecare
				 * celula din matricea labirint */
				for(int j=0; j<width; j++){
					
					if(cells[j].equals(".")){
						mazeCells[i][j] = new EmptyCell();		
					}
					
					else if(cells[j].equals("#")){
						mazeCells[i][j] = new WallCell();
					}
					
					else if(cells[j].equals("I")){
						enter = new EntranceCell(i,j);
						mazeCells[i][j] = enter;
						
					}
					
					else if(cells[j].equals("O")){
						portal = new OutCell(i,j);
						mazeCells[i][j] = portal;
					}
				}
			}
			
			in.close();
			
		}catch(IOException ex){
			System.out.println("Read Failed");
		}
	}
	
	/**
	 * Scrie in fisierul output numarul celulelor prin care trece 
	 * eroul in drumul spre portal, dar si indecsii acestora
	 * @param out fisierul de output
	 * @param path lista in care sunt stocati indecsii celulelor
	 * prin care trece eroul in drumul spre portal
	 * @param noOfMoves numarul de celule prin care trece eroul
	 */
	private void PrintPath(Output out, ArrayList<MovesToPortal> path, int noOfMoves){
		
		out.print(Integer.toString(noOfMoves));
		for(MovesToPortal move : path)
			out.print(move.getX() + " " + move.getY());
	}
	
	/**
	 * Gaseste drumul catre portalul de iesire, conform strategiei impuse
	 * @param out fisieurl de output
	 * @throws CannotMoveIntoWallsException exceptie aruncata in cazul in care
	 * eroul incearca sa se pozitioneze pe o celula de tipul WallCell
	 * @throws HeroOutOfGroundException exceptie aruncata in cazul in care 
	 * eroul incearca sa se pozitioneze in afara labirintului
	 */
	public void FindThePortal(Output out) throws CannotMoveIntoWallsException, HeroOutOfGroundException{
		
		int i=enter.getX(), j=enter.getY(), noOfMoves=0;
		int height = mazeCells.length;
		int width = mazeCells[0].length;
		
		Hero superHero = new Hero();
		
		/* lista contine celulele prin care trece eroul in drumul spre portal,
		 * acestea fiind adaugate pe parcursul "explorarii" labirintului */
		ArrayList <MovesToPortal> path = new ArrayList<MovesToPortal>();
		
		while(true){
			
			if( i<0 || i>=mazeCells.length || j<0 || j>=mazeCells[0].length){
				throw new HeroOutOfGroundException();
			}
			
			else if( mazeCells[i][j] instanceof WallCell){
				throw new CannotMoveIntoWallsException();
			}
			
			if( mazeCells[i][j] instanceof OutCell ){
	
				MovesToPortal finalmove = new MovesToPortal(i, j);
				path.add(finalmove);
				noOfMoves++;
				break;
			}
			
			else if(mazeCells[i][j] instanceof EmptyCell){

				MovesToPortal move = new MovesToPortal(i, j);
				path.add(move);
				noOfMoves++;
				((EmptyCell)mazeCells[i][j]).visit();
				
				/* in cazul in care in vecinatatea eroului se afla
				 * celula de iesire din labirint, el o va alege pe acesta indiferent
				 * de prioritatea celorlalte celule */
				
				if(i >= 1 && (mazeCells[i-1][j] instanceof OutCell)){
					i--;
					continue;
				}
				
				if(i < height-1 && (mazeCells[i+1][j] instanceof OutCell)){
					i++;
					continue;
				}
					
				if(j >= 1 && (mazeCells[i][j-1] instanceof OutCell)){
					j--;
					continue;
				}
		
				if(j < width-1 && (mazeCells[i][j+1] instanceof OutCell)){
					j++;
					continue;
				}
				
				/* se verifica validitatea celulelor din preajma eroului -
				 * celulele sunt situate in interiorul labirintului si acestea sunt "libere";
				 * celulele valide urmeaza sa fie sortate in functie de prioritatea stabilita in enunt
				 * pentru a determina urmatoarea miscare a eroului*/
				
				if(i >= 1 && (mazeCells[i-1][j] instanceof EmptyCell))
					superHero.add((EmptyCell)mazeCells[i - 1][j], "front");
				
				if(i < height-1 && (mazeCells[i+1][j] instanceof EmptyCell))
					superHero.add((EmptyCell)mazeCells[i + 1][j], "back");
				
				if(j >= 1 && (mazeCells[i][j-1] instanceof EmptyCell))
					superHero.add((EmptyCell)mazeCells[i][j-1], "left");
				
				if(j < width-1 && (mazeCells[i][j+1] instanceof EmptyCell))
					superHero.add((EmptyCell)mazeCells[i][j+1], "right");
				
				/* in functie de decizia eroului, se executa urmatoare mutare */
				String nextMove = superHero.GetNextMove();
				
				if(nextMove.equals("front"))
					i--;
				else if(nextMove.equals("back"))
					i++;
				else if(nextMove.equals("right"))
					j++;
				else if(nextMove.equals("left"))
					j--;
				
			}
			
		}
		
		PrintPath(out, path, noOfMoves);
	}
	
	
	/**
	 * Metoda printeaza in fisier cel mai scurt drum catre portal
	 * @param out fisierul de output
	 * @param reversePath stiva in care sunt stocate
	 * celulele corespunzatoare celui mai scurt drum
	 * @param moves numarul de celule din drum
	 */
	private void PrintShortestPath(Output out, Stack<MovesToPortal> reversePath, int moves){
		
		out.print(Integer.toString(moves));
		while(!reversePath.isEmpty()){
			MovesToPortal show = reversePath.pop();
			out.print(show.getX() + " " + show.getY());
		}
		out.close();
	}
	
	/**
	 * Metoda verifica existenta unei celule in lista "path" cu aceleasi proprietati
	 * ca a celulei "checkCell"
	 * @param path lista in care sunt stocate totalitatea celulelor
	 * vizate de algoritmul Lee
	 * @param checkCell celula de cautat in lista
	 * @return true - celula "checkCell" se afla in lista; false - nu se afla
	 */
	private boolean IsCellInThePath(LinkedList<MovesToPortal> path, MovesToPortal checkCell){
		for (ListIterator<MovesToPortal> iterator = path.listIterator(path.size()); iterator.hasPrevious();){
			MovesToPortal el = iterator.previous();
			if(el.getX() == checkCell.getX() && el.getY() == checkCell.getY() && el.getDist() == checkCell.getDist() ){
				return true;
				
			}
		}
		return false;
	}
	
	/**
	 * Metoda implementeaza algoritmul Lee pentru gasirea
	 * celui mai scurt drum intr-un labirint
	 * @param out fisierul de output
	 */
	public void ShortestPathToPortal(Output out){
		
		int i=enter.getX(), j=enter.getY(), noOfMoves=0, dist;
		int outX=portal.getX(), outY=portal.getY();
		int height = mazeCells.length;
		int width = mazeCells[0].length;
		
		/* in acesta matrice vom tine evidenta celulelor vizitate
		 * true = celula a fost vizitata
		 * false = celula nu a fost vizitata */
		boolean[][] visited = new boolean[height][width];
		
		/*coada in care vor fi stocate celulele nevizitate din preajma celulei
		 * procesate; se incepe cu celula de intrare*/
		Queue<MovesToPortal> qu = new LinkedList<MovesToPortal>();
		
		/* in lista sunt retinute totalitaea celulelor vizitate prin intermediul
		 * algoritmului Lee */
		LinkedList<MovesToPortal> path = new LinkedList<MovesToPortal>();
		
		visited[i][j] = true;
		MovesToPortal firstMove = new MovesToPortal(i, j, 0);
		qu.add(firstMove);
		path.push(firstMove);
		
		while(!qu.isEmpty()){
			
			MovesToPortal move = qu.poll();
			i = move.getX();
			j = move.getY();
			dist = move.getDist();
			
			/* in cazul in care coordonatele celulei actual procesate coincid
			 * cu coordonatele portalului, se opreste cautarea */
			if(i == outX && j == outY){
				noOfMoves = dist;
				noOfMoves++;
				break;
			}
		
			/* se verifica validitatea celulelor din preajma celulei actual procesate si,
			 * in functie de acest fapt, se introduc/nu se introduc in coada&lista */
			if(i < height-1 && !(mazeCells[i+1][j] instanceof WallCell) && !(visited[i+1][j])){
				visited[i+1][j] = true;
				MovesToPortal newMove = new MovesToPortal(i+1, j, dist+1);
				qu.add(newMove);
				path.push(newMove);
			}
			

			if(j >= 1 && !(mazeCells[i][j-1] instanceof WallCell) && !(visited[i][j-1])){
				visited[i][j-1] = true;
				MovesToPortal newMove = new MovesToPortal(i, j-1, dist+1);
				qu.add(newMove);
				path.push(newMove);
			}

			if(i >= 1 && !(mazeCells[i-1][j] instanceof WallCell) && !(visited[i-1][j])){
				visited[i-1][j] = true;
				MovesToPortal newMove = new MovesToPortal(i-1, j, dist+1);
				qu.add(newMove);
				path.push(newMove);
			}

			if(j < width-1 && !(mazeCells[i][j+1] instanceof WallCell) && !(visited[i][j+1])){
				visited[i][j+1] = true;
				MovesToPortal newMove = new MovesToPortal(i, j+1, dist+1);
				qu.add(newMove);
				path.push(newMove);
			}
			
				
		}
		
		/* Odata ce s-a ajuns la portal, prima parte a algoritmului se incheie - gasirea lungimii minime
		 * a drumului. A doua parte are rolul de a gasi celulele propriu-zise ce alcatuiesc
		 * drumul cel mai scurt. Se incepe cautarea de la celula portal */
		MovesToPortal move = new MovesToPortal(outX, outY, --noOfMoves);
		
		/* Cautarea incepand de la celula portal, celulele ce alcatuiesc drumul vor fii gasite in ordine
		 * inversa. Astfel, introducem celulele gasite rand pe rand intr-o stiva, pentru ca mai apoi 
		 * sa le afisam in ordinea corespunzatoare intrare -> iesire */
		Stack<MovesToPortal> reversePath = new Stack<MovesToPortal>();
		reversePath.push(move);

		
		while(!(move.getX() == firstMove.getX() && move.getY() == firstMove.getY() )){
		
			i = move.getX();
			j = move.getY();
			dist = move.getDist();
			
			/* Se efectueaza selectia celulelor */
			
			if(j < width-1 && !(mazeCells[i][j+1] instanceof WallCell) && (visited[i][j+1])){
				MovesToPortal newMove = new MovesToPortal(i, j+1, dist-1);
				if(IsCellInThePath(path, newMove)){
					visited[i][j+1]=false;
					reversePath.push(newMove);
					move = newMove;
					continue;
				}
			}

			if(i >= 1 && !(mazeCells[i-1][j] instanceof WallCell) && (visited[i-1][j])){
				MovesToPortal newMove = new MovesToPortal(i-1, j, dist-1);
				if(IsCellInThePath(path, newMove)){
					visited[i-1][j] = false;
					reversePath.push(newMove);
					move = newMove;
					continue;
				}
			}
			
			if(j >= 1 && !(mazeCells[i][j-1] instanceof WallCell) && (visited[i][j-1])){
				MovesToPortal newMove = new MovesToPortal(i, j-1, dist-1);
				if(IsCellInThePath(path, newMove)){
					visited[i][j-1] = false;
					reversePath.push(newMove);
					move = newMove;
					continue;
				}
			}
			
			if(i < height-1 && !(mazeCells[i+1][j] instanceof WallCell) && (visited[i+1][j])){
				MovesToPortal newMove = new MovesToPortal(i+1, j, dist-1);
				if(IsCellInThePath(path, newMove)){
					visited[i+1][j] = false;
					reversePath.push(newMove);
					move = newMove;
					continue;
				}
			}	
		}
		
		PrintShortestPath(out, reversePath, ++noOfMoves);
	}
}
