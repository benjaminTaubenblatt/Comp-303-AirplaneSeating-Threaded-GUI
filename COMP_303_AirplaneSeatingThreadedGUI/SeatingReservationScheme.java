
package comp303hw2;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

/**
 *
 * @author benjamintaubenblatt
 * COMP 303 Assignment 2 
 * ID: 260626105
 */
public class SeatingReservationScheme extends Observable{
    private  int numRow = 0;
    private  int rowSeats = 0;
    private int[][] airplaneSeats;
    private final Lock lockReserve = new ReentrantLock();
    
    /**
     * This method returns a SeatingReservationScheme object aSeatingReservationScheme.
     * @param numRow The number of rows in the airplane.
     * @param rowSeats The number of airplaneSeats per row. 
     * 
     */
    public SeatingReservationScheme(int numRow, int rowSeats) {
        this.numRow = numRow;
        this.rowSeats = rowSeats;
        airplaneSeats = new int[numRow][rowSeats];
        
        for (int i = 0; i <= numRow - 1; i++) { //fill our 2D array with initial zero 
            for (int j = 0; j <= rowSeats - 1; j++) {
                airplaneSeats[i][j] = 0;
            }
        }
             
    }
    
    /**
     * This method checks to see if a seat is a valid seat on our airplane.
     * @param row The row of the seat to be verified.
     * @param column The column of the seat to be verified. 
     * @return Returns a boolean set to true if the specified seat is valid
     *         and false otherwise.
     */
    private boolean isValidSeat(int row, int seat) {
            return ((0 <= row) && (row <= numRow - 1) && (0 <= seat) && (seat <= rowSeats - 1));
    }
    /**
     * This method checks to see if the seat selected is taken or not. 
     * @param row The row of the seat. 
     * @param seat The column of the seat.
     * @return This method returns a boolean set to true if the seat is available. 
     */
    private boolean isSeatAvailable(int row, int seat) {
       
        if (isValidSeat(row, seat)) {
            return (airplaneSeats[row][seat] == 0);
        } else {
            throw new IllegalArgumentException("Invalid seat!");              
        }
       
    
    }
    /**
     * This method checks to see if a requested seat reservation is taken
     * then locks on to reserve the seat. 
     * @param row The number of rows.
     * @param col The number of columns.
     * @param reserverID The thread ID. 
     */
    public void reserveSeat(int row, int col, int reserverID) {
        row = row-1; //for user input column 1-4 row 1-50
        col = col-1; 
        boolean reservationsUpdated = false;       
        lockReserve.lock();
        try {
            if (isSeatAvailable(row,col)) {
                airplaneSeats[row][col] = reserverID;
                reservationsUpdated = true;
                
            } else {
                throw new IllegalArgumentException("The selected seat has already been taken!");
            }
        } finally {
            lockReserve.unlock(); //releases the lock after we attempt a reservation
        }
        
        if (reservationsUpdated) {
            setChanged(); //Raises a flag saying that something has changed
            notifyObservers(); //Notify the observer of the change 
        }
    }
    
    /**
     * This method returns the number of rows. 
     * @return Returns the number of rows. 
     */
    public int getNumRows() {
        int numRow = this.numRow;
        return numRow;
    }
    /**
     * This method returns the seats per row
     * @return Returns the number of seats per row. 
     */
    public int getRowSeats() {
        int seatsProw = this.rowSeats;
        return seatsProw;
    }
   
    /**
     * This method clones and returns our 2D array of airplaneSeats.
     * @return Returns a aClonedArray 2D array.
     */
    public int[][] getSeatings(){
        
        int[][] aClonedArray = new int[numRow][];
        lockReserve.lock();
        
        try {
            
            for(int i = 0; i<numRow; i++){
            aClonedArray[i] = airplaneSeats[i].clone(); 
            
            }
        } finally {
            
            lockReserve.unlock();
            
        }
        
        return aClonedArray; 
    }
   
    
}
