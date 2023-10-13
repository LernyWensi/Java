package Lab_1;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        problem_1_1();
        // problem_1_2();
        // problem_2_1();
        // problem_2_2();
    }

    private static int[] getRandomIntArrayFromInput() {
        System.out.print("Length of the number sequence: ");
        final int arrayLength = scanner.nextInt();

        System.out.print("Bottom and top bounds for random: ");
        final int bottomBound = scanner.nextInt();
        final int topBound = scanner.nextInt();
        System.out.println();

        final int[] numbers = new Random().ints(arrayLength, bottomBound, topBound).toArray();

        return numbers;
    }

    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(String.format("%s  ", matrix[i][j]));
            }
            System.out.println();
        }
        System.out.println();
    }

    // Задана последовательность из N чисел.
    // Определить, сколько среди них чисел меньших К, равных К и больших К.
    public static void problem_1_1() {
        final int[] numbers = getRandomIntArrayFromInput();
        System.out.println(String.format("  Your numbers:\n    > %s\n", Arrays.toString(numbers)));

        System.out.print("Value to compare against: ");
        final int targetValue = scanner.nextInt();

        int greater = 0;
        int lesser = 0;
        int equal = 0;

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > targetValue) {
                greater++;
                continue;
            }

            if (numbers[i] < targetValue) {
                lesser++;
                continue;
            }

            if (numbers[i] == targetValue) {
                equal++;
                continue;
            }
        }

        System.out.println(String.format("\nGreater: %s | Lesser: %s | Equal: %s", greater, lesser, equal));
    }

    // Даны два массива А и В.
    // Найти, сколько элементов массива А совпадает с элементами массива В.
    public static void problem_1_2() {
        final int[] firstArray = getRandomIntArrayFromInput();
        final int[] secondArray = getRandomIntArrayFromInput();

        System.out.println(String.format("  First array:\n    > %s", Arrays.toString(firstArray)));
        System.out.println(String.format("  Second array:\n    > %s", Arrays.toString(secondArray)));

        ArrayList<Integer> firstArrayList = new ArrayList<Integer>(Arrays.stream(firstArray).boxed().toList());
        ArrayList<Integer> secondArrayList = new ArrayList<Integer>(Arrays.stream(secondArray).boxed().toList());

        secondArrayList.retainAll(firstArrayList);
        System.out.println(
                String.format("\nSecond array contains {%s} elements of the first array", secondArrayList.size()));
    }

    // Заданы матрица порядка n и число k. Вычесть из элементов k-го
    // столбца диагональный элемент, расположенный в это столбце.
    public static void problem_2_1() {
        System.out.print("Matrix order: ");
        final int matrixOrder = scanner.nextInt();

        System.out.print("Bottom and top bounds for random: ");
        final int bottomBound = scanner.nextInt();
        final int topBound = scanner.nextInt();
        System.out.println();

        final int[][] matrix = new int[matrixOrder][matrixOrder];

        final Random random = new Random();
        for (int i = 0; i < matrix.length; i++) {
            final int[] row = random.ints(matrixOrder, bottomBound, topBound).toArray();
            matrix[i] = row;
        }

        printMatrix(matrix);

        System.out.print("\nTarget column: ");
        final int targetColumn = scanner.nextInt();

        final int diagonalElement = matrix[targetColumn][targetColumn];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][targetColumn] = matrix[i][targetColumn] - diagonalElement;
        }

        printMatrix(matrix);
    }

    // Дан двумерный массив. Заменить первый нуль в каждом столбце на количество
    // нулей в этом столбце.
    public static void problem_2_2() {
        System.out.print("Number of rows: ");
        final int numberOfColumns = scanner.nextInt();

        System.out.print("Number of columns: ");
        final int numberOfRows = scanner.nextInt();

        System.out.print("Bottom and top bounds for random: ");
        final int bottomBound = scanner.nextInt();
        final int topBound = scanner.nextInt();
        System.out.println();

        final int[][] matrix = new int[numberOfRows][numberOfColumns];

        final Random random = new Random();
        for (int i = 0; i < numberOfRows; i++) {
            final int[] row = random.ints(numberOfColumns, bottomBound, topBound).toArray();
            matrix[i] = row;
        }

        printMatrix(matrix);

        int[] zeroes = new int[numberOfColumns];

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                if (matrix[i][j] == 0) {
                    zeroes[j]++;
                }
            }
        }

        for (int j = 0; j < numberOfColumns; j++) {
            for (int i = 0; i < numberOfRows; i++) {
                if (matrix[i][j] == 0) {
                    matrix[i][j] = zeroes[j];
                    break;
                }
            }
        }

        printMatrix(matrix);
    }
}