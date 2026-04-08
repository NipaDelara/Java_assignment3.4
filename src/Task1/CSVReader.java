package Task1;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CSVReader {
    private final static String FILENAME = "https://users.metropolia.fi/~jarkkov/temploki.csv";

    public static void main(String[] args) {
        BufferedReader bufferedstream = null;
        String line;
        String[] columnNames = new String[0];
        boolean header = true;

        int ulkoTaloIndex = -1;
        int timeIndex = -1;

        double sum = 0;
        int count = 0;

        try {
            URL url = new URL(FILENAME);
            InputStreamReader file = new InputStreamReader(url.openStream());
            bufferedstream = new BufferedReader(file);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

            do {
                line = bufferedstream.readLine();

                if (line != null) {
                    if (header) {
                        // first row of the file contains names of columns
                        columnNames = line.split(";");

                        for (int i = 0; i < columnNames.length; i++) {
                            if (columnNames[i].equals("Aika")) {
                                timeIndex = i;
                            }
                            if (columnNames[i].equals("UlkoTalo")) {
                                ulkoTaloIndex = i;
                            }
                        }

                        header = false;
                    } else {
                        String[] columns = line.split(";");

                        if (timeIndex != -1 && ulkoTaloIndex != -1 && columns.length > ulkoTaloIndex) {
                            try {
                                String timeString = columns[timeIndex];
                                String tempString = columns[ulkoTaloIndex].replace(",", ".");

                                LocalDateTime dateTime = LocalDateTime.parse(timeString, formatter);

                                if (dateTime.getYear() == 2023 &&
                                        dateTime.getMonthValue() == 1 &&
                                        dateTime.getDayOfMonth() == 1) {

                                    double temp = Double.parseDouble(tempString);
                                    sum += temp;
                                    count++;
                                }
                            } catch (Exception e) {
                                // skip invalid rows
                            }
                        }
                    }
                }
            } while (line != null);

            if (count > 0) {
                double average = sum / count;
                System.out.printf("Average temperature in UlkoTalo on 1.1.2023: %.2f °C%n", average);
            } else {
                System.out.println("No data found for 1.1.2023.");
            }

        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                if (bufferedstream != null)
                    bufferedstream.close();
            } catch (Exception e) {
                System.out.println("Error while closing the file " + FILENAME);
            }
        }
    }
}