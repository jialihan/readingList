package readinglist;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by jialihan on 16/10/23.
 */

public class MockitoServiceTest {

    private ReadingListService service;
    @Mock
    private ReadingListRepository  repo;
    @Mock
    private Book book;

    @Before
    public void initMock()
    {
        MockitoAnnotations.initMocks(this);
        service = new ReadingListService();
    }


    @Test
    public void testBookService()
    {
        List<Book> origin_list = new ArrayList<>();
        origin_list.add(new Book(1L, "xiaojia"));
        when(repo.findByReader("xiaojia")).thenReturn(origin_list);

        int result = service.getCountByReader("xiaojia",repo);

        //验证方法调用
        verify(repo).findByReader("xiaojia");

        // junit测试
        assertEquals(result, origin_list.size());
    }



    @Test
    public void testBookDao()
    {
        List<Book> origin_list = new ArrayList<>();
        origin_list.add(new Book(1L, "xiaojia"));
        when(repo.findByReader("xiaojia")).thenReturn(origin_list);

        //junit 测试
        assertEquals(1, repo.findByReader("xiaojia").size());

    }

}

