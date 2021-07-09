package models;


public class User {

    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String salt;
    private boolean is_admin;

    public User() {
        this(-1,"","","","","",true);
    }


    public User(String email,String password)
    {
        this.email=email;
        this.password=password;
    }

    public User(String first_name,String last_name,String email,String password)
    {
        this.first_name=first_name;
        this.last_name=last_name;
        this.email=email;
        this.password=password;
    }


    public User(int id,String first_name,String last_name,String email,String password,String salt,boolean is_admin)
    {
        this.id=id;
        this.first_name=first_name;
        this.last_name=last_name;
        this.email=email;
        this.password=password;
        this.salt=salt;
        this.is_admin=is_admin;
    }

    public User(String first_name,String last_name,String email,String password,String salt,boolean is_admin)
    {

        this.first_name=first_name;
        this.last_name=last_name;
        this.email=email;
        this.password=password;
        this.salt=salt;
        this.is_admin=is_admin;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }



}
