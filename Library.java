package com.eden.nathanq1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Manages the library catalogue, members, and active loans.
 */
public class Library {
    private final List<Book> books;
    private final List<Member> members;
    private final List<Loan> activeLoans;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.activeLoans = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerMember(Member member) {
        members.add(member);
    }

    /**
     * Lends a book to a member when the book is available.
     *
     * @return true if the loan was created; false if rejected
     */
    public boolean lendBook(String memberId, String isbn, LocalDate borrowDate, int loanDays) {
        Optional<Member> member = findMember(memberId);
        Optional<Book> book = findBookByIsbn(isbn);

        if (member.isEmpty() || book.isEmpty()) {
            System.out.println("Loan rejected: member or book not found.");
            return false;
        }

        if (!book.get().isAvailable()) {
            System.out.println("Loan rejected: \"" + book.get().getTitle() + "\" is already on loan.");
            return false;
        }

        LocalDate dueDate = borrowDate.plusDays(loanDays);
        Loan loan = new Loan(member.get(), book.get(), borrowDate, dueDate);
        book.get().setAvailable(false);
        member.get().addLoan(loan);
        activeLoans.add(loan);
        System.out.println("Loan approved: " + loan);
        return true;
    }

    /**
     * Returns a borrowed book and clears its active loan.
     *
     * @return true if the return succeeded; false otherwise
     */
    public boolean returnBook(String isbn) {
        Optional<Book> book = findBookByIsbn(isbn);
        if (book.isEmpty()) {
            System.out.println("Return failed: book not found.");
            return false;
        }

        if (book.get().isAvailable()) {
            System.out.println("Return failed: \"" + book.get().getTitle() + "\" is not on loan.");
            return false;
        }

        Optional<Loan> loan = findActiveLoanByIsbn(isbn);
        if (loan.isEmpty()) {
            System.out.println("Return failed: no active loan record found.");
            return false;
        }

        book.get().setAvailable(true);
        loan.get().getMember().removeLoan(loan.get());
        activeLoans.remove(loan.get());
        System.out.println("Book returned: " + book.get().getTitle());
        return true;
    }

    public List<Book> searchByTitle(String titleFragment) {
        List<Book> matches = new ArrayList<>();
        String query = titleFragment.toLowerCase();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(query)) {
                matches.add(book);
            }
        }
        return matches;
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }

    public List<Member> getMembers() {
        return Collections.unmodifiableList(members);
    }

    public List<Loan> getActiveLoans() {
        return Collections.unmodifiableList(activeLoans);
    }

    private Optional<Member> findMember(String memberId) {
        return members.stream()
                .filter(member -> member.getMemberId().equals(memberId))
                .findFirst();
    }

    private Optional<Book> findBookByIsbn(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }

    private Optional<Loan> findActiveLoanByIsbn(String isbn) {
        return activeLoans.stream()
                .filter(loan -> loan.getBook().getIsbn().equals(isbn))
                .findFirst();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Library state:\n");
        builder.append("  Books (").append(books.size()).append("):\n");
        for (Book book : books) {
            builder.append("    ").append(book).append('\n');
        }
        builder.append("  Members (").append(members.size()).append("):\n");
        for (Member member : members) {
            builder.append("    ").append(member).append('\n');
        }
        builder.append("  Active loans (").append(activeLoans.size()).append("):\n");
        for (Loan loan : activeLoans) {
            builder.append("    ").append(loan).append('\n');
        }
        return builder.toString();
    }
}
