
import java.util.Scanner;

public class Game {
    private Board boardHelper;
    private Scanner input;
    private AI aiHelper;

    public Game() {
        boardHelper = new Board();
        input = new Scanner(System.in);
        aiHelper = new AI();
    }

    public void startGame() {
        int choice;

        System.out.println("Welcome to Tic Tac Toe");
        System.out.println("1. 2 Player");
        System.out.println("2. 1 Player vs AI");
        System.out.print("Enter your choice: ");
        choice = input.nextInt();

        if (choice == 1) {
            playTwoPlayer();
        } else if (choice == 2) {
            playOnePlayer();
        } else {
            System.out.println("Invalid choice. Starting 2 player game.");
            playTwoPlayer();
        }
    }

    public void playTwoPlayer() {
        char[][] board = boardHelper.createBoard();
        String player1Name;
        String player2Name;
        char player1Symbol;
        char player2Symbol;
        char currentSymbol;
        String currentName;
        boolean gameOver = false;

        System.out.print("Enter Player 1 name: ");
        player1Name = input.next();

        System.out.print("Enter Player 2 name: ");
        player2Name = input.next();

        System.out.print(player1Name + ", choose X or O: ");
        player1Symbol = Character.toUpperCase(input.next().charAt(0));

        while (player1Symbol != 'X' && player1Symbol != 'O') {
            System.out.print("Invalid symbol. Choose X or O: ");
            player1Symbol = Character.toUpperCase(input.next().charAt(0));
        }

        if (player1Symbol == 'X') {
            player2Symbol = 'O';
            currentSymbol = player1Symbol;
            currentName = player1Name;
        } else {
            player2Symbol = 'X';
            currentSymbol = player2Symbol;
            currentName = player2Name;
        }

        boardHelper.printBoard(board);

        while (!gameOver) {
            System.out.println(currentName + "'s turn (" + currentSymbol + ")");
            playerTurn(board, currentSymbol);
            boardHelper.printBoard(board);

            if (boardHelper.checkWin(board, currentSymbol)) {
                System.out.println(currentName + " wins!");
                gameOver = true;
            } else if (boardHelper.isBoardFull(board)) {
                System.out.println("It is a draw!");
                gameOver = true;
            } else {
                if (currentSymbol == player1Symbol) {
                    currentSymbol = player2Symbol;
                    currentName = player2Name;
                } else {
                    currentSymbol = player1Symbol;
                    currentName = player1Name;
                }
            }
        }
    }

    public void playOnePlayer() {
        char[][] board = boardHelper.createBoard();
        String humanName;
        char humanSymbol;
        char aiSymbol;
        char currentSymbol;
        boolean gameOver = false;

        System.out.print("Enter your name: ");
        humanName = input.next();

        System.out.print(humanName + ", choose X or O: ");
        humanSymbol = Character.toUpperCase(input.next().charAt(0));

        while (humanSymbol != 'X' && humanSymbol != 'O') {
            System.out.print("Invalid symbol. Choose X or O: ");
            humanSymbol = Character.toUpperCase(input.next().charAt(0));
        }

        if (humanSymbol == 'X') {
            aiSymbol = 'O';
            currentSymbol = 'X';
        } else {
            aiSymbol = 'X';
            currentSymbol = 'X';
        }

        boardHelper.printBoard(board);

        while (!gameOver) {
            if (currentSymbol == humanSymbol) {
                System.out.println(humanName + "'s turn (" + humanSymbol + ")");
                playerTurn(board, humanSymbol);
            } else {
                System.out.println("Computer's turn (" + aiSymbol + ")");
                aiTurn(board, aiSymbol, humanSymbol);
            }

            boardHelper.printBoard(board);

            if (boardHelper.checkWin(board, currentSymbol)) {
                if (currentSymbol == humanSymbol) {
                    System.out.println(humanName + " wins!");
                } else {
                    System.out.println("Computer wins!");
                }
                gameOver = true;
            } else if (boardHelper.isBoardFull(board)) {
                System.out.println("It is a draw!");
                gameOver = true;
            } else {
                if (currentSymbol == 'X') {
                    currentSymbol = 'O';
                } else {
                    currentSymbol = 'X';
                }
            }
        }
    }

    public void playerTurn(char[][] board, char symbol) {
        int row;
        int col;
        boolean moved = false;

        while (!moved) {
            System.out.print("Enter row (0-2): ");
            row = input.nextInt();
            System.out.print("Enter column (0-2): ");
            col = input.nextInt();

            if (boardHelper.isValidMove(board, row, col)) {
                boardHelper.makeMove(board, row, col, symbol);
                moved = true;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    public void aiTurn(char[][] board, char aiSymbol, char humanSymbol) {
        int[] move = aiHelper.returnBestMove(board, aiSymbol, humanSymbol);
        if (move[0] != -1 && move[1] != -1) {
            boardHelper.makeMove(board, move[0], move[1], aiSymbol);
        }
    }
}
