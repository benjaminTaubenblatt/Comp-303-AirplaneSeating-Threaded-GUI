
package comp303hw2;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author benjamintaubenblatt
 * COMP 303 Assignment 2 
 * ID: 260626105
 */
public class automationAgent implements Runnable{
    private int ID = 0;
    private SeatingReservationScheme controlla; 
    
    /**
     * This method creates a new automationAgent to automate seat reservations.
     * @param ID The thread ID. 
     * @param mySeats The SeatingReservationScheme.
     * 
     */
    public automationAgent(int ID, SeatingReservationScheme mySeats)
    {
        
        if(ID == 0){
            throw new IllegalArgumentException(); 
        }else{
            this.ID = ID;
            this.controlla = mySeats; 
        }
    }
    
    
    /**
     * This method takes no parameters but generates random numbers row: 0-49 
     * and column: 0-3 and attempts seat reservations with those random numbers
     * and the thread ID.
     * 
     */
    @Override
    public void run(){
        
        int n;
        int m;
        Random rand = new Random();
        Random rend = new Random();
        int i = 0; 
        
        while(i != 200){ //generating random numbers for our automated seating
            n = rand.nextInt(50); 
            m = rend.nextInt(4);
            try{
            controlla.reserveSeat(n,m,ID); //reserve seats
            } catch (IllegalArgumentException e) {
                
            }
            
            i++;
            try {
                Thread.sleep(800); //delay thread speed 
            } catch (InterruptedException ex) { //we slow down the automated thread so we can make a reservation
                Logger.getLogger(automationAgent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * This method returns the ID of our thread.
     * 
     * @return The ID of our thread. 
     */
    public int getID(){
        return ID;
    }
}
    