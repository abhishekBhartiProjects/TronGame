import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {
    private static String UP = "UP";
    private static String DOWN = "DOWN";
    private static String LEFT = "LEFT";
    private static String RIGHT = "RIGHT";
    private static int xMax = 29, xMin = 0;
    private static int yMax = 19, yMin = 0;
    private static String movingDirection = "";
    
    
    
    private static HashMap<String, Boolean> pathtraced = new HashMap();
    private static int totalPlayers, myNumber;

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        
        // game loop
        while (true) {
            int N = in.nextInt(); // total number of players (2 to 4).
            int P = in.nextInt(); // your player number (0 to 3).
            int myX=0, myY=0;
            totalPlayers = N;
            myNumber = P;
            for (int i = 0; i < N; i++) {
                int X0 = in.nextInt(); // starting X coordinate of lightcycle (or -1)
                int Y0 = in.nextInt(); // starting Y coordinate of lightcycle (or -1)
                int X1 = in.nextInt(); // starting X coordinate of lightcycle (can be the same as X0 if you play before this player)
                int Y1 = in.nextInt(); // starting Y coordinate of lightcycle (can be the same as Y0 if you play before this player)
            
                pathtraced.put(i+"_"+X1+"_"+Y1, true);
                if(i == P){
                    myX = X1;
                    myY = Y1;
                }
            }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");
            String output = getOutput(pathtraced, myX, myY);
            movingDirection = output;
            System.out.println(output); // A single line with UP, DOWN, LEFT or RIGHT
        }
    }
    
    private static String getOutput(HashMap<String, Boolean> pathtraced, int myX, int myY){
        if(myX == xMax || myX == xMin){
            if(isTopAvailable(pathtraced, myX, myY, 1)){
                return UP;
            } else if(isDownAvailable(pathtraced, myX, myY, 1)){
                return DOWN;
            }  else if(isLeftAvailable(pathtraced, myX, myY, 1)){
                return LEFT;
            } else if (isRightAvailable(pathtraced, myX, myY, 1)){
                return RIGHT;
            }
        }
        
        if(myY == yMax || myY == yMin){
            if(isLeftAvailable(pathtraced, myX, myY, 1)){
                return LEFT;
            } else if (isRightAvailable(pathtraced, myX, myY, 1)){
                return RIGHT;
            }  else if (isTopAvailable(pathtraced, myX, myY, 1)){
                return UP;
            }  else if(isDownAvailable(pathtraced, myX, myY, 1)){
                return DOWN;
            }  
        }
        
        if(isLeftAvailable(pathtraced, myX, myY, 1)){
            return LEFT;
        } else if (isRightAvailable(pathtraced, myX, myY, 1)){
            return RIGHT;
        }  else if (isTopAvailable(pathtraced, myX, myY, 1)){
            return UP;
        }  else if(isDownAvailable(pathtraced, myX, myY, 1)){
            return DOWN;
        }  
        
        
        return "RIGHT";    
    }
    
    private static boolean isTopAvailable(HashMap<String, Boolean> pathtraced, int myX, int myY, int steps){
        for(int i=0; i< totalPlayers; i++){
            if(pathtraced.containsKey(i+"_"+myX+"_"+(myY-steps))){
                return false;    
            }    
        }
        
        if(myY == yMin){
            return false;    
        }
        
        return true;
    }
    
    private static boolean isDownAvailable(HashMap<String, Boolean> pathtraced, int myX, int myY, int steps){
        for(int i=0; i< totalPlayers; i++){
            if(pathtraced.containsKey(i+"_"+myX+"_"+(myY+steps))){
                return false;    
            }    
        }
        
        if(myY == yMax){
            return false;    
        }
        
        return true;
    }
    
    private static boolean isLeftAvailable(HashMap<String, Boolean> pathtraced, int myX, int myY, int steps){
        for(int i=0; i< totalPlayers; i++){
            if(pathtraced.containsKey(i+"_"+(myX-steps)+"_"+(myY))){
                return false;    
            }    
        }
        
        if(myX == xMin){
            return false;    
        }
        
        return true;
    }
    
    private static boolean isRightAvailable(HashMap<String, Boolean> pathtraced, int myX, int myY, int steps){
        for(int i=0; i< totalPlayers; i++){
            if(pathtraced.containsKey(i+"_"+(myX+steps)+"_"+(myY))){
                return false;    
            }    
        }
        
        if(myX == xMax){
            return false;    
        }
        
        return true;
    }
    
    private static String decideDirection(HashMap<String, Boolean> pathtraced, int myX, int myY){
        
        int availableRight = 0, availableLeft = 0, availableUp = 0, availableDown = 0;
        
        return "";
        
    }
    
      
}





















