package readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jialihan on 16/10/10.
 */
@Service
public class ReadingListService {

    @Autowired
    ReadingListRepository readingListRepository;



    public Boolean checkEmptyList(String username) {

        List<Book> readingList = readingListRepository.findByReader(username);
        Boolean result;
        if (readingList == null || readingList.size() == 0) {

            // readingListRepository 可以在此处为user添加一本新书
            result= true;
        } else {
            result = false;
        }
        return result;
    }

    public Boolean emptyReadingList(String username){


        if(checkEmptyList(username))
        {
            // already empty(), nothing to do
            return true;
        }
        else
        {
            List<Book> books = readingListRepository.findByReader(username);
            for(Book one: books)
            {
                readingListRepository.delete(one);
            }
            if(checkEmptyList(username))
                return true; // check again? whether clean successfully?

        }
        // empty Execution Failure
        return false;
    }

    public void addBookByReader(String reader, Book book) {
        book.setReader(reader);
        readingListRepository.save(book);

    }

    public List<Book> fetchReadingListByReader(String reader) {

        List<Book> readingList = readingListRepository.findByReader(reader);
        return readingList;
    }

    public int getCountByReader(String reader, ReadingListRepository repo){
        this.readingListRepository = repo;

        List<Book> list =  readingListRepository.findByReader(reader);
        if( list == null )
            return -1;

        return list.size();
    }




}
