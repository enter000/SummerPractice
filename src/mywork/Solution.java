package mywork;

import java.io.*;
import java.util.*;


public class Solution {
    static int[][] matrix;
    static int rowcount = 0;
    static int columncount = 0;

    public static HashSet<Integer> searchingColumns() {
        HashSet<Integer> columnset = new HashSet<>();

        for (int column = 0; column < columncount; column++) {
            boolean key = true;

                for (int row = 0; row < rowcount; row++) {
                    if (Math.abs(matrix[row][column]) > 10) {
                        key = false;
                        break;
                    }
                }
                if (key) {
                    columnset.add(column);
                }
            }
        return columnset;
    }

    public static int theLeastMultiplication() {
        HashSet<Integer> columnset = searchingColumns();

        if (columnset.size() == 0) {
            System.out.println("there are no columns satisfying the condition");
            System.exit(1);
        }

        int leastmultiplication = (int) Math.pow(10,rowcount);
        int rightcolumn = 0;

        Iterator<Integer> iterator = columnset.iterator();

        while (iterator.hasNext()) {
            int multiplication = 1;
            int column = iterator.next();
            for (int row = 0; row < rowcount; row++) {
                multiplication = matrix[row][column] * multiplication;
            }

            if (multiplication < leastmultiplication) {
                leastmultiplication = multiplication;
                rightcolumn = column;
            }
        }

        return rightcolumn;
    }

    static void readFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the full path of the file from which you want to read the matrix");

        while (matrix == null) {
            try {
                String filepath = reader.readLine();
                Scanner scanner = new Scanner(new File(filepath));

                ArrayList<String> stringlist = new ArrayList<>();

                while (scanner.hasNext()) {
                    stringlist.add(scanner.nextLine());
                }
                scanner.close();

                rowcount = stringlist.size();
                String[] columns = stringlist.get(0).split(" ");
                columncount = columns.length;

                matrix = new int[rowcount][columncount];

                for (int row = 0; row < rowcount; row++) {
                    String[] matrixline = stringlist.get(row).split(" ");

                    for (int column = 0; column < columncount; column++) {
                        matrix[row][column] = Integer.valueOf(matrixline[column]);
                    }
                }

            } catch (FileNotFoundException e) {
                System.out.println("File not found, try again");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        boolean key = true;
        System.out.println("do u want to see matrix? type y to see, n to not");
        while (key) {
            String answer = reader.readLine();

            if (answer.equals("y")) {
                for (int row = 0; row < rowcount; row++) {
                    for (int column = 0; column < columncount; column++) {
                        System.out.print(matrix[row][column]+"\t");
                    }
                    System.out.println();
                }
                key = false;
            } else {
                if (answer.equals("n")) {
                    System.out.println("as u wish");
                    key = false;
                }
            }
            System.out.println("try again");
        }
    }

    static void matrixInput() {
        System.out.println("how do u want to read matrix?");
        System.out.println("write "+InsertSelection.FILE+" if u want to read matrix from file; " +
                "write "+ InsertSelection.MYSELF+" if u want to put matrix yourself; " +
                "write "+ InsertSelection.RANDOM+" if u want to do a random matrix.");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (Solution.matrix == null) {
         try {
             String choise = reader.readLine();
             InsertSelection.insertSelection(choise);
         } catch (IOException e) {
             e.printStackTrace();
         }
        }
    }

    public static void main(String[] args) {

        matrixInput();
        System.out.println("A column satisfying the condition, with the smallest product of elements is "
                +theLeastMultiplication());
    }
}