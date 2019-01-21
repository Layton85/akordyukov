package ru.job4j.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Bank - class describes storage of Users and their Accounts and operations over them.
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Bank {
    /** storage of Users and their Accounts */
    private TreeMap<User, List<Account>> usersStorage = new TreeMap<>();

    /**
     * Adding User into the storage
     * @param user - User for adding
     * @throws IllegalArgumentException when the specified User is null
     */
    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Specified user argument is null !");
        }
        this.usersStorage.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Deleting User from the storage
     * @param user - User for deleting
     * @throws IllegalArgumentException when the specified User is null
     */
    public void deleteUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Specidied user argument is null !");
        }
        this.usersStorage.remove(user);
    }

    /**
     * Adding Account to User in the storage
     * @param passport - User passport
     * @param account - User account
     * @throws IllegalArgumentException when the specified User passport or account is null
     */
    public void addAccountToUser(String passport, Account account) {
        if (passport == null || account == null) {
            throw new IllegalArgumentException("Specified argument is null !");
        }
        try {
            this.usersStorage.get(this.getUserFromStorage(passport)).add(account);
        } catch (NullPointerException nPE) {
            this.exceptionOuterPrint(nPE.toString(), "user with this passport was not found in storage.");
        }
    }

    /**
     * Deleting Account from the User in the storage
     * @param passport - User passport
     * @param account - User account
     * @throws IllegalArgumentException when the specified User passport or account is null
     */
    public void deleteAccountFromUser(String passport, Account account) {
        if (passport == null || account == null) {
            throw new IllegalArgumentException("Specified argument is null !");
        }
        try {
            this.usersStorage.get(this.getUserFromStorage(passport)).remove(account);
        } catch (NullPointerException nPE) {
            this.exceptionOuterPrint(nPE.toString(), "user with this passport was not found in storage.");
        }
    }

    /**
     * The method gets the list of User`s accounts.
     * @param passport - User passport
     * @return - The List of User`s Accounts.
     * If the User was not found or it have not any Accounts the method returns an empty List.
     * @throws IllegalArgumentException when the specified passport is null.
     */
    public List<Account> getUserAccounts(String passport) {
        if (passport == null) {
            throw new IllegalArgumentException("Specified argument passport is null !");
        }
        List<Account> accountsList = new ArrayList<>();
        User user = this.getUserFromStorage(passport);
        if (user != null) {
            accountsList = this.usersStorage.get(user);
        }
        return accountsList;
    }

    /**
     * The method transfers specified amount of money from the one account to another.
     * @param srcPassport - passport of source User
     * @param srcRequisites - requisites of the source Account
     * @param destPassport - passport of destination User
     * @param destRequisites - requisites of the destination Account
     * @param amount - amount of the transferred money
     * @return - true if the operation was successfully passed,
     *              false if the operation was not passed
     * @throws IllegalArgumentException if any one of parameters is null or the specified amount of money equals or less then 0
     */
    public boolean transferMoney(String srcPassport, String srcRequisites, String destPassport, String destRequisites, double amount) {
        if (srcPassport == null || srcRequisites == null || destPassport == null || destRequisites == null) {
            throw new IllegalArgumentException("Wrong source or destination was specified !");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Wrong amount was specified !");
        }
        boolean result = false;
        if (!srcRequisites.equals(destRequisites) && !srcPassport.equals(destPassport)) {
            Account srcAccount = this.getAccount(srcPassport, srcRequisites);
            Account destAccount = this.getAccount(destPassport, destRequisites);
            if (srcAccount != null && destAccount != null) {
                if (srcAccount.getValue() >= amount) {
                    srcAccount.setValue(srcAccount.getValue() - amount);
                    destAccount.setValue(destAccount.getValue() + amount);
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * Service private method which allows to get User object from the usersStorage using passport string.
     * @param passport - passport string of User which is looking for.
     * @return - the first occurence of the User with specified passport string in the usersStorage.
     * If no any Users was found the method returns null.
     * @throws IllegalArgumentException when the specified passport is null
     */
    private User getUserFromStorage(String passport) {
        if (passport == null) {
            throw new IllegalArgumentException("Specified passport argument is null !");
        }
        User result = null;
        for (User user : this.usersStorage.keySet()) {
            if (user.getPassport().equals(passport)) {
                result = user;
            }
        }
        return result;
    }

    /**
     * Service private method.
     * The method gets from the storage the Account for which is specified User`s passport and Account`s requisites
     * @param passport - User`s passport
     * @param requisites - Account`s requisites
     * @return - founded Account or null if Account was not found
     */
    private Account getAccount(String passport, String requisites) {
        if (passport == null) {
            throw new IllegalArgumentException("Specified passport argument is null !");
        }
        Account resAccount = null;
        List<Account> list = this.getUserAccounts(passport);
        if (list != null) {
            for (Account acc : list) {
                if (acc.getRequisites().equals(requisites)) {
                    resAccount = acc;
                    break;
                }
            }
        }
        return resAccount;
    }

    /**
     * Service private method.
     * The method printed exception information and some additional comments
     * @param excStr - exception information
     * @param addText - some additional comments
     */
    private void exceptionOuterPrint(String excStr, String addText) {
        StringBuilder outer = new StringBuilder();
        outer.append(excStr);
        if (addText != null) {
            outer.append(System.lineSeparator());
            outer.append(addText);
        }
        System.out.println(outer.toString());
    }
}
