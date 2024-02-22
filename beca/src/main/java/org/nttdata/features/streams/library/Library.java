package org.nttdata.features.streams.library;

import java.util.*;
import java.util.stream.Collectors;

public class Library {
    public static void main(String[] args) {
        List<Book> books = Arrays.asList(
                new Book("The Adventures of Sherlock Holmes", 307, "Arthur Conan Doyle"),
                new Book("Moby Dick", 635, "Herman Melville"),
                new Book("The Great Gatsby", 180, "F. Scott Fitzgerald")
        );

        List<ComicBook> comicBooks = Arrays.asList(
                new ComicBook("The Adventures of Tintin", 62, "Hergé", "Hergé"),
                new ComicBook("Batman: Year One", 144, "Frank Miller", "David Mazzucchelli"),
                new ComicBook("Watchmen", 334, "Alan Moore", "Dave Gibbons")
        );

        List<Book> bigBooks = books.stream()
                .filter(book -> book.pageNumber() > 100)
                .toList();
        System.out.println("Books with more than 100 pages: " + bigBooks);

        Optional<Book> smallestBook = books.stream()
                .min(Comparator.comparingInt(Book::pageNumber));
        smallestBook.ifPresent(book -> System.out.println("Book with the least number of pages: " + book));

        List<String> authors = books.stream()
                .map(Book::author)
                .toList();
        System.out.println("Authors: " + authors);

        Map<String, List<Book>> booksByAuthor = books.stream()
                .collect(Collectors.groupingBy(Book::author));
        System.out.println("Books grouped by author: " + booksByAuthor);

        List<String> filteredAndSortedBookTitles = books.stream()
                .filter(book -> book.pageNumber() > 200)
                .sorted((book1, book2) -> book2.pageNumber() - book1.pageNumber())
                .map(Book::name)
                .collect(Collectors.toList());

        System.out.println(filteredAndSortedBookTitles);

        List<String> filteredAndSortedComicAuthors = comicBooks.stream()
                .filter(comicBook -> comicBook.pageNumber() > 100)
                .sorted()
                .map(ComicBook::author)
                .collect(Collectors.toList());

        System.out.println(filteredAndSortedComicAuthors);
    }
}