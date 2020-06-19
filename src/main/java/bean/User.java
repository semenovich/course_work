package bean;

public class User extends Entity{
    private UserRole role;
    private String login;
    private String password;
    private boolean status;

    private String name;
    private String surname;


    public boolean getStatus() {
        return status;
    }

    public User setStatus(boolean status) {
        this.status = status;
        return this;
    }

    private String email;
    private String telephone;


    public UserRole getRole() {
        return role;
    }

    public User setRole(UserRole role) {
        this.role = role;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public User setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public User setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }
}
