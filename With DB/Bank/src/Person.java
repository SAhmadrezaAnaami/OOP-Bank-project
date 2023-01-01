public abstract class Person {


    private String firstName , lastName , userPass;


    public Person(String firstName, String lastName, String userPass) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userPass = userPass;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
    
}
