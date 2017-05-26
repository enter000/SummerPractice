package mywork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InsertSelection {
    final static String FILE = "file";
    final static String RANDOM = "random";
    final static String MYSELF = "myself";

    static void insertSelection(String choise) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        switch (choise) {
            case (InsertSelection.RANDOM): {

                System.out.println("insert dimension of a matrix: ");

                while (Solution.matrix == null) {
                    while (Solution.rowcount == 0) {
                        try {
                            System.out.println("insert rowcount");
                            Solution.rowcount = Integer.parseInt(reader.readLine());
                        } catch (NumberFormatException e) {
                            System.out.println("parameter must be int valued");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    while (Solution.columncount == 0) {
                        try {
                            System.out.println("insert columncount");
                            Solution.columncount = Integer.parseInt(reader.readLine());
                        } catch (NumberFormatException e) {
                            System.out.println("parameter must be int valued");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Solution.matrix = new int[Solution.rowcount][Solution.columncount];

                    for (int row = 0; row < Solution.rowcount; row++) {
                        for (int column = 0; column < Solution.columncount; column++) {
                            Solution.matrix[row][column] = (int) (Math.random() * 100);
                        }
                    }

                }
                System.out.println("matrix was generated: ");

                for (int row = 0; row < Solution.rowcount; row++) {
                    for (int column = 0; column < Solution.columncount; column++) {
                        System.out.print(Solution.matrix[row][column] + "\t");
                    }
                    System.out.println();
                }

                break;
            }
            case (InsertSelection.MYSELF): {
                System.out.println("insert dimension of a matrix: ");

                while (Solution.matrix == null) {
                    while (Solution.rowcount == 0)
                        try {
                            System.out.println("insert rowcount");
                            Solution.rowcount = Integer.parseInt(reader.readLine());
                        } catch (NumberFormatException e) {
                            System.out.println("введи целочисленный параметр");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    while (Solution.columncount == 0) {
                        try {
                            System.out.println("insert columncount");
                            Solution.columncount = Integer.parseInt(reader.readLine());
                        } catch (NumberFormatException e) {
                            System.out.println("parameter must be int valued");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Solution.matrix = new int[Solution.rowcount][Solution.columncount];

                    System.out.println("insert matrix");

                    for (int row = 0; row < Solution.rowcount; row++) {
                        for (int column = 0; column < Solution.columncount; column++) {
                            try {
                                Solution.matrix[row][column] = Integer.parseInt(reader.readLine());
                            } catch (NumberFormatException e) {
                                System.out.println("matrix consists of integer values only");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                break;
            }

            case (InsertSelection.FILE): {
                Solution.readFromFile();
                break;
            }
            default:
                System.out.println("incorrect input, try again");
        }
    }
}

