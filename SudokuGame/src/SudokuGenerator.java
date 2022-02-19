import java.util.Random;

public class SudokuGenerator {

    private static int board[][] = new int[9][9];



    public int[][] getBoard() {

        return board;
    }

    public void setBoard(int[][] board) {
        for(int i = 0 ; i < 9 ; i++) {
            for(int j = 0 ; j < 9 ; j++) {
                board[i][j] = board[i][j];
            }
        }
    }

    public SudokuGenerator() {
        for(int i = 0 ; i < 9 ; i++) {
            for(int j = 0 ; j < 9 ; j++) {
                board[i][j] = 0;
            }
        }
    }



    private static boolean check(int number, int x, int y) {
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

    public static int[][] generate(int difficulty){
        int diffCount = 0;

        switch(difficulty) {
            case 1:  //kolay
                diffCount = 15;
                break;
            case 2:
                diffCount = 25;
                break;
            case 3:
                diffCount = 35;
                break;
            default:
                diffCount = 15;
        }



        Random rand = new Random();
        int debug = 0;
        for(int i = 0 ; i < 9 ; i++) {
            for(int j = 0 ; j < 9 ; j++) {
                int checkCount = 0;
                while(true) {
                    checkCount++;

                    if(checkCount == 100) {
                        for(int k = 0 ; k < 9 ; k++) {
                            for(int r = 0 ; r < 9 ; r++)
                                board[r][k] = 0;
                        }

                        checkCount = 0;
                        j = 0;
                        i = 0;
                    }
                    int number = rand.nextInt(9) + 1;
                    if(check(number,i,j)) { //check methodunu kullandik
                        board[i][j] = number;
                        break;
                    }
                }
            }
        }

        int checkCount = 0;
        while(checkCount < diffCount) {
            int randx = rand.nextInt(9);
            int randy = rand.nextInt(9);
            if(board[randx][randy] != 0) {
                checkCount++;
                board[randx][randy] = 0; //silinen hucreli haliyle boardu veriyor
            }
        }
        return board;
    }

}
