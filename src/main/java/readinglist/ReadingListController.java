package readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jialihan on 16/10/3.
 */
@Controller
@RequestMapping("/")
public class ReadingListController {

    private static final String reader = "jiali";

    @Autowired
    private ReadingListService readingListService;

    @RequestMapping(method=RequestMethod.GET, value={"/index","/"})
    public String welcomeIndexPage()
    {

        return "index";
    }

    @RequestMapping(method=RequestMethod.GET, value="/fail")
    public void fail()
    {

        throw new RuntimeException();
    }

    @ExceptionHandler(value=RuntimeException.class)
    @ResponseStatus(value= HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
    public String error(){

        return "error";
    }


    @RequestMapping(value="/readingList", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String readersBooks (Model model)
    {
        List<Book> readingList= readingListService.fetchReadingListByReader(reader);
        if(readingList != null)
        {
            model.addAttribute("books", readingList);
        }
        return "readingList";
    }


    @RequestMapping(value = "/readingList", method = RequestMethod.POST)
    public String addToReadingList(Book book)
    {

        readingListService.addBookByReader(reader,book);
        return "redirect:/readingList";
    }


    @RequestMapping(method=RequestMethod.GET, value= "/login")
    public String loginPage(){


        return "login";
    }

    @RequestMapping(method=RequestMethod.GET, value= "/addSuccess")
    public String addSuccess(){


        return "addSuccess";
    }




}
