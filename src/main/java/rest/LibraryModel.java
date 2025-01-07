package rest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryModel {
    private final String filePath = "src/main/resources/library.txt";

    public List<Book> getAllBooks() throws IOException {
        List<Book> books = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                books.add(Book.fromString(line));
            }
        }
        return books;
    }

    public void addBook(Book book) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(book.toString());
            writer.newLine();
        }
    }

    public void removeBook(String isbn) throws IOException {
        List<Book> books = getAllBooks();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Book book : books) {
                if (!book.getIsbn().equals(isbn)) {
                    writer.write(book.toString());
                    writer.newLine();
                }
            }
        }
    }
}