import java.util.Scanner;
import java.util.HashMap;



public class Main {
    public static void main(String[] args) {
        char[][] field = new char[10][10];

        HashMap<String, Integer> ships = new HashMap<>();
        ships.put("Aircraft Carrier", 5);
        ships.put("Battleship", 4);
        ships.put("Submarine", 3);
        ships.put("Cruiser", 3);
        ships.put("Destroyer", 2);

        
        Scanner scanner = new Scanner(System.in);


        fillField(field);
        printField(field);
        placeShips(scanner, field, ships);



        scanner.close();
    }

    public static void placeShips(Scanner scanner, char[][] field, HashMap<String, Integer> ships) {
        String[] shipsNames = {"Aircraft Carrier","Battleship","Submarine","Cruiser","Destroyer"};

        int shipPlaced = 0;
        while(true) {
            System.out.printf("\nEnter the coordinates of the %s (%d cells): ", shipsNames[shipPlaced], ships.get(shipsNames[shipPlaced]));
            String input = scanner.nextLine();
            String[] coordinates = input.split(" ");

            placeShip(coordinates[0], coordinates[1], field);
            printField(field);
            shipPlaced++;
            if(shipPlaced >= shipsNames.length) {
                break;
            }

        }
    }

    public static void placeShip(String firstCoordinate, String secondCoordinate,char[][] field) {

            if (firstCoordinate.charAt(0) == secondCoordinate.charAt(0)) {
                int row = firstCoordinate.charAt(0) - 'A';
                for (int col = Integer.parseInt(firstCoordinate.substring(1)) - 1; col <= Integer.parseInt(secondCoordinate.substring(1)) - 1; col++) {
                    field[row][col] = 'O';
                }
            } else if (Integer.parseInt(firstCoordinate.substring(1)) == Integer.parseInt(secondCoordinate.substring(1))) {
                int col = Integer.parseInt(firstCoordinate.substring(1)) - 1;
                for (int row = firstCoordinate.charAt(0) - 'A'; row <= secondCoordinate.charAt(0) - 'A'; row++) {
                    field[row][col] = 'O';
                }
            } else {
                System.out.println("Error!");
            }


    }

    public static void fillField(char[][] field) {
        for(int row = 0; row < field.length; row++) {
            for(int col = 0; col < field[0].length; col++) {
                field[row][col] = '~';
            }
        }
    }

    public static void printField(char[][] field) {
        System.out.println();
        System.out.print("  ");
        for(int i = 1; i <= field.length; i++) {
            System.out.printf("%d ", i);
        }
        System.out.println();

        for(int row = 0; row < field.length; row++) {
            System.out.printf("%c ", 65 + row);
            for(int col = 0; col < field[0].length; col++) {
                System.out.printf("%c ", field[row][col]);
            }
            System.out.println();
        }
    }
}