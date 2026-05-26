package service;

import java.util.ArrayList;
import util.SecurityUtil;
import model.User;
import util.FileUtil;

public class BankService {

    private ArrayList<User> users;

    public BankService() {

        users = FileUtil.loadUsers();
    }

    // REGISTER
    public boolean register(String username,
            String password) {

        for (User user : users) {

            if (user.getUsername()
                    .equals(username)) {

                return false;
            }
        }
        String hashedPassword = SecurityUtil.hashPassword(password);

        users.add(
                new User(
                        username,
                        hashedPassword));

        FileUtil.saveUsers(users);

        return true;
    }

    // LOGIN
    public User login(String username,
            String password) {

        String hashedPassword = SecurityUtil.hashPassword(password);

        for (User user : users) {

            if (user.getUsername()
                    .equals(username)) {

                // CHECK LOCK
                if (user.isLocked()) {

                    System.out.println(
                            "Account Locked!");

                    return null;
                }

                // PASSWORD CHECK
                if (user.getPassword()
                        .equals(hashedPassword)) {

                    user.resetLoginAttempts();

                    return user;
                } else {

                    user.incrementLoginAttempts();

                    int attemptsLeft = 3 - user.getLoginAttempts();

                    System.out.println(
                            "Wrong Password!");

                    System.out.println(
                            "Attempts Left: "
                                    + attemptsLeft);

                    if (user.getLoginAttempts() >= 3) {

                        user.lockAccount();

                        System.out.println(
                                "Account Locked!");
                    }

                    return null;
                }
            }
        }

        return null;
    }

    // SAVE AFTER TRANSACTION
    public void saveData() {

        FileUtil.saveUsers(users);
    }

    public ArrayList<User> getAllUsers() {

        return users;
    }

    public double getTotalBankBalance() {

        double total = 0;

        for (User user : users) {

            total += user.getBalance();
        }

        return total;
    }
    public User searchUser(String username) {

    for(User user : users) {

        if(user.getUsername()
                .equals(username)) {

            return user;
        }
    }

    return null;
}
public boolean deleteUser(String username) {

    for(User user : users) {

        if(user.getUsername()
                .equals(username)) {

            users.remove(user);

            FileUtil.saveUsers(users);

            return true;
        }
    }

    return false;
}
}