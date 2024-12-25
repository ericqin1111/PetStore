package csu.web.mypetstore.persistence;

import csu.web.mypetstore.domain.Account;

public interface AccountDao{
    Account getAccountByUsername(String username);

    Account getAccountByUsernameAndPassword(Account account);

    void insertAccount(Account account);

    void updateAccount(String username, String newPassword);

    void insertProfile(Account account);

    void insertSignon(Account account);



    void updateProfile(Account account);

    void updateSignon(Account account);
}
