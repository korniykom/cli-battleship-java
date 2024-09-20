package battleship;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char[][] field = new char[10][10];
        Scanner scanner = new Scanner(System.in);



        fillField(field);
        printField(field);
        placeShip(scanner);


        scanner.close();
    }

    public static void placeShip(Scanner scanner) {
        int length;

        System.out.println("Enter the coordinates of the ship:");
        String input = scanner.nextLine();
        String[] coordinates = input.split(" ");

        if (coordinates.length != 2) {
            System.out.println("Error!");
            return;
        }

        String firstCoordinate = coordinates[0];
        String secondCoordinate = coordinates[1];

        if (firstCoordinate.length() > 3 || firstCoordinate.length() < 2 || secondCoordinate.length() > 3 || secondCoordinate.length() < 2) {
            System.out.println("Error!");
            return;
        }

        char firstCoordinateLetter = firstCoordinate.charAt(0);
        char secondCoordinateLetter = secondCoordinate.charAt(0);

        if(!Character.isLetter(firstCoordinateLetter) || !Character.isLetter(secondCoordinateLetter)) {
            System.out.println("Error!");
            return;
        }

        if(firstCoordinateLetter > 'J' || secondCoordinateLetter > 'J') {
            System.out.println("Error!");
            return;
        }

        int firstCoordinateNumber = Integer.parseInt(firstCoordinate.substring(1));
        int secondCoordinateNumber = Integer.parseInt(secondCoordinate.substring(1));

        if(firstCoordinateNumber > 10 || secondCoordinateNumber > 10 || firstCoordinateNumber < 1 || secondCoordinateNumber < 1) {
            System.out.println("Error!");
            return;
        }

        if(secondCoordinateLetter < firstCoordinateLetter || secondCoordinateNumber < firstCoordinateNumber) {
            char tempLetter = firstCoordinateLetter;
            firstCoordinateLetter = secondCoordinateLetter;
            secondCoordinateLetter = tempLetter;

            int tempNumber = firstCoordinateNumber;
            firstCoordinateNumber = secondCoordinateNumber;
            secondCoordinateNumber = tempNumber;
        }

        if(firstCoordinateNumber == secondCoordinateNumber) {
            length = Math.abs((int) firstCoordinateLetter - (int) secondCoordinateLetter) + 1;
            System.out.printf("Length: %d\n", length);

            System.out.print("Parts: ");
            for(int i = 0; i < length; i++) {
                System.out.printf("%c%d ", firstCoordinateLetter + i, firstCoordinateNumber);
            }
        } else if(firstCoordinateLetter == secondCoordinateLetter) {
            length = Math.abs(firstCoordinateNumber - secondCoordinateNumber) + 1;
            System.out.printf("Length: %d\n", length);

            System.out.print("Parts: ");
            for(int i = 0; i < length; i++) {
                System.out.printf("%c%d ", firstCoordinateLetter , firstCoordinateNumber + i);
            }
        } else {
            System.out.println("Error!");
            return;
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
        System.out.print("  ");
        for(int i = 0; i < field.length; i++) {
            System.out.printf("%d ", i + 1);
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