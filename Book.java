package com.eden.nathanq1;

/**
 * Represents a book in the community library catalogue.
 */
public class Book {
    private String isbn;
    private String title;
    private String author;
    private boolean available;

    /** Creates a book with default author "Unknown" and available status true. */
    public Book(String isbn, String title) {
        this(isbn, title, "Unknown");
    }

    /** Creates a book with the given author and available status true. */
    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return String.format("Book[isbn=%s, title=%s, author=%s, available=%s]",
                isbn, title, author, available);
    }
}
