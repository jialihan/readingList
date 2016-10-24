package readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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


    @RequestMapping(value="/readingList/{name}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String readersBooks (@PathVariable String name, Model model)
    {
        List<Book> readingList= readingListService.fetchReadingListByReader(name);
        model.addAttribute("username",name);
        if(readingList != null) {
            model.addAttribute("books", readingList);
        }
        return "readingList";
    }


    @RequestMapping(value = "/readingList/{name}", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String addToReadingList(@PathVariable String name, Book book)
    {
        readingListService.addBookByReader(name,book);
        return "redirect:/readingList/"+name;
    }


    @RequestMapping(method=RequestMethod.GET, value= "/login")
    public String loginPage(){


        return "login";
    }

   /*
    *
    @RequestMapping(method=RequestMethod.GET, value= "/pass")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String loginPass(ModelMap model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        model.addAttribute("author", name);
        return "welcome";

    }
    *
    */

   @RequestMapping(method=RequestMethod.GET, value= "/pass")
   @PreAuthorize("hasAnyRole('ADMIN','USER')")
   public String getName(ModelMap model){

       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       String name = auth.getName(); //get logged in username

       return "redirect:/readingList/"+name;

   }





}
