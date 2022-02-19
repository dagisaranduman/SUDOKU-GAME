import java.util.Random;

public class SudokuSolver {
    private static boolean check(int [][] board, int number, int x, int y) { //satÄ±r sÃ¼tun 3x3
        int xbase = x / 3;
        int ybase = y / 3;
        if(board[x][y] != 0) {
            return false;
        }
        for(int i = 0; i < 9 ; i++) {
            if(board[x][i] == number)
                return false;
        }

        for(int i = 0; i < 9 ; i++) {
            if(board[i][y] == number)
                return false;
        }
        for(int i = 3*xbase ; i < 3*(xbase+1) ; i++) {
            for(int j = 3*ybase ; j < 3*(ybase+1) ; j++) {
                if(board[i][j] == number)
                    return false;
            }
        }
        return true;
    }

    public static int[][] solve(int[][] board){ //

        int copyBoard[][] = new int[9][9];
        for(int i = 0 ; i < 9 ; i++) {
            for(int j = 0 ; j < 9 ; j++) {
                copyBoard[i][j] = board[i][j];
            }
        }
        for(int i = 0 ; i < 9 ; i++) {
            int checkCount = 0;
            boolean out = false;
            for(int j = 0 ; j < 9 ; j++) {
                checkCount = 0;
                if(board[i][j] == 0) {
                    while(true) {
                        checkCount++; //
                        if(checkCount == 100) {
                            for(int m = 0 ; m < 9 ; m++) {
                                for(int n = 0 ; n < 9 ; n++) {
                                    board[m][n] = copyBoard[m][n];
                                }
                            }
                            checkCount = 0;
                            i = 0;
                            j = 0;
                            out = true;
                            break;
                        }
                        Random rand = new Random();
                        int number = rand.nextInt(9) + 1;
                        if(check(board, number,i,j)) {
                            board[i][j] = number;
                            break;
                        }
                    }
                }
                if(out)
                    break;
            }
            if(out)
                i = -1;
        }
        return board;
    }

}
