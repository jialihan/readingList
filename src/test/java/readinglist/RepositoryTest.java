package readinglist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=ReadingListApplication.class)
public class RepositoryTest {

    @Autowired
    private ReadingListRepository repo;

    @Test
    @Transactional
    public void saveABook() throws Exception {
        assertEquals(0, repo.findAll().size());

        Book book = new Book();
        book.setTitle("helloJiali");
        book.setDescription("DESCRIPTION");
        book.setAuthor("AUTHOR");
        book.setReader("jiali");
        book.setIsbn("19901227");
        Book saved = repo.save(book);

        Book found = repo.findOne(saved.getId());
        assertEquals(saved.getId(), found.getId());
        assertEquals("helloJiali", found.getTitle());
        assertEquals("DESCRIPTION", found.getDescription());
        assertEquals("AUTHOR", found.getAuthor());
        assertEquals("19901227", found.getIsbn());
        assertEquals("jiali", found.getReader());
    }

}
