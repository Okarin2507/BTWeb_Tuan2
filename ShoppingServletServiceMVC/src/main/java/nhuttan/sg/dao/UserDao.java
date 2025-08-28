package nhuttan.sg.dao;
import nhuttan.sg.model.User;
public interface UserDao {

    void insert(User user);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
    
 
    User get(String username);
}