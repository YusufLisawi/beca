package org.nttdata.features.streams.library;

public record Book(String name, Integer pageNumber, String author) implements Comparable<Book> {
    @Override
    public int compareTo(Book o) {
        return this.pageNumber.compareTo(o.pageNumber);
    }
}