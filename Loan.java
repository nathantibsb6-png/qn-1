package com.eden.nathanq1;

import java.time.LocalDate;

/**
 * Connects exactly one member to exactly one book for a borrowing period.
 */
public class Loan {
    private final Member member;
    private final Book book;
    private final LocalDate borrowDate;
    private final LocalDate dueDate;

    public Loan(Member member, Book book, LocalDate borrowDate, LocalDate dueDate) {
        this.member = member;
        this.book = book;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    public Member getMember() {
        return member;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return String.format("Loan[member=%s, book=%s, borrowDate=%s, dueDate=%s]",
                member.getMemberId(), book.getIsbn(), borrowDate, dueDate);
    }
}
