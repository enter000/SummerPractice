package mywork;

import java.io.*;
import java.util.*;


public class Solution {
    private static int[][] matrix;
    private static int rowcount = 0;
    private static int columncount = 0;

    public static HashSet<Integer> searchingColumns() {
        readFromFile();
        HashSet<Integer> columnset = new HashSet<Integer>();

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

    public static int theLeastMuliplication() {
        HashSet<Integer> columnset = searchingColumns();
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

    private static void readFromFile() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the full path of the file from which you want to read the matrix");

        while (matrix == null) {
            try {
                String filepath = reader.readLine();
                Scanner scanner = new Scanner(new File(filepath));

                ArrayList<String> stringlist = new ArrayList<String>();

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
    }

    public static void main(String[] args) throws IOException {

        System.out.println("A column satisfying the condition, with the smallest product of elements is "
                +theLeastMuliplication());
    }
}