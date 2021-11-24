package bo.edu.ucb.ingsoft.SakilaRental.bl;

import bo.edu.ucb.ingsoft.SakilaRental.dao.UserDao;
import bo.edu.ucb.ingsoft.SakilaRental.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserBL {
    private final UserDao userDao;

    @Autowired
    public UserBL(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> findUserById(Integer userID){
        return userDao.findUserById(userID);
    }

    public boolean createUser(User user){
        return userDao.createUser(user);
    }

    public boolean updateUser(User user){
        return userDao.updateUser(user);
    }
}
