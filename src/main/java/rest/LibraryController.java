package rest;
import java.io.IOException;

public class LibraryController {
    private final LibraryModel model;
    private final LibraryView view;

    public LibraryController(LibraryModel model, LibraryView view) {
        this.model = model;
        this.view = view;
    }

    public void start() {
        while (true) {
            int choice = view.displayMenu();
            try {
                switch (choice) {
                    case 1:
                        Book book = view.getBookDetails();
                        model.addBook(book);
                        view.displayMessage("Book added successfully!");
                        break;
                    case 2:
                        view.displayBooks(model.getAllBooks());
                        break;
                    case 3:
                        String isbn = view.getIsbnToRemove();
                        model.removeBook(isbn);
                        view.displayMessage("Book removed successfully!");
                        break;
                    case 4:
                        view.displayMessage("Exiting the system. Goodbye!");
                        return;
                    default:
                        view.displayMessage("Invalid choice. Try again.");
                }
            } catch (IOException e) {
                view.displayMessage("Error: " + e.getMessage());
            }
        }
    }
}