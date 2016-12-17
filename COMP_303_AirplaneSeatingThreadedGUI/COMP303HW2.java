
package comp303hw2;

import java.util.Arrays;

/**
 * @author benjamintaubenblatt
 * COMP 303 Assignment 2 
 * ID: 260626105
 */
public class COMP303HW2 {

    /**
     * This is the main method. 
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SeatingReservationScheme yourSeating = new SeatingReservationScheme(50,4);
        GUIAirplaneSeating viewMe = new GUIAirplaneSeating(yourSeating);
        
        yourSeating.addObserver(viewMe); //Informs the model that when a change is made, update 
        
        viewMe.setVisible(true); //Allows GUI to be visible
        
        Runnable r1 = new automationAgent(1,yourSeating); //automating our threads
        Runnable r2 = new automationAgent(2,yourSeating);
        
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2); 
         
        t1.start();
        t2.start();         
    }
    
}
