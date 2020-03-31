package attendance.application.user.controller;


import attendance.application.exception.UserNotFoundException;
import attendance.application.user.entity.User;
import attendance.application.user.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {

    private Optional<User> user;

    @Autowired
    UserRepo userRepo;

    @GetMapping("/search")
    public ResponseEntity retrieveUserName(@RequestParam(value = "username", defaultValue = "") String userName) {

        Optional<User> user = Optional.ofNullable(userRepo.findByUserName(userName));

        if (!user.isPresent())
            throw new UserNotFoundException("name-" + userName);


        return new ResponseEntity(user.get().getUserName() + " exist.", HttpStatus.OK);
    }

    @RequestMapping(value = "/search/password", method = RequestMethod.GET)
    public ResponseEntity retrieveUserPass(@RequestParam(value = "password", defaultValue = "") String password,@RequestParam(value = "username", defaultValue = "") String username) {


        Optional<User> user= Optional.ofNullable(userRepo.findByUserNameAndPassword(username, password));

        if (!user.isPresent())
            throw new UserNotFoundException("check your name or password");



            return new ResponseEntity(user.get().getUserName() + " logged in Successfully.", HttpStatus.OK);



    }


//==================testing======================

    @PostMapping()
    public User addUser(@RequestBody User user) {

        return userRepo.save(user);


    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") int id) {

        userRepo.deleteById(id);


    }

    @GetMapping
    public List<User> getAll() {

        return userRepo.findAll();

    }



}
