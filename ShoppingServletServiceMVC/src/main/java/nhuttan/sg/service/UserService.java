package nhuttan.sg.service;

import nhuttan.sg.model.User;

public interface UserService {

    User login(String username, String password);
    User get(String username);

    void requestPasswordReset(String email);
    User validatePasswordResetToken(String token);
    void resetPassword(String token, String newPassword);
    boolean register(String email, String password, String username, String fullname, String phone);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
}