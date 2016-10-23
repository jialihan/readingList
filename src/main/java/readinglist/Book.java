package readinglist;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by jialihan on 16/10/3.
 */
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String reader;
    private String isbn;
    private String title;
    private String author;
    private String description;
    public  Book(Long id, String reader)
    {
        this.id = id;
        this.reader = reader;
    }
    public  Book()
    {
        this.id = 0L;
    }
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getReader(){
        return this.reader;
    }
    public void setReader(String reader)
    {
        this.reader = reader;
    }
    public String getIsbn(){
        return this.isbn;
    }
    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getAuthor()
    {
        return  this.author;
    }
    public void setAuthor(String author)
    {
        this.author = author;
    }
    public String getDescription()
    {
        return this.description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }


}
