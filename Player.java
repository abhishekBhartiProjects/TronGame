import java.util.*;
import java.io.*;
import java.math.*;
import java.util.Arrays; 

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
            // if(isTopAvailable(pathtraced, myX, myY, 1)){
            //     return UP;
            // } else if(isDownAvailable(pathtraced, myX, myY, 1)){
            //     return DOWN;
            // }  else if(isLeftAvailable(pathtraced, myX, myY, 1)){
            //     return LEFT;
            // } else if (isRightAvailable(pathtraced, myX, myY, 1)){
            //     return RIGHT;
            // }
            
            return decideDirection(pathtraced, myX, myY);
        }
        
        if(myY == yMax || myY == yMin){
            // if(isLeftAvailable(pathtraced, myX, myY, 1)){
            //     return LEFT;
            // } else if (isRightAvailable(pathtraced, myX, myY, 1)){
            //     return RIGHT;
            // }  else if (isTopAvailable(pathtraced, myX, myY, 1)){
            //     return UP;
            // }  else if(isDownAvailable(pathtraced, myX, myY, 1)){
            //     return DOWN;
            // } 
            
            return decideDirection(pathtraced, myX, myY);
        }
        
        // if(isLeftAvailable(pathtraced, myX, myY, 1)){
        //     return LEFT;
        // } else if (isRightAvailable(pathtraced, myX, myY, 1)){
        //     return RIGHT;
        // }  else if (isTopAvailable(pathtraced, myX, myY, 1)){
        //     return UP;
        // }  else if(isDownAvailable(pathtraced, myX, myY, 1)){
        //     return DOWN;
        // }  
        
        return decideDirection(pathtraced, myX, myY);
        
        
    }
    
    private static boolean isTopAvailable(HashMap<String, Boolean> pathtraced, int myX, int myY, int steps){
        for(int i=0; i< totalPlayers; i++){
            if(pathtraced.containsKey(i+"_"+myX+"_"+(myY-steps))){
                return false;    
            }    
        }
        
        if(myY-steps+1 == yMin){
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
        
        if(myY+steps-1 == yMax){
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
        
        if(myX-steps+1 == xMin){
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
        
        if(myX+steps-1 == xMax){
            return false;    
        }
        
        return true;
    }
    
    private static String decideDirection(HashMap<String, Boolean> pathtraced, int myX, int myY){
        
        int availableRight = 0, availableLeft = 0, availableUp = 0, availableDown = 0;
        
        //availableUp
        for(int i = 1; i< yMax; i++){
            if(isTopAvailable(pathtraced, myX, myY, i)){
                availableUp = i;    
            } else{
                break;    
            }
        }
        
        //availableDown
        for(int i = 1; i< yMax; i++){
            if(isDownAvailable(pathtraced, myX, myY, i)){
                availableDown = i;    
            } else{
                break;    
            }
        }
        
        //availableLeft
        for(int i = 1; i< xMax; i++){
            if(isLeftAvailable(pathtraced, myX, myY, i)){
                availableLeft = i;    
            } else{
                break;    
            }
        }
        
        //availableRight
        for(int i = 1; i< xMax; i++){
            if(isRightAvailable(pathtraced, myX, myY, i)){
                availableRight = i;    
            } else{
                break;    
            }
        }
        
        String directionWithMaxSpace = RIGHT; //init value
        int arr[] = {availableRight, availableLeft, availableUp, availableDown}; 
        int max = Arrays.stream(arr).max().getAsInt();
        
        if(availableRight == max){
            directionWithMaxSpace = RIGHT;    
        } else if(availableLeft == max){
            directionWithMaxSpace = LEFT;    
        } else if(availableUp == max){
            directionWithMaxSpace = UP;    
        } else if(availableDown == max){
            directionWithMaxSpace = DOWN;    
        }
        
        System.err.println("R->"+availableRight+" L->"+availableLeft+" U->"+availableUp+" D->"+availableDown);
        System.err.println("Deciding for ("+myX+" , "+myY+") : " + directionWithMaxSpace);
        
        return directionWithMaxSpace;
        
    }
    
      
}




