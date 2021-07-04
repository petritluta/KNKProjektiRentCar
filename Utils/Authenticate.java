package Utils;

import models.User;
import repositories.UserRepo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Authenticate {

    private static ArrayList<Notification> notifications=new ArrayList<Notification>();

    public static User login(String email,String password) throws Exception {
        User u=UserRepo.find(email);
        if(u==null) {
            notifications.add(new Notification("Invalid Credentials",NotificaitonType.ERROR));
            return null;
        }

        if(u.getPassword().equals(Security.hashPassword(password,u.getSalt())))
            return u;

        notifications.add(new Notification("Invalid Credentials",NotificaitonType.ERROR));
        return null;
    }



    public static ArrayList<Notification> getNotifications() {

        return notifications;
    }

    public static void clearNotificaitons()
    {
        notifications.removeAll(notifications);
    }


    public static User register(User u) throws Exception {
        if(userExists(u.getEmail()))
        {
            notifications.add(new Notification("User exits", NotificaitonType.WARNING));
            return null;
        }
        else{
            String salt=Security.generateSalt();
            u.setSalt(salt);
            String hashedPass=Security.hashPassword(u.getPassword(),u.getSalt());
            u.setPassword(hashedPass);
            u.setIs_admin(false);

            return UserRepo.create(u);
        }
    }



    public static boolean userExists(String email) throws Exception {
        return  UserRepo.find(email)!=null;
    }



}
