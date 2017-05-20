package anotherstuff;

import java.io.*;
import java.util.*;


public class Solution {
    private static int[][] matrix = new int[10][10];

    public static void readFromFile(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));

            ArrayList<String> arraylist = new ArrayList<String>();

            while (scanner.hasNext()) {
                arraylist.add(scanner.nextLine());
            }

            for (int row = 0; row < arraylist.size(); row++) {
                String[] matrixline = arraylist.get(row).split(" ");

                for (int element = 0; element < matrixline.length; element++) {
                    matrix[row][element] = Integer.valueOf(matrixline[element]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(1);
        }
    }

    public static void findSimilarRowAndColumn(String filename) {
        readFromFile(filename);
        HashMap<Integer,Integer> setofsimilarrowsandcolumns = new HashMap<Integer,Integer>();

        for (int iterator = 0; iterator < 10; iterator++) {

            int[] line = new int[10];

            for (int iteratorforarray = 0; iteratorforarray < 10; iteratorforarray++) {
                line[iteratorforarray] = matrix[iterator][iteratorforarray];
            }

            for (int column = 0; column < 10; column++) {
                boolean key = true;

                for (int row = 0; row < 10; row++) {
                    if (line[row] != matrix[row][column]) {
                        key = false;
                        break;
                    }
                }
                if (key) {
                    setofsimilarrowsandcolumns.put(iterator,column);
                }
            }


        }

        if (setofsimilarrowsandcolumns.isEmpty()) {
            System.out.println("no rows and columns satisfying the condition");
        } else {
            Iterator<Map.Entry<Integer,Integer>> iterator = setofsimilarrowsandcolumns.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry<Integer,Integer> pair = iterator.next();
                System.out.println("row number "+pair.getKey()+" and column number "+pair.getValue()+" are similar");
            }
        }
    }

    public static void printMatrix(String pathname) {
        for (int i = 0; i < 10; i ++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(matrix[i][j]+"\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter the full path of the file from which you want to read the matrix");
        String filepath = reader.readLine();

        findSimilarRowAndColumn(filepath);

    }
}
