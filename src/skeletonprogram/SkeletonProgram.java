package skeletonprogram;

import java.util.ArrayList;

public class SkeletonProgram {
    

    public static class Main {

        public class Coordinate {

            int value;
        } 

        public static char board[][];
        public static String playerOneName;
        public static String playerTwoName;
        public static float playerOneScore;
        public static float playerTwoScore;
        public static int xCoord;
        public static int yCoord;
        public static boolean validMove;
        public static int noOfMoves;
        public static boolean gameHasBeenWon;
        public static boolean gameHasBeenDrawn;
        public static char currentSymbol;
        public static char startSymbol;
        public static char playerOneSymbol;
        public static char playerTwoSymbol;
        public static char answer;
        public static char uppersymbol;
        Console console = new Console();
        
        public Main() {
            board = new char[4][4];
            playerOneName = console.readLine("What is the name of player one? ");
            playerTwoName = console.readLine("What is the name of player two? ");
            playerOneScore = 0;
            playerTwoScore = 0;
            do { 
                boolean valid = false;
                while(!valid){
                try{
                
                playerOneSymbol = console.readChar((playerOneName
                        + " what symbol do you wish to use X or O? "));
                valid = true;
                }catch(Exception e){
                System.out.println("enter something");
                }
                }
                uppersymbol = Character.toUpperCase(playerOneSymbol);
                if (uppersymbol != 'X' && uppersymbol != 'O') {
                    console.println("Symbol to play must be uppercase X or O");
                } 
            } while (uppersymbol != 'X' && uppersymbol != 'O');
            if (uppersymbol == 'X') {
                playerTwoSymbol = 'O';
            } else {
                playerTwoSymbol = 'X';
            } 
            startSymbol = 'O';
            do { 
                noOfMoves = 0;
                gameHasBeenDrawn = false;
                gameHasBeenWon = false;
                clearBoard(board);
                console.println();
                displayBoard(board);
                if (startSymbol == uppersymbol) {
                    console.println(playerOneName + " starts playing " + startSymbol);
                } else {
                    console.println(playerTwoName + " starts playing " + startSymbol);
                } 
                console.println();
                currentSymbol = startSymbol;
                do {
                    do { 
                        Coordinate x = new Coordinate();
                        Coordinate y = new Coordinate();
                        getMoveCoordinates(x, y);
                        xCoord = x.value;
                        yCoord = y.value;
                        validMove = checkValidMove(xCoord, yCoord, board);
                        if (!validMove) {
                            console.println("Coordinates invalid, please try again");
                        }
                    } while (!validMove);
                    if(xCoord == 666){
                    twoGoes();
                     }
                    board[xCoord][yCoord] = currentSymbol;
                    displayBoard(board);
                    gameHasBeenWon = checkXOrOHasWon(board);
                    noOfMoves++;
                    ////////
                    if(xCoord == 666){
                       console.println("Player " + currentSymbol + ", Enter another coordinate"); 
                    }else{
                    ChangeSymbol();
                    }      
                     
                } while (!gameHasBeenWon && !gameHasBeenDrawn);
           
                if (gameHasBeenWon) {
                    if (uppersymbol == currentSymbol) {
                        console.println(playerOneName + " congratulations you win!");
                        playerOneScore++;
                    } else {
                        console.println(playerTwoName + " congratulations you win!");
                        playerTwoScore++;
                    }
                } else {
                    console.println("A draw this time!");
                } 
                console.println();
                console.println(playerOneName + " your score is: " + String.valueOf(playerOneScore));
                console.println(playerTwoName + " your score is: " + String.valueOf(playerTwoScore));
                console.println();
                if (startSymbol == uppersymbol) {
                    startSymbol = playerTwoSymbol;
                } else {
                    startSymbol = uppersymbol;
                } 
                answer = console.readChar("Another game Y/N? ");
            } while (answer != 'N' && answer != 'n');
        }

        public void twoGoes(){
            int goes = 0;
            while(goes != 2){
                
                Coordinate x = new Coordinate();
                Coordinate y = new Coordinate();
                getMoveCoordinates(x, y);
                xCoord = x.value;
                yCoord = y.value;
                validMove = checkValidMove(xCoord, yCoord, board);
                if (!validMove) {
                    console.println("Coordinates invalid, please try again");
                }
                board[xCoord][yCoord] = currentSymbol;
                displayBoard(board);
                gameHasBeenWon = checkXOrOHasWon(board);
                noOfMoves++;
                goes++;
            }
        }
        
        public static void ChangeSymbol(){
        if (!gameHasBeenWon) {
  
                        if (noOfMoves == 9) {
                            gameHasBeenDrawn = true;
                        } else {
                            if (currentSymbol == 'X') {
                                currentSymbol = 'O';
                            } else {
                                currentSymbol = 'X';
                            } 
                        }  
                                    }
    }
        
        void displayBoard(char[][] board) {
            int row;
            int column;
            console.println(" | 1 2 3 ");
            console.println("--+-------");
            for (row = 1; row <= 3; row++) {
                console.write(row + " | ");
                for (column = 1; column <= 3; column++) {
                    console.write(board[column][row] + " ");
                } 
                console.println();
            } 
        } 

        void clearBoard(char[][] board) {
            int row;
            int column;
            for (row = 1; row <= 3; row++) {
                for (column = 1; column <= 3; column++) {
                    board[column][row] = ' ';
                } 
            } 
        } 

        void getMoveCoordinates(Coordinate xCoordinate, Coordinate yCoordinate) {
            xCoordinate.value = console.readInteger("Enter x Coordinate: ");
            yCoordinate.value = console.readInteger("Enter y Coordinate: ");
            
        } 

        boolean checkValidMove(int xCoordinate, int yCoordinate, char[][] board) {
            boolean validMove;
            validMove = true;
            
            if (xCoordinate < 1 || xCoordinate > 3) {
                validMove = false;
            } 
            if(xCoordinate == 666){
                validMove = true;
            }
            else{
            if(board[xCoordinate][yCoordinate] == 'X'){
                console.println("invalid coordinates, choose another");
                validMove = false;
            }
            if(board[xCoordinate][yCoordinate] == 'O'){
                console.println("invalid coordinates, choose another");
                validMove = false;
            }}
            return validMove;
        }  

        boolean checkXOrOHasWon(char[][] board) {
            boolean xOrOHasWon;
            int row;
            int column;
            xOrOHasWon = false;
            if(board[1][1]==board[2][2]&&board[2][2]==board[3][3]&&board[2][2]!=' '){
            xOrOHasWon = true;
            }
            if(board[3][1]==board[2][2]&&board[2][2]==board[1][3]&&board[2][2]!=' '){
            xOrOHasWon = true;
            }
            for (column = 1; column <= 3; column++) {
                if (board[column][1] == board[column][2]
                        && board[column][2] == board[column][3]
                        && board[column][2] != ' ') {
                    xOrOHasWon = true;
                } 
            } 
            for (row = 1; row <= 3; row++) {
                if (board[1][row] == board[2][row]
                        && board[2][row] == board[3][row]
                        && board[2][row] != ' ') {
                    xOrOHasWon = true;
                } 
            } 
            return xOrOHasWon;
        } 
    }

    public static void main(String[] args) {
        new Main();
    }
}

