package dome.test.litepaldome.model;

import org.litepal.crud.LitePalSupport;

public class UserSql extends LitePalSupport {
    private int id;
    private String account;
    private String password;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserSql{" + "id=" + id + ", account='" + account + '\'' + ", password='" + password + '\'' + ", name='" + name + '\'' + '}';
    }
}
