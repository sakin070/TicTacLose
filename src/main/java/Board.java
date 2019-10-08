import java.util.ArrayList;
import java.util.Scanner;

public class Board {
    private String[][] board;
    private ArrayList<String> availableMoves;
    private int moves;
    private String lastX = "";

    private Board(){
        this.board = new String[3][3];
        availableMoves = new ArrayList<String>();
        for (int i = 0;i<3;i++){
            for(int j =0;j<3;j++){
                availableMoves.add(Integer.toString(i)+Integer.toString(j));
                board[i][j] = "-";
            }
        }
    }

    private boolean playX(String point){
        if(availableMoves.contains(point) ){
            lastX = point;
            board[Character.getNumericValue(point.charAt(0))][Character.getNumericValue(point.charAt(1))] = "X";
            availableMoves.remove(point);
            moves++;
            return true;
        }
        else{
            System.out.println("coordinates occupied pick another");
            return false;
        }
    }

    private String block(String s){
        if(board[0][0].equals(s)&&board[0][0].equals(board[1][0]) && board[2][0].equals("-")){
            return "20";
        }
        else if(board[0][0].equals(s)&&board[0][0].equals(board[2][0]) && board[1][0].equals("-")){
            return "10";
        }
        else if(board[1][0].equals(s)&&board[1][0].equals(board[2][0]) && board[0][0].equals("-")){
            return "00";
        }
        else if(board[0][0].equals(s)&&board[0][0].equals(board[1][1]) && board[2][2].equals("-")){
            return "22";
        }
        else if(board[0][0].equals(s)&&board[0][0].equals(board[2][2]) && board[1][1].equals("-")){
            return "11";
        }
        else if(board[1][1].equals(s)&&board[1][1].equals(board[2][2]) && board[0][0].equals("-")){
            return "00";
        }
        else if(board[0][0].equals(s)&&board[0][0].equals(board[0][1]) && board[0][2].equals("-")){
            return "02";
        }
        else if(board[0][0].equals(s)&&board[0][0].equals(board[0][2]) && board[0][1].equals("-")){
            return "01";
        }
        else if(board[0][1].equals(s)&&board[0][1].equals(board[0][2]) && board[0][0].equals("-")){
            return "00";
        }
        else if(board[0][1].equals(s)&&board[0][1].equals(board[1][1]) && board[2][1].equals("-")){
            return "21";
        }
        else if(board[0][1].equals(s)&&board[0][1].equals(board[2][1]) && board[1][1].equals("-")){
            return "11";
        }
        else if(board[1][1].equals(s)&&board[1][1].equals(board[2][1]) && board[0][1].equals("-")){
            return "01";
        }
        else if(board[1][0].equals(s)&&board[1][0].equals(board[1][1]) && board[1][2].equals("-")){
            return "12";
        }
        else if(board[1][0].equals(s)&&board[1][0].equals(board[1][2]) && board[1][1].equals("-")){
            return "11";
        }
        else if(board[1][1].equals(s)&&board[1][1].equals(board[1][2]) && board[1][0].equals("-")){
            return "10";
        }
        else if(board[0][2].equals(s)&&board[0][2].equals(board[1][1]) && board[2][0].equals("-")){
            return "20";
        }
        else if(board[0][2].equals(s)&&board[0][2].equals(board[2][0]) && board[1][1].equals("-")){
            return "11";
        }
        else if(board[1][1].equals(s)&&board[1][1].equals(board[2][0]) && board[0][2].equals("-")){
            return "02";
        }
        else if(board[2][0].equals(s)&&board[2][0].equals(board[2][1]) && board[2][2].equals("-")){
            return "22";
        }
        else if(board[2][0].equals(s)&&board[2][0].equals(board[2][2]) && board[2][1].equals("-")){
            return "21";
        }
        else if(board[2][1].equals(s)&&board[2][1].equals(board[2][2]) && board[2][0].equals("-")){
            return "20";
        }
        else if(board[0][2].equals(s)&&board[0][2].equals(board[1][2]) && board[2][2].equals("-")){
            return "22";
        }
        else if(board[0][2].equals(s)&&board[0][2].equals(board[2][2]) && board[1][2].equals("-")){
            return "12";
        }
        else if(board[1][2].equals(s)&&board[1][2].equals(board[2][2]) && board[0][2].equals("-")){
            return "02";
        }
        return "";
    }
    private String playConner(){
        String oPlay = "";
        if (availableMoves.contains("00")){
            oPlay = "00";
        }
        else if (availableMoves.contains("02")){
            oPlay = "02";
        }
        else if (availableMoves.contains("20")){
            oPlay = "20";
        }
        else if (availableMoves.contains("22")){
            oPlay = "22";
        }
        return oPlay;
    }
    private boolean playO(){
        String oPlay = "";
        if(moves == 1){
            oPlay = getOpposite(lastX);
            if(oPlay.equals("")){
                oPlay = getSide(lastX);
            }
            if(oPlay.equals("")){
                oPlay = playConner();
            }
            // to be tested for bugs
            System.out.println("This is the first move of O");
        }
        else if (moves == 3){
            oPlay = block("X") ;
            if(oPlay.equals("") && availableMoves.contains("11")){
                oPlay = "11";
            }
            else if (oPlay.equals("")){
                oPlay = playConner();
            }


        }
        else if (moves >3){
            oPlay = block("O") ;
            if(oPlay.equals("")){
                oPlay = block("X") ;
            }
            if(oPlay.equals("") && availableMoves.contains("11")){
                oPlay = "11";
            }
            if(oPlay.equals("")){
                oPlay = playConner();
            }
            if(oPlay.equals("")){
                oPlay = availableMoves.get(0);
            }
        }
        if(availableMoves.contains(oPlay)){
            board[Character.getNumericValue(oPlay.charAt(0))][Character.getNumericValue(oPlay.charAt(1))] = "O";
            availableMoves.remove(oPlay);
            moves++;
            return true;
        }
        else{
            System.out.println("O failed to pick a coordinates occupied pick another");
            return false;
         }
    }

    private String getOpposite(String point){
        if(point.equals("00")){
            return "22";
        }
        else if (point.equals("22")){
            return "00";
        }
        else if (point.equals("02")){
            return "20";
        }
        else if(point.equals("20")){
            return "02";
        }
        return "";
    }

    private String getSide(String point){
        if(point.equals("10")){
            if(availableMoves.contains("00")){
                return "00";
            }
            return "20";
        }
        else if(point.equals("21")){
            if(availableMoves.contains("20")){
                return "20";
            }
            return "22";
        }
        else if(point.equals("12")){
            if(availableMoves.contains("02")){
                return "02";
            }
            return "22";
        }
        else if(point.equals("01")){
            if(availableMoves.contains("00")){
                return "02";
            }
            return "22";
        }
        return "";
    }

    private boolean gameComplete(){
        if (moves == 9){
            return true;
        }
        else if(!board[0][0].equals("-")&&board[0][0].equals(board[0][1]) && board[0][0].equals(board[0][2])){
            return true;
        }
        else if(!board[0][0].equals("-")&&board[0][0].equals(board[1][0]) && board[0][0].equals(board[2][0])){
            return true;
        }
        else if(!board[0][0].equals("-")&&board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2])){
            return true;
        }
        else if(!board[2][0].equals("-")&&board[2][0].equals(board[2][1]) && board[2][0].equals(board[2][2])){
            return true;
        }
        else if(!board[0][2].equals("-")&&board[0][2].equals(board[1][2]) && board[0][2].equals(board[2][2])){
            return true;
        }
        else if(!board[0][2].equals("-")&&board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0])){
            return true;
        }
        else if(!board[0][1].equals("-")&&board[0][1].equals(board[1][1]) && board[0][1].equals(board[2][1])){
            return true;
        }
        return !board[1][0].equals("-")&&board[1][0].equals(board[1][1]) && board[1][0].equals(board[1][2]);
    }

    private void printBoard(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args){
        Board board = new Board();
        board.printBoard();
        while(!board.gameComplete()){
            boolean xPlayed = false;
            while(!xPlayed){
                System.out.println("Player X enter your coordinates of choice");
                Scanner scanner = new Scanner(System.in);
                String point = scanner.nextLine();
                xPlayed = board.playX(point);
            }

            board.printBoard();

            boolean oPlayed = false;
            while(!oPlayed && !board.gameComplete()){
                oPlayed = board.playO();
                System.out.println("Computer has played");
                System.out.println();
                board.printBoard();
            }

        }
        System.out.println("Game over Player X Lost");
//        System.out.println();
//        board.playX(0,0);
//        board.playO(0,1);
//        board.printBoard();
//        System.out.println();
//
//
//        Scanner scanner = new Scanner(System.in);
//        String username = scanner.nextLine();
//        System.out.println("Your username is " + username);
    }

}
