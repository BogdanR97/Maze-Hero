//package magicMaze;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clasa este folosita pentru scrierea datelor
 * in fisierul de output corespunzator
 * @author bogdan
 *
 */
public class Output {
    
    private PrintWriter pw;
    
    /**
     * @param filename numele fisierului de output
     */
    public Output(String filename) {
        try {
            pw = new PrintWriter(new File(filename));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Output.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @param text textul ce urmeaza sa fie scris in fisierul output
     */
    public void print(String text) {
        pw.println(text);
        pw.flush();
    }
    
    /**
     *inchide fisireul de output 
     */
    public void close() {
        pw.close();
    }
}
