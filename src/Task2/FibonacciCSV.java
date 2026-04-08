package Task2;

import java.io.*;

public class FibonacciCSV {
    private final static String FILENAME = "fibonacci.csv";

    public static void main(String[] args) {
        writeFibonacciToCSV();
        readCSVFile();
    }

    public static void writeFibonacciToCSV() {
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new FileWriter(FILENAME));

            // Header row
            writer.println("Index;Fibonacci");

            long first = 0;
            long second = 1;

            for (int i = 1; i <= 60; i++) {
                long value;

                if (i == 1) {
                    value = 0;
                } else if (i == 2) {
                    value = 1;
                } else {
                    value = first + second;
                    first = second;
                    second = value;
                }

                writer.println(i + ";" + value);
            }

            System.out.println("Fibonacci sequence written to file: " + FILENAME);

        } catch (IOException e) {
            System.err.println(e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public static void readCSVFile() {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(FILENAME));
            String line;

            System.out.println("\nReading file content:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.out.println("Error while closing the file " + FILENAME);
            }
        }
    }
}