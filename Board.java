/**
 * Names & Student Numbers:
 * 1. Akinwunmi Eludoyin – 101567797
 * 2. Blossom Babalola – 101606071
 * 3. Sofia Janik – 101573681
 * */

public class Board {
    public char[][] createBoard() {
        char [][] board = new char[3][3];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = ' ';
            }
        }
        return board;
    };

    public void printBoard(char[][] board) {
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("  -----");
        }
    }

    public boolean isValidMove(char[][] board, int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) return false;
        return board[row][col] == ' ';
    }

    public void makeMove(char[][] board, int row, int col, char symbol) {
        if(isValidMove(board, row, col)) {
            board[row][col] = symbol;
        }
    }

    public boolean checkWin(char[][] board, char symbol) {
        // check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) return  true;
            if(board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) return true;
        }
        // diagonals
        if(board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) return true;
        if(board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) return true;
        return false;
    }

    public boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') return false;
            }
        }
        return true;
    }

}
