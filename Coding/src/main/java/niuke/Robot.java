package niuke;

import java.util.Scanner;

public class Robot {

    public static int getDirection(String direction){
        switch (direction){
            case "EAST":
                return 0;
            case "SOUTH":
                return 1;
            case "WEST":
                return 2;
            case "NORTH":
                return 3;
            default:
                return -1;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int startX,startY;
        String startD;

        int endX,endY;
        String endD;

        String line = scanner.nextLine();
        String tokens[] = line.split(" ");
        startX = Integer.parseInt(tokens[0]);
        startY = Integer.parseInt(tokens[1]);
        startD = tokens[2];

        line = scanner.nextLine();
        tokens = line.split(" ");
        endX = Integer.parseInt(tokens[0]);
        endY = Integer.parseInt(tokens[1]);
        endD = tokens[2];

        line = scanner.nextLine();
        tokens = line.split(" ");
        int row = Integer.parseInt(tokens[0]);
        int col = Integer.parseInt(tokens[1]);
        int map[][] = new int[row][col];

        for (int i = 0; i < row; i++) {
            line = scanner.nextLine();
            tokens = line.split(" ");
            for (int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(tokens[j]);
            }
        }



    }

    

}
