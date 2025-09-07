package ca.saultcollege;

import java.sql.ClientInfoStatus;
import java.util.Scanner;
import java.util.ArrayList;

class Book {
    private String title;
    private String author;
    private String isbn;
    private int pages;

    public Book(String title,String author,String isbn,int pages) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.pages = pages;
    }

        public void setTitle(String title) {this.title = title;}
        public void setAuthor(String author) {this.author = author;}
        public void setIsbn(String isbn) {this.isbn = isbn;}
        public void setPages(int pages) {this.pages = pages;}

        @Override
        public String toString() {
            return String.format("Title: %-25s Author: %-20s ISBN: %s Pages: %d",
                     title,author,isbn,pages);
    }
}

    public class Main {
        private static final ArrayList<Book> books = new ArrayList<>();
        private static Scanner scanner = new Scanner(System.in);

        public static void main(String[] args) {
            while (true) {
                printMenu();
                int choice = readInt("Enter your choice: ");

                switch (choice) {
                    case 1 -> addBook();
                    case 2 -> editBook();
                    case 3 -> deleteBook();
                    case 4 -> listBooks();
                    case 5 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid Choice, Please Try Again.");
                }
            }
        }

        private static void printMenu() {
            System.out.println("\n ----------------");
            System.out.println("   1. Add Book");
            System.out.println("   2. Edit Book");
            System.out.println("   3. Delete Book");
            System.out.println("   4. List Books");
            System.out.println("   5. Exit");
            System.out.println(" ----------------");
        }

        private static void addBook() {
            System.out.println("Enter Title:  ");
            String title = scanner.nextLine();
            System.out.println("Enter Author: ");
            String author = scanner.nextLine();
            System.out.println("Enter ISBN:   ");
            String isbn = scanner.nextLine();
            int pages = readInt("Enter Pages: ");

            books.add(new Book(title, author, isbn, pages));
            System.out.println("Book Added!");
        }

        private static void editBook() {
            listBooks();
            if(books.isEmpty()) return;

            int index = readInt("Enter the number of book to edit: ") - 1;
            if (index < 0 || index >= books.size()) {
                System.out.println("Invalid Selection.");
                return;
            }

            Book book = books.get(index);
            System.out.println("New Title (leave blank to keep current): ");
            String title = scanner.nextLine();
            if(!title.isEmpty()) book.setTitle(title);

            System.out.println("New Author (leave blank to keep current): ");
            String author = scanner.nextLine();
            if(!author.isEmpty()) book.setAuthor(author);

            System.out.println("New ISBN (leave blank to keep current): ");
            String isbn = scanner.nextLine();
            if(!isbn.isEmpty()) book.setIsbn(isbn);

            //String pagesInput;
            System.out.print("New Page Count (leave blank to keep current): ");
            String pagesInput = scanner.nextLine();
            if(!pagesInput.isEmpty()) {book.setPages(Integer.parseInt(pagesInput));}

            System.out.println("Book Updated!");

        }

        private static void deleteBook() {
            listBooks();
            if(books.isEmpty()) return;

            int index = readInt("Enter the number of book to delete: ")- 1;
            if (index < 0 || index >= books.size()) {
                System.out.println("Invalid Selection.");
                return;
            }

            books.remove(index);
            System.out.println("Book Deleted");
        }

        private static void listBooks() {
            if (books.isEmpty()) {
                System.out.println("No Books available.");
                return;
            }

            for (int i = 0; i < books.size(); i++) {
                System.out.printf("%d. %s%n", i+1, books.get(i));
            }
        }

        private static int readInt(String Prompt) {
            while (true) {
                try {
                    System.out.print(Prompt);
                    String input = scanner.nextLine();
                    return Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number");
                }
            }
        }
    }