package com.example.attendanceservice.controllers;


import com.example.attendanceservice.entities.User;
import com.example.attendanceservice.exception.UserNotFoundException;
import com.example.attendanceservice.repositories.UserRepo;
import com.example.attendanceservice.session.LoginSession;
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

    @Autowired
    private Optional<User> user;

    @Autowired
    UserRepo userRepo;

    @GetMapping("/search")
    public ResponseEntity retrieveUserName(@RequestParam(value = "username", defaultValue = "") String userName) {

        user = Optional.ofNullable(userRepo.findByUserName(userName));

        if (!user.isPresent())
            throw new UserNotFoundException("name-" + userName);


        return new ResponseEntity(user.get().getUserName() + " enter your password .", HttpStatus.OK);
    }

    @RequestMapping(value = "/search/password", method = RequestMethod.GET)
    public ResponseEntity retrieveUserPass(HttpServletRequest request,@RequestParam(value = "password", defaultValue = "") String password) {




        if (!user.isPresent())
            throw new UserNotFoundException("Enter your name first !");


        if (password.equals(user.get().getPassword())) {


            request.getSession().setAttribute("LOGIN-SESSION", user.get());
            request.getSession().setMaxInactiveInterval(40);
            LoginSession.session = request.getSession();


            return new ResponseEntity(user.get().getUserName() + " logged in .", HttpStatus.OK);
        }

        return new ResponseEntity("password is not correct", HttpStatus.BAD_REQUEST);

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

    @GetMapping("/test")
    public ResponseEntity test() {

        try{
            return new ResponseEntity(LoginSession.session.getAttribute("LOGIN-SESSION"), HttpStatus.OK);


        }catch(Exception e){
            return new ResponseEntity("session closed", HttpStatus.UNAUTHORIZED);
        }



    }


}
