import java.util.Scanner;

import model.User;
import service.BankService;
import java.util.InputMismatchException;
import util.FileUtil;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BankService bank = new BankService();

        while (true) {

            System.out.println("\n===== BANKING SYSTEM =====");

            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");

            System.out.print("Choose option: ");

            int choice;

            try {

                choice = sc.nextInt();

            } catch (InputMismatchException e) {

                System.out.println(
                        "Invalid Input! Numbers Only.");

                sc.nextLine();

                continue;
            }
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter Username: ");
                    String regUser = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String regPass = sc.nextLine();

                    boolean registered = bank.register(regUser, regPass);

                    if (registered) {
                        System.out.println("Registration Successful");
                    } else {
                        System.out.println("Username Already Exists");
                    }

                    break;

                case 2:

                    System.out.print("Enter Username: ");
                    String logUser = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String logPass = sc.nextLine();

                    User currentUser = bank.login(logUser, logPass);

                    if (currentUser != null) {

                        System.out.println("Login Successful");

                        userMenu(currentUser, sc, bank);
                    } else {
                        System.out.println("Invalid Credentials");
                    }

                    break;
                case 3:

                    System.out.print(
                            "Enter Admin Username: ");

                    String adminUser = sc.nextLine();

                    System.out.print(
                            "Enter Admin Password: ");

                    String adminPass = sc.nextLine();

                    if (adminUser.equals("admin")
                            &&
                            adminPass.equals("admin123")) {

                        adminMenu(bank, sc);
                    } else {

                        System.out.println(
                                "Invalid Admin Credentials");
                    }

                    break;
                case 4:

                    System.out.println("Thank You");
                    System.exit(0);

                default:

                    System.out.println("Invalid Option");
            }
        }
    }

    public static void userMenu(User user,
            Scanner sc,
            BankService bank) {

        while (true) {

            System.out.println("\n===== USER MENU =====");

            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Transaction History");
            System.out.println("5. Logout");

            System.out.print("Choose option: ");

            int choice;

            try {

                choice = sc.nextInt();

            } catch (InputMismatchException e) {

                System.out.println(
                        "Invalid Input! Numbers Only.");

                sc.nextLine();

                continue;
            }

            switch (choice) {

                case 1:

                    try {

                        System.out.print(
                                "Enter Amount: ₹");

                        double deposit = sc.nextDouble();

                        if (deposit <= 0) {

                            System.out.println(
                                    "Invalid Amount!");

                            break;
                        }

                        user.deposit(deposit);

                        bank.saveData();

                        System.out.println(
                                "Amount Deposited");

                    } catch (InputMismatchException e) {

                        System.out.println(
                                "Numbers Only!");

                        sc.nextLine();
                    }

                    break;
                case 2:

                    try {

                        System.out.print(
                                "Enter Amount: ₹");

                        double withdraw = sc.nextDouble();

                        if (withdraw <= 0) {

                            System.out.println(
                                    "Invalid Amount!");

                            break;
                        }

                        boolean success = user.withdraw(withdraw);

                        if (success) {

                            bank.saveData();

                            System.out.println(
                                    "Withdrawal Successful");
                        } else {

                            System.out.println(
                                    "Insufficient Balance");
                        }

                    } catch (InputMismatchException e) {

                        System.out.println(
                                "Numbers Only!");

                        sc.nextLine();
                    }

                    break;
                case 3:

                    System.out.println("Balance: ₹"
                            + user.getBalance());

                    break;

                case 4:

                    System.out.println(
                            "\n===== TRANSACTION HISTORY =====");

                    FileUtil.readTransactions(
                            user.getUsername());

                    break;

                case 5:

                    System.out.println("Logged Out");
                    return;

                default:

                    System.out.println("Invalid Option");
            }
        }
    }

    public static void adminMenu(
            BankService bank,
            Scanner sc) {

        while (true) {

            System.out.println(
                    "\n===== ADMIN PANEL =====");

            System.out.println(
                    "1. View All Users");

            System.out.println(
                    "2. Total Bank Balance");

            System.out.println(
                    "3. Search User");

            System.out.println(
                    "4. Delete User");

            System.out.println(
                    "5. Exit Admin Panel");

            System.out.print(
                    "Choose Option: ");

            int choice;

            try {

                choice = sc.nextInt();
                sc.nextLine();

            } catch (Exception e) {

                System.out.println(
                        "Numbers Only!");

                sc.nextLine();

                continue;
            }

            switch (choice) {

                case 1:

                    System.out.println(
                            "\n===== USERS =====");

                    for (User user : bank.getAllUsers()) {

                        System.out.println(
                                "Username: "
                                        + user.getUsername());

                        System.out.println(
                                "Balance: ₹"
                                        + user.getBalance());

                        System.out.println(
                                "----------------");
                    }

                    break;

                case 2:

                    System.out.println(
                            "Total Bank Balance: ₹"
                                    + bank.getTotalBankBalance());

                    break;

                case 3:

                    System.out.print(
                            "Enter Username: ");

                    String searchName = sc.nextLine();

                    User foundUser = bank.searchUser(searchName);

                    if (foundUser != null) {

                        System.out.println(
                                "User Found");

                        System.out.println(
                                "Balance: ₹"
                                        + foundUser.getBalance());
                    } else {

                        System.out.println(
                                "User Not Found");
                    }

                    break;

                case 4:

                    System.out.print(
                            "Enter Username: ");

                    String deleteName = sc.nextLine();

                    boolean deleted = bank.deleteUser(deleteName);

                    if (deleted) {

                        System.out.println(
                                "User Deleted");
                    } else {

                        System.out.println(
                                "User Not Found");
                    }

                    break;

                case 5:

                    return;

                default:

                    System.out.println(
                            "Invalid Option");
            }
        }
    }
}