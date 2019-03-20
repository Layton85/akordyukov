package ru.job4j.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
     */
    public void addUser(User user) {
        this.usersStorage.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Deleting User from the storage
     * @param user - User for deleting
     */
    public void deleteUser(User user) {
        this.usersStorage.remove(user);
    }

    /**
     * Adding Account to User in the storage
     * @param passport - User passport
     * @param account - User account
     */
    public void addAccountToUser(String passport, Account account) {
        List<Account> list = usersStorage.get(this.getUserFromStorage(passport));
        if (list != null) {
            list.add(account);
        }
    }

    /**
     * Deleting Account from the User in the storage
     * @param passport - User passport
     * @param account - User account
     */
    public void deleteAccountFromUser(String passport, Account account) {
        List<Account> list = usersStorage.get(this.getUserFromStorage(passport));
        if (list != null) {
            list.remove(account);
        }
    }

    /**
     * The method gets the list of User`s accounts.
     * @param passport - User passport
     * @return - The List of User`s Accounts.
     * If the User was not found or it have not any Accounts the method returns an empty List.
     */
    public List<Account> getUserAccounts(String passport) {
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
     */
    public boolean transferMoney(String srcPassport, String srcRequisites, String destPassport, String destRequisites, double amount) {
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
     */
    private User getUserFromStorage(String passport) {
        return this.usersStorage.keySet().stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst()
                .orElse(null);
    }

    /**
     * Service private method.
     * The method gets from the storage the Account for which is specified User`s passport and Account`s requisites
     * @param passport - User`s passport
     * @param requisites - Account`s requisites
     * @return - founded Account or null if Account was not found
     */
    private Account getAccount(String passport, String requisites) {
        return this.getUserAccounts(passport).stream()
                .filter(account -> account.getRequisites().equals(requisites))
                .findFirst()
                .orElse(null);
    }
}
