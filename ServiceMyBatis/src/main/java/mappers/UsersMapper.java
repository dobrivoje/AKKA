package mappers;

import ent.Users;
import java.util.List;

public interface UsersMapper {

    List<Users> getAllUsers();

    Users getUser(long ID);

}
