package services;

import mappers.UsersMapper;
import ent.Users;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersMapper usersMapper;

    public Users getUser(long userId) {
        return usersMapper.getUser(userId);
    }

    public List<Users> getAllUsers() {
        return usersMapper.getAllUsers();
    }

}
