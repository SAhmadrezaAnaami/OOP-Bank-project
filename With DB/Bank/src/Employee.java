import java.util.Scanner;

public class Employee extends Person{
    private String employeeID ;
  
    public Employee(String firstName, String lastName, String userPass, String employeeID) {
        super(firstName, lastName, userPass);
        this.employeeID = employeeID;
    } 

    public static Employee getEmployee(){
        Scanner S = new Scanner(System.in);
        System.out.println("Enter employee First Name: ");
        String firstName = S.nextLine();
        System.out.println("Enter employee Last Name: ");
        String lastName = S.nextLine();
        System.out.println("Enter employee userPass: ");
        String userPass = S.nextLine();
        System.out.println("Enter employee employeeID: ");
        String employeeID = S.nextLine();

        

        Employee E = new Employee(firstName , lastName, userPass, employeeID);
        return E ; 

    }
    public String getEmployeeID() {
        return employeeID;
    }
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }


    public void addCustomer(){
        Accounts.addCustomer();
    }
    public void removeCustomer(String accountID){
        Accounts.removeCustomer(accountID);
    }
    public void removeCustomer(int index){
        Accounts.removeCustomer(index);
    }

    @Override
    public String toString() {
        return "Employee [ name= "+ this.getLastName()  +" employeeID= " + employeeID + "]";
    }


}
