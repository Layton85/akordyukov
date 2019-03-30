package ru.job4j.bank;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * BankTest.
 *
 * @author Alexander Kordyukov (alex-programm@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BankTest {
    /**
     * Service method which using in many tests.
     * It fills the Bank object to often found content.
     * @return - The Bank object in useful condition.
     */
    public Bank prepareBank() {
        Bank bank = new Bank();
        bank.addUser(new User("Alex", "333222"));
        bank.addAccountToUser("333222", new Account(100, "111000"));
        bank.addUser(new User("John", "444222"));
        bank.addAccountToUser("444222", new Account(100, "222000"));
        return bank;
    }

    @Test
    public void whenAddNewUser() {
        boolean result = false;
        User user = new User();
        Bank bank = new Bank();
        bank.addUser(user);
        try {
            result = bank.getUserAccounts("").isEmpty();
        } catch (NullPointerException exc) {
            StringBuilder outer = new StringBuilder();
            outer.append(exc.toString());
            outer.append(" in TreeMap usersStorage - User was not added.");
            System.out.println(outer.toString());
        } finally {
            assertThat(result, is(true));
        }
    }

    @Test
    public void whenDeleteUser() {
        Bank bank = this.prepareBank();
        bank.deleteUser(new User("Alex", "333222"));
        List<Account> expectedList = List.of();
        assertThat(bank.getUserAccounts("333222").equals(expectedList), is(true));
    }

    @Test
    public void whenAddAccountToUser() {
        User user = new User("Alex", "333222");
        Account acc = new Account(0, "111000");
        Bank bank = new Bank();
        bank.addUser(user);
        bank.addAccountToUser("333222", new Account(0, "111000"));
        List<Account> accountsList = bank.getUserAccounts("333222");
        assertThat(accountsList.size() == 1 && accountsList.get(0).equals(acc), is(true));
    }

    @Test
    public void whenDeleteAccountFromUser() {
        User user = new User("Alex", "333222");
        Bank bank = new Bank();
        bank.addUser(user);
        bank.addAccountToUser("333222", new Account(0, "111000"));
        bank.deleteAccountFromUser("333222", new Account(0, "111000"));
        List<Account> accountsList = bank.getUserAccounts("333222");
        assertThat(accountsList != null && accountsList.isEmpty(), is(true));
    }

    @Test
    public void whenGetUserAccountsNormally() {
        User user = new User("Alex", "333222");
        Bank bank = new Bank();
        bank.addUser(user);
        bank.addAccountToUser("333222", new Account(0, "111000"));
        List<Account> expectedList = List.of(new Account(0, "111000"));
        List<Account> resultList = bank.getUserAccounts("333222");
        assertThat(resultList != null && resultList.equals(expectedList), is(true));
    }

    @Test
    public void whenGetUserAccountsThenReturnsEmptyListBecauseOfUserWasNotFoundInTheStorage() {
        Bank bank = new Bank();
        List<Account> expectedList = List.of();
        List<Account> resultList = bank.getUserAccounts("333222");
        assertThat(resultList != null && resultList.equals(expectedList), is(true));
    }

    @Test
    public void whenTransferMoney() {
        Bank bank = this.prepareBank();
        boolean result = bank.transferMoney("333222", "111000", "444222", "222000", 50);
        assertThat(
                bank.getUserAccounts("333222").get(0).getValue() == 50
                        && bank.getUserAccounts("444222").get(0).getValue() == 150
                            && result,
                is(true)
        );
    }

    @Test
    public void whenTransferTooMuchMoney() {
        Bank bank = this.prepareBank();
        boolean result = bank.transferMoney("333222", "111000", "444222", "222000", 1000);
        assertThat(
                bank.getUserAccounts("333222").get(0).getValue() == 100
                        && bank.getUserAccounts("444222").get(0).getValue() == 100
                            && !result,
                is(true)
        );
    }
}
