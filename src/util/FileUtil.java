package util;

import java.io.*;
import java.util.ArrayList;

import model.User;

public class FileUtil {

    private static final String FILE_PATH = "data/users.txt";

    // SAVE USERS
    public static void saveUsers(ArrayList<User> users) {

        try {

            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(FILE_PATH));

            for (User user : users) {

                writer.write(
                        user.getUsername()
                                + ","
                                + user.getPassword()
                                + ","
                                + user.getBalance());

                writer.newLine();
            }

            writer.close();

        } catch (Exception e) {

            System.out.println("Error Saving Users");
        }
    }

    // LOAD USERS
    public static ArrayList<User> loadUsers() {

        ArrayList<User> users = new ArrayList<>();

        try {

            BufferedReader reader = new BufferedReader(
                    new FileReader(FILE_PATH));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                String username = data[0];
                String password = data[1];
                double balance = Double.parseDouble(data[2]);

                User user = new User(username, password);

                user.setBalance(balance);

                users.add(user);
            }

            reader.close();

        } catch (Exception e) {

            System.out.println("No Previous Data Found");
        }

        return users;
    }

    // SAVE TRANSACTION
    public static void saveTransaction(
            String username,
            String transaction) {

        try {

            String fileName = "data/transactions_"
                    + username
                    + ".txt";

            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(fileName, true));

            writer.write(transaction);

            writer.newLine();

            writer.close();

        } catch (Exception e) {

            System.out.println(
                    "Transaction Save Error");
        }
    }

    // READ TRANSACTIONS
    public static void readTransactions(
            String username) {

        try {

            String fileName = "data/transactions_"
                    + username
                    + ".txt";

            BufferedReader reader = new BufferedReader(
                    new FileReader(fileName));

            String line;

            while ((line = reader.readLine()) != null) {

                System.out.println(line);
            }

            reader.close();

        } catch (Exception e) {

            System.out.println(
                    "No Transactions Found");
        }
    }
}