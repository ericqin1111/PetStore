package csu.web.mypetstore.service;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.persistence.AccountDao;
import csu.web.mypetstore.persistence.impl.AccountDaoImpl;

public class AccountService {
    private AccountDao accountDao;
    public AccountService(){
       this.accountDao= new AccountDaoImpl();
    }
    public Account getAccount(String username, String password) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        return this.accountDao.getAccountByUsernameAndPassword(account);
    }

    public boolean NameIsExist(Account account){
        Account CurrentAccount=accountDao.getAccountByUsername(account.getUsername());
        return CurrentAccount != null;
    }

    public int doRegister(Account account) throws Exception{
//        Account account1=accountDao.getAccountByUsernameAndPassword(account);
//        if(account1==null){
//            throw new RuntimeException("");
//        }
        Account existAccount= accountDao.getAccountByUsername(account.getUsername());
        if(existAccount!=null||account.getUsername()==null){
            throw new RuntimeException("用户名为空或已存在");

        }
        accountDao.insertAccount(account);
        return 0;
    };

    public int updateAccount(String username,String newPassword) throws Exception{

//        Account existAccount= accountDao.getAccountByUsername(account.getUsername());
        if(username==null){
            throw new RuntimeException("用户名为空");

        }
        accountDao.updateAccount(username,newPassword);
        return 0;
    };

}
