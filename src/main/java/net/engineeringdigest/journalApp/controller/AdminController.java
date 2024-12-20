package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.service.EmailService;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AppCache appCache;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){

        List<User> all = userService.getAll();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/create-new-admin")
    public void createAdmin(@RequestBody User user){

        try {
            userService.saveNewAdmin(user);
        }catch (Exception e){
            System.out.print(e);
            throw new RuntimeException("Error creating new admin", e);
        }

    }

    @GetMapping("clear-app-cache")
    public void clearAppCache(){
        appCache.init();
    }

    @GetMapping("send-mail")
    public ResponseEntity<?> sendMail(){
        try{
            emailService.sendEmail("ashish.goutham@gmail.com", "Hello bro", "fml bro #13reasonsWhyThisSeriesIsShit");
            return new ResponseEntity<>("Mail sent", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Mail not sent " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
