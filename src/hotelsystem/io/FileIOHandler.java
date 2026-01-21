
package hotelsystem.io;

import java.io.*;
import java.util.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileIOHandler {

    public static void appendLine(String filename, String line) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename, true))) {
            pw.println(line);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static List<String> readAll(String filename) {
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } 
        catch (FileNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return lines;
    }

    public static void deleteLineFromFile(String filename, String lineToRemove) {
        File file = new File(filename);
        StringBuilder content = new StringBuilder();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.equals(lineToRemove)) {
                    content.append(line).append("\n");
                }
            }
        } 
        catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.print(content.toString());
        } 
        catch (Exception e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    public static void updateLineInFile(String filename, String oldLine, String newLine) {
        File file = new File(filename);
        StringBuilder content = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.equals(oldLine)) {
                    content.append(newLine).append("\n");
                } 
                else {
                    content.append(line).append("\n");
                }
            }
        } 
        catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        try (PrintWriter pw = new PrintWriter(file)) {
            pw.print(content.toString());
        } 
        catch (Exception e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
