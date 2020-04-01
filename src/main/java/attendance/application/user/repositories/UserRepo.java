package attendance.application.user.repositories;

import attendance.application.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {


    User findByUserName(String userName);


    User findByIdAndPassword(int id,String password);
}
