public class Main {
    public static void main(String[] args) {
        char[][] field = new char[10][10];
        fillField(field);
        printField(field);
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