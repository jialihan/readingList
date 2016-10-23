package readinglist;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by jialihan on 16/10/23.
 */
public class EasyMockBaseTest {

    private Book book;
    private ReadingListRepository repo;


    @Before
    public void initMock(){
        book = EasyMock.createMock(Book.class);
        repo = EasyMock.createMock(ReadingListRepository.class);

        //激活mock的对象，才能进行test
        EasyMock.replay(book);
        EasyMock.replay(repo);

    }

    @Test
    public void testMockitoBase(){
        Assert.assertNotNull(book);
        Assert.assertNotNull(repo);

    }

}


