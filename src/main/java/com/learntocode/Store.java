package com.learntocode;
import java.util.ArrayList;
import java.util.Scanner;

public class Store {
    private static ArrayList<Book> inventory = new ArrayList<Book>();

    public static void main(String[] args) {
        inventory.add(new Book(1, "9781451673319", "Thinking, Fast and Slow"));
        inventory.add(new Book(2, "9780140449279", "The Republic"));
        inventory.add(new Book(3, "9780141187761", "Animal Farm"));
        inventory.add(new Book(4, "9780679724779", "The Stranger"));
        inventory.add(new Book(5, "9780812980581", "The Immortal Life of Henrietta Lacks"));

        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            System.out.println("Welcome to Ousmane's the Library!");
            System.out.println("Please select an option:");
            System.out.println("1. Show Available Books");
            System.out.println("2. Show Checked Out Books");
            System.out.println("3. Exit");

            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    showAvailableBooks(scanner);
                    break;
                case "2":
                    showCheckedOutBooks(scanner);
                    break;
                case "3":
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (!choice.equals("3"));
    }

    private static void showAvailableBooks(Scanner scanner) {
        System.out.println("Available Books:");
        for (Book book : inventory) {
            if (!book.isCheckedOut()) {
                System.out.println(book.getId() + " - " + book.getIsbn() + " - " + book.getTitle());
            }
        }
        System.out.println("Please select a book to check out (or type X to go back to the home screen):");
        String choice = scanner.nextLine();
        if (!choice.equals("X")) {
            int bookId = Integer.parseInt(choice);
            Book book = findBookById(bookId);
            if (book != null && !book.isCheckedOut()) {
                System.out.println("Please enter your name:");
                String name = scanner.nextLine();
                book.checkOut(name);
                System.out.println("Thank you, " + name + "! You have checked out " + book.getTitle() + ".");
            } else {
                System.out.println("Invalid selection. Please try again.");
            }
        }
    }

    private static void showCheckedOutBooks(Scanner scanner) {
        System.out.println("Checked Out Books:");
        for (Book book : inventory) {
            if (book.isCheckedOut()) {
                System.out.println(book.getId() + " - " + book.getIsbn() + " - " + book.getTitle() + " - Checked out to: " + book.getCheckedOutTo());
            }
        }
        System.out.println("Please select an option:");
        System.out.println("C - Check In a book");
        System.out.println("X - Go back to the home screen");
        String choice = scanner.nextLine();
        if (choice.equals("C")) {
            System.out.println("Please enter the ID of the book to check in:");
            int bookId = Integer.parseInt(scanner.nextLine());
            Book book = findBookById(bookId);
            if (book != null && book.isCheckedOut()) {
                book.checkIn();
                System.out.println("Book checked in successfully!");
            } else {
                System.out.println("Invalid selection. Please try again.");
            }
        }
    }
    public static Book findBookById(int id) {
        for (Book book : inventory) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }
}




