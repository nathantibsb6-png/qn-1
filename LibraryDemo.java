package com.eden.nathanq1;

import java.time.LocalDate;

/**
 * Demonstrates library lending and return operations.
 */
public class LibraryDemo {

    public static void main(String[] args) {
        Library library = new Library();

        library.addBook(new Book("978-0141036144", "1984"));
        library.addBook(new Book("978-0061120084", "To Kill a Mockingbird", "Harper Lee"));
        library.addBook(new Book("978-0743273565", "The Great Gatsby", "F. Scott Fitzgerald"));

        library.registerMember(new Member("M001", "Alice Kamau"));
        library.registerMember(new Member("M002", "Brian Otieno"));

        System.out.println("=== BEFORE LEND/RETURN OPERATIONS ===");
        System.out.println(library);

        LocalDate today = LocalDate.of(2026, 6, 15);
        library.lendBook("M001", "978-0141036144", today, 14);
        library.lendBook("M002", "978-0061120084", today, 21);

        System.out.println("\n=== ATTEMPT TO LEND ALREADY BORROWED BOOK (SHOULD BE REJECTED) ===");
        library.lendBook("M002", "978-0141036144", today, 7);

        library.lendBook("M001", "978-0743273565", today, 10);
        library.returnBook("978-0141036144");

        System.out.println("\n=== AFTER LEND/RETURN OPERATIONS ===");
        System.out.println(library);

        System.out.println("Search results for \"Great\":");
        for (Book book : library.searchByTitle("Great")) {
            System.out.println("  " + book);
        }
    }
}
