package skeletonprogram;

public class SkeletonProgram {

    public static class Main {

        class Coordinate {

            int value;
        } 

        char board[][];
        String playerOneName;
        String playerTwoName;
        float playerOneScore;
        float playerTwoScore;
        int xCoord;
        int yCoord;
        boolean validMove;
        int noOfMoves;
        boolean gameHasBeenWon;
        boolean gameHasBeenDrawn;
        char currentSymbol;
        char startSymbol;
        char playerOneSymbol;
        char playerTwoSymbol;
        char answer;
        Console console = new Console();

        public Main() {
            board = new char[4][4];
            playerOneName = console.readLine("What is the name of player one? ");
            playerTwoName = console.readLine("What is the name of player two? ");
            playerOneScore = 0;
            playerTwoScore = 0;
            do { 
                playerOneSymbol = console.readChar((playerOneName
                        + " what symbol do you wish to use X or O? "));
                if (playerOneSymbol != 'X' && playerOneSymbol != 'O') {
                    console.println("Symbol to play must be uppercase X or O");
                } 
            } while (playerOneSymbol != 'X' && playerOneSymbol != 'O');
            if (playerOneSymbol == 'X') {
                playerTwoSymbol = 'O';
            } else {
                playerTwoSymbol = 'X';
            } 
            startSymbol = 'X';
            do { 
                noOfMoves = 0;
                gameHasBeenDrawn = false;
                gameHasBeenWon = false;
                clearBoard(board);
                console.println();
                displayBoard(board);
                if (startSymbol == playerOneSymbol) {
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
                    board[xCoord][yCoord] = currentSymbol;
                    displayBoard(board);
                    gameHasBeenWon = checkXOrOHasWon(board);
                    noOfMoves++;
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
                } while (!gameHasBeenWon && !gameHasBeenDrawn);
           
                if (gameHasBeenWon) {
                    if (playerOneSymbol == currentSymbol) {
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
                if (startSymbol == playerOneSymbol) {
                    startSymbol = playerTwoSymbol;
                } else {
                    startSymbol = playerOneSymbol;
                } 
                answer = console.readChar("Another game Y/N? ");
            } while (answer != 'N' && answer != 'n');
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
            return validMove;
        }  

        boolean checkXOrOHasWon(char[][] board) {
            boolean xOrOHasWon;
            int row;
            int column;
            xOrOHasWon = false;
            
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

