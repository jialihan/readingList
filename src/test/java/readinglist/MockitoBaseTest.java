package readinglist;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

/**
 * Created by jialihan on 16/10/23.
 */
public class MockitoBaseTest {
    private Book book;
    private ReadingListRepository repo;
    private ReadingListService readingListService;

    @Before
    public void initMock(){
        readingListService = new ReadingListService();
        book = mock(Book.class);
        repo = mock(ReadingListRepository.class);
    }

    @Test
    public void testMockitoBase(){
        Assert.assertNotNull(book);
        Assert.assertNotNull(repo);

    }

}




