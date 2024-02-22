package org.nttdata.features.streams.library;

public record ComicBook(String name, Integer pageNumber, String author, String illustrator) implements Comparable<ComicBook> {
    @Override
    public int compareTo(ComicBook o) {
        return this.pageNumber.compareTo(o.pageNumber);
    }
}