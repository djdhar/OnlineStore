package User;
public class User
{
    public String username;
    public String name;
    public String email;
    public String gender;
    public String preference;
    public String password;

    public User(String username, String name, String email, String gender, String preference, String password){
        this.username=username;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.preference = preference;
        this.password = password;
    }
}