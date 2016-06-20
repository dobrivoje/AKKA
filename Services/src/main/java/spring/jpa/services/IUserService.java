package spring.jpa.services;

import ent.Users;
import java.util.List;

public interface IUserService {

    List<Users> getAllUsers();

    Users getUser(long ID);

    void insertNewUser(String name, String surname, String username);

}
