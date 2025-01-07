package rest;
import java.io.*;
import java.util.*;

public class MainApplication {
    private static final String LIBRARY_FILE = "library.txt";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Library Management System");
            System.out.println("1. Add a Book");
            System.out.println("2. View All Books");
            System.out.println("3. Remove a Book");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    viewAllBooks();
                    break;
                case 3:
                    removeBook(scanner);
                    break;
                case 4:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addBook(Scanner scanner) throws IOException {
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LIBRARY_FILE, true))) {
            writer.write(title + "," + author + "," + isbn);
            writer.newLine();
        }

        System.out.println("Book added successfully!");
    }

    private static void viewAllBooks() throws IOException {
        File file = new File(LIBRARY_FILE);
        if (!file.exists()) {
            System.out.println("No books found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("Books in the Library:");
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                System.out.println("Title: " + details[0] + ", Author: " + details[1] + ", ISBN: " + details[2]);
            }
        }
    }

    private static void removeBook(Scanner scanner) throws IOException {
        System.out.print("Enter ISBN of the book to remove: ");
        String isbnToRemove = scanner.nextLine();

        File file = new File(LIBRARY_FILE);
        if (!file.exists()) {
            System.out.println("No books found.");
            return;
        }

        File tempFile = new File("library_temp.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details[2].equals(isbnToRemove)) {
                    found = true;
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }

            if (found) {
                System.out.println("Book removed successfully!");
            } else {
                System.out.println("Book not found.");
            }
        }

        // Replace the old file with the new one
        if (!file.delete() || !tempFile.renameTo(file)) {
            System.out.println("Error updating the library file.");
        }
    }
}