package attendance.application.user.service;

import attendance.application.exception.UserNotFoundException;
import attendance.application.user.entity.User;
import attendance.application.user.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DashboardService {

    @Autowired
    UserRepo userRepo;

    public User checkUser(int id){

        Optional<User> user = userRepo.findById(id);

        if (!user.isPresent())
            throw new UserNotFoundException("check your name or password");

        return user.get();
    }

}
