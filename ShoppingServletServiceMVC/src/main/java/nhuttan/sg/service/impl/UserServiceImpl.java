package nhuttan.sg.service.impl;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;
import nhuttan.sg.service.EmailService;
import nhuttan.sg.dao.UserDao;
import nhuttan.sg.dao.impl.UserDaoImpl;
import nhuttan.sg.model.User;
import nhuttan.sg.service.UserService;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        User user = this.get(username);
        if (user != null && password.equals(user.getPassWord())) {
            return user;
        }
        return null;
    }

    @Override
    public User get(String username) {
        return userDao.get(username);
    }
    
    @Override
    public boolean register(String email, String password, String username, String fullname, String phone) {
        if (userDao.checkExistUsername(username)) {
            return false;
        }
        if (userDao.checkExistEmail(email)) {
            return false;
        }
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        userDao.insert(new User(email, username, fullname, password, null, 0, phone, date));
        return true;
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }
    @Override
    public void requestPasswordReset(String email) {
        User user = userDao.findByEmail(email);
        if (user != null) {
            String token = UUID.randomUUID().toString();
            
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.HOUR, 1);
            Timestamp expiryDate = new Timestamp(cal.getTime().getTime());
            
            user.setResetPasswordToken(token);
            user.setTokenExpiryDate(expiryDate);
            userDao.update(user);
            
            EmailService emailService = new EmailService();
            String resetLink = "http://localhost:8080/ShoppingServletServiceMVC/reset-password?token=" + token;
            String subject = "Yeu Cau Dat Lai Mat Khau";
            String body = "Xin chao " + user.getFullName() + ",\n\n"
                        + "Ban da yeu cau dat lai mat khau. Vui long nhan vao link duoi day de tiep tuc:\n"
                        + resetLink + "\n\n"
                        + "Link se het han sau 1 gio. Neu ban khong yeu cau, vui long bo qua email nay.\n\n"
                        + "Tran trong.";
            emailService.sendEmail(email, subject, body);
        }
    }
    @Override
    public User validatePasswordResetToken(String token) {
        User user = userDao.findByResetToken(token);
        if (user == null) {
            return null; 
        }

        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (user.getTokenExpiryDate().before(now)) {
            return null; 
        }
        
        return user;
    }
    @Override
    public void resetPassword(String token, String newPassword) {
        User user = validatePasswordResetToken(token);
        if (user != null) {
            user.setPassWord(newPassword);
            user.setResetPasswordToken(null);
            user.setTokenExpiryDate(null);
            userDao.update(user);
        }
    }
}