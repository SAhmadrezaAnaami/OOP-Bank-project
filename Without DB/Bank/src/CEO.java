import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class CEO extends Person {

    ArrayList<Employee> employees ;

    Random r ;

    public CEO(String firstName, String lastName, String userPass) {
        super(firstName, lastName, userPass);
        employees = new ArrayList<Employee>();
        r = new Random();
    }

    public void addEmployee(){

        employees.add(Employee.getEmployee());
        System.out.println("employee added");

    }

    public int SearchEmployees(String employeeID){

        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployeeID().equals(employeeID)) {
                return i ;
            }
        }
        return -1 ; 

    }

    public void removeEmployee(int index){
        employees.remove(index);
    }

    public void removeEmployee(String employeeID){
        employees.remove(SearchEmployees(employeeID));
    }
    private double value ; 
    public void goNextDay(){
        Accounts.date ++ ;
        System.out.println(Accounts.date + "");


        for (int i = 0; i < Accounts.customers.size(); i++) {

            if (Accounts.customers.get(i).getAccountType() instanceof ShortType) {
                value = ((ShortType)Accounts.customers.get(i).getAccountType()).getAdditionalValue();
                value += Accounts.customers.get(i).getBalance();
                ((ShortType)Accounts.customers.get(i).getAccountType()).setAdditionalValue(value);
            }
            if (Accounts.customers.get(i).getAccountType() instanceof LongType) {
                value = ((LongType)Accounts.customers.get(i).getAccountType()).getAdditionalValue();
                value += Accounts.customers.get(i).getBalance();
                ((LongType)Accounts.customers.get(i).getAccountType()).setAdditionalValue(value);
            }

        }

        for (int i = 0; i < Accounts.customers.size(); i++) {
            int cDate = Accounts.customers.get(i).getAccountType().getCreatedDate() + 1;
            Accounts.customers.get(i).getAccountType().setCreatedDate(cDate);
        }

    }
    int randIndex = -1 ;
    ArrayList<Customer> SVTemp ;
    int temp = 0 ;

    public void DrawAnAccountEvery30(){
        
        if (Accounts.date % 5 == 0 ) {
            SVTemp = new ArrayList<Customer>();

            for (int i = 0; i < Accounts.customers.size(); i++) {
                if (Accounts.customers.get(i).getAccountType() instanceof SaveType) {
                    SVTemp.add(Accounts.customers.get(i));
                }
            }
            if (randIndex == -1) {
                randIndex = r.nextInt(0 , SVTemp.size() - 1);
                SVTemp.get(randIndex).setBalance(SVTemp.get(randIndex).getBalance() + SVTemp.size()  - 1 );
                for (int i = 0; i < SVTemp.size(); i++) {
                    if (i != randIndex) {
                        SVTemp.get(i).setBalance(SVTemp.get(i).getBalance() - 1 );
                    }
                }            
            }
            else{
                temp = randIndex;
                randIndex = r.nextInt(0 , SVTemp.size() - 1);
                SVTemp.get(randIndex).setBalance(SVTemp.get(randIndex).getBalance() + SVTemp.size() - 1 );
                SVTemp.get(temp).setBalance(SVTemp.get(temp).getBalance() - SVTemp.size() + 1 );
            }
        }
    }
    private int locked ;
    private int createdDate ; 
    Double interest;
    public void addInterest(){

        for (int i = 0; i < Accounts.customers.size(); i++) {
            if (Accounts.customers.get(i).getAccountType().isLocked() == false) {
                if (Accounts.customers.get(i).getAccountType() instanceof ShortType) {
                    createdDate = ((ShortType)Accounts.customers.get(i).getAccountType()).getCreatedDate();
                    if (createdDate % 30 == 0) {
                        
                        value = ((ShortType)Accounts.customers.get(i).getAccountType()).getAdditionalValue();
                        interest = Double.valueOf(((ShortType)Accounts.customers.get(i).getAccountType()).getInterest());
                        if (value >= 0) {
                            value = value * (interest/ 100) ;
                            value = value +  Accounts.customers.get(i).getBalance();
                            Accounts.customers.get(i).setBalance(value);
                            ((ShortType)Accounts.customers.get(i).getAccountType()).setAdditionalValue(0);
                        }
                        else{
                            value = value * (interest/ 100) ;
                            value = Accounts.customers.get(i).getBalance() + value;
                            Accounts.customers.get(i).setBalance(value);
                            ((ShortType)Accounts.customers.get(i).getAccountType()).setAdditionalValue(0);
                        }
                        
                    }
                }
                if (Accounts.customers.get(i).getAccountType() instanceof LongType) {
                    createdDate = ((LongType)Accounts.customers.get(i).getAccountType()).getCreatedDate();
                    if (createdDate % 30 == 0) {
                        interest = Double.valueOf(((LongType)Accounts.customers.get(i).getAccountType()).getInterest());
                        value = ((LongType)Accounts.customers.get(i).getAccountType()).getAdditionalValue();
                        if (value >= 0) {

                            value = value * ( interest/ 200) ;
                            value = value +  Accounts.customers.get(i).getBalance();
                            Accounts.customers.get(i).setBalance(value);
                            ((LongType)Accounts.customers.get(i).getAccountType()).setAdditionalValue(0);
                            
                        }
                        else{
                            value = value * ( interest/ 200) ;
                            value =Accounts.customers.get(i).getBalance() + value;
                            Accounts.customers.get(i).setBalance(value);
                            ((LongType)Accounts.customers.get(i).getAccountType()).setAdditionalValue(0);
                            
                        }
                        
                    }
                }
                
            }

            if (Accounts.customers.get(i).getAccountType() instanceof LongType && Accounts.customers.get(i).getAccountType().isLocked() == true){
                createdDate = ((LongType)Accounts.customers.get(i).getAccountType()).getCreatedDate();
                locked = ((LongType)Accounts.customers.get(i).getAccountType()).getLockedTime();

                if (createdDate % locked == 0) {
                    value = ((LongType)Accounts.customers.get(i).getAccountType()).getAdditionalValue();
                    interest = Double.valueOf(((LongType)Accounts.customers.get(i).getAccountType()).getInterest());
                    value = (value * (interest/ 100)) ;
                    value = value +  Accounts.customers.get(i).getBalance();
                    Accounts.customers.get(i).setBalance(value);
                    ((LongType)Accounts.customers.get(i).getAccountType()).setAdditionalValue(0);

                    ((LongType)Accounts.customers.get(i).getAccountType()).setLocked(false);
                    ((LongType)Accounts.customers.get(i).getAccountType()).setLockedTime(30);

                }
            }


        }
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void showEmployee(){
        for (int i = 0; i < employees.size(); i++) {
            System.out.println(employees.get(i).toString());
        }
    }
    public void lockAccount(String accountID , int lockTime){
        int index = Accounts.SearchCustomer(accountID);
        ((LongType)Accounts.customers.get(index).getAccountType()).setLocked(true);
        ((LongType)Accounts.customers.get(index).getAccountType()).setLockedTime(lockTime);
    }
    public void unLockAccount(String accountID ){
        int index = Accounts.SearchCustomer(accountID);
        ((LongType)Accounts.customers.get(index).getAccountType()).setLocked(false);
        ((LongType)Accounts.customers.get(index).getAccountType()).setLockedTime(30);
    }
}
