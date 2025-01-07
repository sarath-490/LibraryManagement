package rest;





import java.util.List;
import java.util.Scanner;

public class LibraryView {
    private final Scanner scanner = new Scanner(System.in);

    public int displayMenu() {
        System.out.println("Library Management System");
        System.out.println("1. Add a Book");
        System.out.println("2. View All Books");
        System.out.println("3. Remove a Book");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    public Book getBookDetails() {
        scanner.nextLine(); // Consume newline
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();
        return new Book(title, author, isbn);
    }

    public String getIsbnToRemove() {
        System.out.print("Enter ISBN of the book to remove: ");
        return scanner.next();
    }

    public void displayBooks(List<Book> books) {
        System.out.println("Available Books:");
        for (Book book : books) {
            System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor() + ", ISBN: " + book.getIsbn());
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}