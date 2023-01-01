import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


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

    Connection connection;
    public void readDB(){
        try {

            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connection = DriverManager.getConnection("jdbc:ucanaccess://DB\\Bank.accdb");//Establishing Connection
            System.out.println("Connected Successfully");


            PreparedStatement preparedStatement1= connection.prepareStatement("select * from Employee");
            //Setting values for Each Parameter
            ResultSet resultSet1=preparedStatement1.executeQuery();

            while(resultSet1.next()){
                String firstName=resultSet1.getString("firstName");
                String lastName=resultSet1.getString("lastName");
                String userPass=resultSet1.getString("userPass");
                String employeeID=resultSet1.getString("employeeID");

                Employee E = new Employee(firstName, lastName, userPass, employeeID);
                employees.add(E);
                System.out.println("employee [ " +" firstName :"+firstName +" lastName :"+ lastName +" userPass :" +" ***** " +" employeeID :"+ employeeID + " ] added");

            }

            PreparedStatement preparedStatement2= connection.prepareStatement("select * from dateTime");
            //Setting values for Each Parameter
            ResultSet resultSet2=preparedStatement2.executeQuery();


            while(resultSet2.next()){
                int date=resultSet2.getInt("dateTimeST");
                Accounts.date = date ;

                System.out.println("refresh date to { " +date + " }");

            }

            PreparedStatement preparedStatementCustomer= connection.prepareStatement("select * from customer");
            ResultSet resultSetCustomer=preparedStatementCustomer.executeQuery();

            PreparedStatement preparedStatementHistory= connection.prepareStatement("select * from history");
            ResultSet resultSetHistory=preparedStatementHistory.executeQuery();

            PreparedStatement preparedStatementAccountType= connection.prepareStatement("select * from accountType");
            ResultSet resultSetAccountType=preparedStatementAccountType.executeQuery();

            ArrayList<String> historyArray ;
            AccountType accountType;
            
            while(resultSetCustomer.next()){
                String firstName=resultSetCustomer.getString("firstName");
                String lastName=resultSetCustomer.getString("lastName");
                String userPass=resultSetCustomer.getString("userPass");
                String accountID=resultSetCustomer.getString("accountID");
                String phoneNumber=resultSetCustomer.getString("phoneNumber");
                String address=resultSetCustomer.getString("address");
                Double balance=resultSetCustomer.getDouble("balance");
                int linkAccountType=resultSetCustomer.getInt("linkAccountType");
                int linkHistory=resultSetCustomer.getInt("linkHistory");

                historyArray = new ArrayList<>();
                while(resultSetHistory.next()){
                    String history=resultSetHistory.getString("history");
                    int linkCustomerHistory=resultSetHistory.getInt("linkCustomer");
                    if (linkHistory == linkCustomerHistory) {
                        historyArray.add(history);
                    }
                }
                accountType = new RunType(1);
                while(resultSetAccountType.next()){
                    int interest=resultSetAccountType.getInt("interest");
                    int lockedTime=resultSetAccountType.getInt("lockedTime");
                    int createdDate=resultSetAccountType.getInt("createdDate");
                    int type=resultSetAccountType.getInt("type");
                    int linkCustomerAccountType=resultSetAccountType.getInt("linkCustomer");
                    if (linkAccountType == linkCustomerAccountType) {
                        if (type == 1) {
                            accountType = new ShortType(interest , createdDate);
                        }
                        else if (type == 2) {
                            accountType = new LongType(interest , lockedTime , createdDate);
                        }
                        else if (type == 3) {
                            accountType = new SaveType(createdDate);
                        }
                        else if (type == 4) {
                            accountType = new RunType(createdDate);
                        }
                        else{
                            accountType = new RunType(1);
                        }
                    }
                }

                Customer C = new Customer(firstName, lastName, userPass, accountID, phoneNumber, address, balance, accountType,historyArray);

                historyArray = new ArrayList<>();

                Accounts.customers.add(C);
                System.out.println("customer - firstName - "+ firstName +" - lastName - "+lastName+" - userPass - "+userPass+" -  accountID - "+accountID+" - phoneNumber - "+phoneNumber+" - address - "+address+" - balance - "+balance+" - linkAccountType - "+linkAccountType+" - linkHistory - "+linkHistory+" - added");
            }


        } catch (Exception e) {

            System.out.println("Error in connection");
            System.out.println(e);

        }
    }

    public void updateDB(){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connection = DriverManager.getConnection("jdbc:ucanaccess://DB\\Bank.accdb");//Establishing Connection
            System.out.println("Connected Successfully");


            // empty tables >> ha ha

            PreparedStatement preparedStatementDeleteEmployee=connection.prepareStatement("delete from Employee");
            preparedStatementDeleteEmployee.executeUpdate();
            PreparedStatement preparedStatementDeleteCustomer=connection.prepareStatement("delete from customer");
            preparedStatementDeleteCustomer.executeUpdate();
            PreparedStatement preparedStatementAccountType=connection.prepareStatement("delete from accountType");
            preparedStatementAccountType.executeUpdate();
            PreparedStatement preparedStatementHistory=connection.prepareStatement("delete from history");
            preparedStatementHistory.executeUpdate();
            PreparedStatement preparedStatementDateTime=connection.prepareStatement("delete from dateTime");
            preparedStatementDateTime.executeUpdate();
            System.out.println("Data deleted Successfully");

            //load tables >> data

            String firstName , lastName , userPass , employeeID , accountID , phoneNumber , address;
            double balance ; 
            int index;
            int interest , lockedTime , createdDate ;
            int type  = 1;
            String history ;

            for (int i = 0; i < employees.size(); i++) {
                firstName = employees.get(i).getFirstName();
                lastName = employees.get(i).getLastName();
                userPass = employees.get(i).getUserPass();
                employeeID = employees.get(i).getEmployeeID();
                PreparedStatement preparedStatement= connection.prepareStatement("INSERT into Employee(firstName,lastName,userPass,employeeID) values('"+firstName+"','"+lastName+"','"+userPass+"','"+employeeID+"')");
                preparedStatement.execute();
                System.out.println("Employee [ lastName - "+lastName+" - userPass - "+userPass+" - employeeID - " + employeeID + " - added ]");
            }


            for (int i = 0; i < Accounts.customers.size(); i++) {
                firstName = Accounts.customers.get(i).getFirstName();
                lastName = Accounts.customers.get(i).getLastName();
                userPass = Accounts.customers.get(i).getUserPass();
                accountID = Accounts.customers.get(i).getAccountID();
                phoneNumber = Accounts.customers.get(i).getPhoneNumber();
                address = Accounts.customers.get(i).getAddress();
                balance = Accounts.customers.get(i).getBalance();
                index = i + 1 ;
                PreparedStatement preparedStatement= connection.prepareStatement("INSERT into customer(firstName,lastName,userPass,accountID,phoneNumber,address,balance,linkAccountType,linkHistory) values('"+firstName+"','"+lastName+"','"+userPass+"','"+accountID+"','"+phoneNumber+"','"+address+"','"+balance+"','"+index+"','"+index+"')");
                preparedStatement.execute();

                System.out.println("Customer [ lastName - "+lastName+" - userPass - "+userPass+" - accountID - " + accountID +" - balance - " + balance + " - added ]");

            
                interest  = Accounts.customers.get(i).getAccountType().getInterest();
                lockedTime = Accounts.customers.get(i).getAccountType().getLockedTime();
                createdDate = Accounts.customers.get(i).getAccountType().getCreatedDate();
            
            
                if (Accounts.customers.get(i).getAccountType() instanceof ShortType) {
                    type = 1 ;
                }
                else if(Accounts.customers.get(i).getAccountType() instanceof LongType){
                    type = 2 ;
                }
                else if(Accounts.customers.get(i).getAccountType() instanceof SaveType){
                    type = 3 ;
                }
                else if(Accounts.customers.get(i).getAccountType() instanceof RunType){
                    type = 4 ;
                }
                PreparedStatement preparedStatementAccountTypeAdd= connection.prepareStatement("INSERT into accountType(interest,lockedTime,createdDate,linkCustomer,type) values('"+interest+"','"+lockedTime+"','"+createdDate+"','"+index+"','"+type+"')");
                preparedStatementAccountTypeAdd.execute();
            
            
                for (int j = 0; j < Accounts.customers.get(i).getHistory().size(); j++) {
                    history = Accounts.customers.get(i).getHistory().get(j);
                    PreparedStatement preparedStatementDateTimeAdd= connection.prepareStatement("INSERT into history(history,linkCustomer) values('"+history+"','"+index+"')");
                    preparedStatementDateTimeAdd.execute();
                }


            
            }


            PreparedStatement preparedStatementDateTimeAdd= connection.prepareStatement("INSERT into dateTime(dateTimeST) values('"+Accounts.date+"')");
            preparedStatementDateTimeAdd.execute();
            


        } catch (Exception e) {
            System.out.println("Error in connection !!!!!!!");
            System.out.println(e);
        }
        
    }




}
