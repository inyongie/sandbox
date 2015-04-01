package codejam;

/**
 * Created by ichung on 3/31/2015.
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.io.InputStreamReader;

class Ideone {

    public static void rotateBoard(Character[][] gameBoard, int boardLength) {
        // Rotation of 90 degrees
        // implement non-copy approach of rotation
        // O(n^2)
        // Is there better?

        if(boardLength == 0 || boardLength == 1) return;

        int quarterDimX = boardLength % 2 == 1 ? (boardLength/2) + 1 : boardLength/2;
        int quarterDimY = boardLength/2;

        for(int i=0; i<quarterDimY; i++) {
            for(int j=0; j<quarterDimX; j++) {
                char tempChar = gameBoard[j][i];
                int x1 = i; //Math.abs(i-(boardLength-1));
                int y1 = Math.abs(j-(boardLength-1));
                int x2 = y1; //Math.abs(y1-(boardLength-1));
                int y2 = Math.abs(x1-(boardLength-1));
                int x3 = y2; //Math.abs(y2-(boardLength-1));
                int y3 = Math.abs(x2-(boardLength-1));
                gameBoard[j][i] = gameBoard[x1][y1];
                gameBoard[x1][y1] = gameBoard[x2][y2];
                gameBoard[x2][y2] = gameBoard[x3][y3];
                gameBoard[x3][y3] = tempChar;
            }
        }
    }

    public static void applyGravity(Character[][] gameBoard, int boardLength) {
        // Current implementation idea:
        // insert non-'.'s into stack, pop stack and re-populate from back and fill rest with '.'
        // O(n^2)
        // Is there better?

        for(int j=0; j<boardLength; j++) {
            Stack<Character> tempStack = new Stack<Character>();
            for (int k = 0; k < boardLength; k++) {
                if(!gameBoard[j][k].equals('.')) {
                    tempStack.push(gameBoard[j][k]);
                }
            }

            for (int k = boardLength-1; k >= 0; k--) {
                if(!tempStack.isEmpty()) gameBoard[j][k] = tempStack.pop();
                else gameBoard[j][k] = '.';
            }
        }

    }

    public static int findLongestStreak(Character[][] gameBoard, int boardLength, char value) {
        int maximumStreak = 0;
        
        for(int j=0; j<boardLength; j++) {
            int counter = 0;
            // Find longest Vertical Streak
            for (int k = 0; k < boardLength; k++) {
                if(gameBoard[j][k].equals(value)) {
                    counter++;
                    if(k==boardLength-1) {
                        maximumStreak = Math.max(maximumStreak, counter);
                    }
                }
                else {
                    maximumStreak = Math.max(maximumStreak, counter);
                    counter = 0;
                }
            }
            
            // Find longest Horizontal Streak
            counter = 0;
            for (int k = 0; k < boardLength; k++) {
                if(gameBoard[k][j].equals(value)) {
                    counter++;
                    if(k==boardLength-1) {
                        maximumStreak = Math.max(maximumStreak, counter);
                    }
                }
                else {
                    maximumStreak = Math.max(maximumStreak, counter);
                    counter = 0;
                }
            }
        }

        // Find longest left -> right diagonal streak
        // y = x + y graph eq
        for(int j=(boardLength-1)*(-1); j<boardLength; j++) {
            int counter = 0;
            for (int k = 0; k < boardLength; k++) {
                if(k+j < 0) continue;
                if(k+j == boardLength || k == boardLength) {
                    maximumStreak = Math.max(maximumStreak, counter);
                    break;
                }
                if(gameBoard[k][k+j].equals(value)) {
                    counter++;
                    
                }
                else {
                    maximumStreak = Math.max(maximumStreak, counter);
                    counter = 0;
                }
            }
        }

        // Find longest right -> left diagonal streak
        // y = -x + y graph eq
        for(int j=0; j<((boardLength*2)); j++) {
            int counter = 0;
            for (int k = 0; k < boardLength; k++) {
                if((k*-1)+j < 0 || (k*-1)+j > boardLength-1 || (k*-1) > boardLength-1) continue;
                if((k*-1)+j == boardLength || k == boardLength) { 
                    maximumStreak = Math.max(maximumStreak, counter);
                    break;
                }
                if(gameBoard[k][(k*-1)+j].equals(value)) {
                    counter++;
                }
                else {
                    maximumStreak = Math.max(maximumStreak, counter);
                    counter = 0;
                }
            }
        }

        //return the max;
        return maximumStreak;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");

        BufferedReader in;
        try {
            // Uncomment appropriate input location
//            in = new BufferedReader(new FileReader("F:\\code\\rotate_test.txt"));
            // in = new BufferedReader(new FileReader("F:\\code\\rotate_A-small-practice.in"));
//            in = new BufferedReader(new FileReader("C:\\code\\welcome_C-large-practice.in"));
in = new BufferedReader(new InputStreamReader(System.in));

            String casesStr = in.readLine();
            int cases = Integer.parseInt(casesStr);

            for(int i = 1; i<=cases; i++) {
                String winner = "";
                String[] valueStr = in.readLine().split(" ");
                int dimSize = Integer.parseInt(valueStr[0]);
                int winStreak = Integer.parseInt(valueStr[1]);

                Character[][] gameBoard = new Character[dimSize][dimSize];

                // Populate game board
                for(int j=0; j<dimSize; j++) {
                    String tempLine = in.readLine();
//                    System.out.println(tempLine);
                    for(int k=0; k<dimSize; k++) {
                        gameBoard[k][j] = tempLine.charAt(k);
                    }
                }

                // Rotate game board
                rotateBoard(gameBoard, dimSize);


                // Apply gravity
                applyGravity(gameBoard, dimSize);


                // find winner
                int rLength = 0, bLength = 0;
                rLength = findLongestStreak(gameBoard, dimSize, 'R');
                bLength = findLongestStreak(gameBoard, dimSize, 'B');


                if(bLength >= winStreak && rLength >= winStreak) {
                    winner = "Both";
                } else if(bLength < winStreak && rLength < winStreak) {
                	winner = "Neither";
                } else if(bLength > rLength && bLength >= winStreak) {
                	winner = "Blue";
                } else if(rLength > bLength && rLength >= winStreak) {
                	winner = "Red";
                }
                
                System.out.println("Case #" + i + ": " + winner);
            }
            in.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

