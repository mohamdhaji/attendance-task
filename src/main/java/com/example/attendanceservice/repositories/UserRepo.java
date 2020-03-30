package com.example.attendanceservice.repositories;

import com.example.attendanceservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {


    User findByUserName(String userName);
}
