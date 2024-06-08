package model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Data
@NoArgsConstructor
@SequenceGenerator(name = "book_id_seq", sequenceName = "book_id_seq", allocationSize = 1)
@Entity
@Table(name = "Book")
public class Book {
    @Setter
    @Getter
    @Id
    @GeneratedValue(generator = "book_id_seq")
    @Column(name = "id")
    private int id;
    @Setter
    @Getter
    @Column(name = "name")
    private String name;
    @Setter
    @Getter
    @Column(name = "genre")
    private String genre;
    @Column(name = "number_of_pages")
    private int numberOfPages;
    @Column(name = "year_of_publishing")
    private int yearOfPublishing;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;
    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    public Book(String name, String genre, int numberOfPages, int yearOfPublishing) {
        this.name = name;
        this.genre = genre;
        this.numberOfPages = numberOfPages;
        this.yearOfPublishing = yearOfPublishing;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    @Override
    public String toString() {
        return "Book: " +
                "\n Name: " + name +
                "\n Genre: " + genre +
                "\n Number of pages: " + numberOfPages +
                "\n Year of publishing: " + yearOfPublishing;
    }
}


