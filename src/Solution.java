import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Solution {
    private static int[][] matrix;
    private static int rowcount = 0;
    private static int columncount = 0;

    public static HashSet<Integer> searchingColumns(String filepath) {
        readFromFile(filepath);
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

    public static int theLeastMuliplication(String filepath) {
        HashSet<Integer> columnset = searchingColumns(filepath);
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

    private static void readFromFile(String filename) {

        try {
            Scanner scanner = new Scanner(new File(filename));

            boolean key = false;

            ArrayList<String> stringlist = new ArrayList<String>();

            while (scanner.hasNext()) {
                stringlist.add(scanner.nextLine());
            }

            rowcount = stringlist.size();
            String[] columns = stringlist.get(0).split(" ");
            columncount = columns.length;

            matrix = new int[rowcount][columncount];

            for (int row = 0; row < stringlist.size(); row++) {
                String[] matrixline = stringlist.get(row).split(" ");

                if (key = false) {
                    columncount = matrixline.length;
                    key = true;
                }

                for (int column = 0; column < matrixline.length; column++) {
                    matrix[row][column] = Integer.valueOf(matrixline[column]);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the full path of the file from which you want to read the matrix");

        String filepath = reader.readLine();

        System.out.println("A column satisfying the condition, with the smallest product of elements is "+theLeastMuliplication(filepath));
    }
}