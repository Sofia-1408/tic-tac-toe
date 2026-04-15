/**
 * Names & Student Numbers:
 * 1. Akinwunmi Eludoyin – 101567797
 * 2. Blossom Babalola – 101606071
 * 3. Sofia Janik – 101573681
 * */

public class AI {
    //lets the class use methods from Board.java
    private Board boardHelper;

    //constructor
    public AI() {
        boardHelper = new Board();
    }

    //returns the best move
    public int[] returnBestMove(char[][] board, char aiSymbol, char humanSymbol) {
        int bestScore = Integer.MIN_VALUE;
        //default in case no move is found
        int[] bestMove = {-1, -1};
        //check every position
        for (int rw = 0; rw < 3; rw++) {
            for (int cl = 0; cl < 3; cl++) {
                //avoid illegal moves
                if (boardHelper.isValidMove(board, rw, cl)) {
                    //check the move by simulating the rest of the game
                    board[rw][cl] = aiSymbol;
                    int score = miniMax(board, false, aiSymbol, humanSymbol);
                    board[rw][cl] = ' ';
                    //save the best move
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = rw;
                        bestMove[1] = cl;
                    }
                }
            }
        }
        return bestMove;
    }

    private int miniMax(char[][] board, boolean isMaxing, char aiSymbol, char humanSymbol) {
        //win
        if (boardHelper.checkWin(board, aiSymbol)) {
            return 1;
        }
        //lose
        if (boardHelper.checkWin(board, humanSymbol)) {
            return -1;
        }
        //draw
        if (boardHelper.isBoardFull(board)) {
            return 0;
        }
        //AI turn maximize the score
        if (isMaxing) {
            int bestScore = Integer.MIN_VALUE;
            //try ever possible move
            for (int rw = 0; rw < 3; rw++) {
                for (int cl = 0; cl < 3; cl++) {
                    if (boardHelper.isValidMove(board, rw, cl)) {
                        //simulate AI move and evaluate results
                        board[rw][cl] = aiSymbol;
                        int score = miniMax(board, false, aiSymbol, humanSymbol);
                        board[rw][cl] = ' ';
                        //save the highest score
                        if (score > bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;
        }
        else { //human turn minimize AI's score
            int bestScore = Integer.MAX_VALUE;
            //try ever possible move
            for (int rw = 0; rw < 3; rw++) {
                for (int cl = 0; cl < 3; cl++) {
                    if (boardHelper.isValidMove(board, rw, cl)) {
                        //simulate human move and evalute results
                        board[rw][cl] = humanSymbol;
                        int score = miniMax(board, true, aiSymbol, humanSymbol);
                        board[rw][cl] = ' ';
                        //save the highest score
                        if (score < bestScore) {
                            bestScore = score;
                        }
                    }
                }
            }
            return bestScore;
        }
    }
}