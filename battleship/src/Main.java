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

            if(getShipLength(coordinates) != ships.get(shipsNames[shipPlaced])) {
                System.out.print("Error!");
                continue;
            }

            if(!checkInput(coordinates)) {
                continue;
            }

            if(isShipTooClose(coordinates[0], coordinates[1], field)) {
                System.out.print("Error!");
                continue;
            }

            placeShip(coordinates[0], coordinates[1], field);
            printField(field);
            shipPlaced++;
            if(shipPlaced >= shipsNames.length) {
                break;
            }

        }
    }

    public static int getShipLength(String[] coordinates) {

        String firstCoordinate = coordinates[0];
        String secondCoordinate = coordinates[1];

        char firstCoordinateLetter = firstCoordinate.charAt(0);
        char secondCoordinateLetter = secondCoordinate.charAt(0);

        int firstCoordinateNumber = Integer.parseInt(firstCoordinate.substring(1));
        int secondCoordinateNumber = Integer.parseInt(secondCoordinate.substring(1));

        if(firstCoordinateNumber == secondCoordinateNumber) {
//            System.out.print(Math.abs(firstCoordinateLetter - secondCoordinateLetter) + 1);
            return Math.abs(firstCoordinateLetter - secondCoordinateLetter) + 1;
        } else if(firstCoordinateLetter == secondCoordinateLetter) {
//            System.out.print(Math.abs(firstCoordinateNumber - secondCoordinateNumber) + 1);
            return Math.abs(firstCoordinateNumber - secondCoordinateNumber) + 1;
        } else {
            return 0;
        }



    }

    public static boolean checkInput(String[] coordinates) {
        if (coordinates.length != 2) {
            System.out.println("Error!");
            return false;
        }

        String firstCoordinate = coordinates[0];
        String secondCoordinate = coordinates[1];

        if (firstCoordinate.length() > 3 || firstCoordinate.length() < 2 || secondCoordinate.length() > 3 || secondCoordinate.length() < 2) {
            System.out.println("Error!");
            return false;
        }

        char firstCoordinateLetter = firstCoordinate.charAt(0);
        char secondCoordinateLetter = secondCoordinate.charAt(0);

        if (!Character.isLetter(firstCoordinateLetter) || !Character.isLetter(secondCoordinateLetter)) {
            System.out.println("Error!");
            return false;
        }

        if (firstCoordinateLetter > 'J' || secondCoordinateLetter > 'J') {
            System.out.println("Error!");
            return false;
        }

        int firstCoordinateNumber = Integer.parseInt(firstCoordinate.substring(1));
        int secondCoordinateNumber = Integer.parseInt(secondCoordinate.substring(1));

        if (firstCoordinateNumber > 10 || secondCoordinateNumber > 10 || firstCoordinateNumber < 1 || secondCoordinateNumber < 1) {
            System.out.println("Error!");
            return false;
        }

        return true;
    }

    public static void placeShip(String firstCoordinate, String secondCoordinate,char[][] field) {

        char firstCoordinateLetter = firstCoordinate.charAt(0);
        char secondCoordinateLetter = secondCoordinate.charAt(0);

        int firstCoordinateNumber = Integer.parseInt(firstCoordinate.substring(1));
        int secondCoordinateNumber = Integer.parseInt(secondCoordinate.substring(1));


        if (secondCoordinateLetter < firstCoordinateLetter || secondCoordinateNumber < firstCoordinateNumber) {
            char tempLetter = firstCoordinateLetter;
            firstCoordinateLetter = secondCoordinateLetter;
            secondCoordinateLetter = tempLetter;

            int tempNumber = firstCoordinateNumber;
            firstCoordinateNumber = secondCoordinateNumber;
            secondCoordinateNumber = tempNumber;
        }

            if (firstCoordinateLetter == secondCoordinateLetter) {
                int row = firstCoordinate.charAt(0) - 'A';
                for (int col = firstCoordinateNumber - 1; col <= secondCoordinateNumber - 1; col++) {
                    field[row][col] = 'O';
                }
            } else if (firstCoordinateNumber == secondCoordinateNumber) {
                int col = firstCoordinateNumber - 1;
                for (int row = firstCoordinateLetter - 'A'; row <= secondCoordinateLetter - 'A'; row++) {
                    field[row][col] = 'O';
                }
            } else {
                System.out.println("Error!");
            }


    }

    public static boolean isShipTooClose(String firstCoordinate, String secondCoordinate, char[][] field) {

        char firstCoordinateLetter = firstCoordinate.charAt(0);
        char secondCoordinateLetter = secondCoordinate.charAt(0);

        int firstCoordinateNumber = Integer.parseInt(firstCoordinate.substring(1));
        int secondCoordinateNumber = Integer.parseInt(secondCoordinate.substring(1));

        if (secondCoordinateLetter < firstCoordinateLetter || secondCoordinateNumber < firstCoordinateNumber) {
            char tempLetter = firstCoordinateLetter;
            firstCoordinateLetter = secondCoordinateLetter;
            secondCoordinateLetter = tempLetter;

            int tempNumber = firstCoordinateNumber;
            firstCoordinateNumber = secondCoordinateNumber;
            secondCoordinateNumber = tempNumber;
        }

        int startRow = Math.max(0, firstCoordinateLetter - 'A' - 1);
        int endRow = Math.min(9, secondCoordinateLetter - 'A' + 1);
        int startCol = Math.max(0, firstCoordinateNumber - 1 - 1);
        int endCol = Math.min(9, secondCoordinateNumber - 1 + 1);

        for (int row = startRow; row <= endRow; row++) {
            for (int col = startCol; col <= endCol; col++) {
                if (field[row][col] == 'O') {
                    return true;
                }
            }
        }

        return false;
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