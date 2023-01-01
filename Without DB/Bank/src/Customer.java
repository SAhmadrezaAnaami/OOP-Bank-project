import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class Customer extends Person{
    private String accountID , phoneNumber , Address ; 
    private Double balance ;
    private ArrayList<String> history ;
    private AccountType accountType;



    public Customer(String firstName, String lastName, String userPass, String accountID, String phoneNumber,
            String address, Double balance, AccountType accountType) {
        super(firstName, lastName, userPass);
        this.accountID = accountID;
        this.phoneNumber = phoneNumber;
        Address = address;
        this.balance = balance;
        this.accountType = accountType;
        history = new ArrayList<String>();
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public ArrayList<String> getHistory() {
        return history;
    }

 

    public static Customer getCustomer(){
        Scanner S = new Scanner(System.in);

        System.out.println("Enter Customer First Name: ");
        String firstName = S.nextLine();
        System.out.println("Enter Customer Last Name: ");
        String lastName = S.nextLine();
        System.out.println("Enter Customer userPass: ");
        String userPass = S.nextLine();
        System.out.println("Enter Customer accountID: ");
        String accountID = S.nextLine();
        System.out.println("Enter Customer phoneNumber: ");
        String phoneNumber = S.nextLine();
        System.out.println("Enter Customer address: ");
        String address = S.nextLine();
        System.out.println("Enter Customer balance: ");
        double balance = Double.parseDouble(S.nextLine());
        System.out.println("Enter account Type 1:Short , 2:Long , 3:Save , 4:Run : ");
        int type = Integer.parseInt(S.nextLine());
        AccountType accountType ;
        int interest;

        switch (type) {
            case 1:
                System.out.println("enter Interest amount ;");
                interest = Integer.parseInt(S.nextLine());
                accountType = new ShortType(interest);
                break;
        
            case 2:
                System.out.println("enter Interest amount ;");
                interest = Integer.parseInt(S.nextLine());
                System.out.println("enter lockTime amount ;");
                int lockTime = Integer.parseInt(S.nextLine());                
                accountType = new LongType(interest , lockTime);
            break;
            case 3 :
                accountType = new SaveType();
                break;

            case 4 :
                accountType = new RunType();
                break;

            default:
                accountType = new RunType();
                break;
        }





        Customer C = new Customer(firstName , lastName, userPass, accountID , phoneNumber ,address , balance , accountType);
        return C ; 

    }

    public double Withdraw(int amount){
        if (this.getAccountType().isLocked() == false) {
            this.balance = this.balance - amount;
            history.add("Withdraw " + amount + " - day : " + Accounts.date);
            return amount ; 
        }
        else {
            System.out.println("your account is locked");
            return -1;
        }


    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public void sendMoney(Double amount , String accountID){
        int index = Accounts.SearchCustomer(accountID);
        if (this.getAccountType().isLocked() == false) {
            if (index > 0) {
                Accounts.customers.get(index).balance += amount;
            this.balance -= amount ;
            System.out.println("my balance" + this.balance);
            System.out.println("accountID balance" + Accounts.customers.get(index).balance);
            history.add("Send money to " + accountID + " " + amount);
            }
            else{
                System.out.println("was not found");
            }
        }
        else {
            System.out.println("your account is locked");
        }
    
    }
    public double addMoney(int amount){
        this.balance = this.balance + amount;
        history.add("added " + amount);
        return balance ; 
    }

    public String get5History(){
        String a = "";
        if (history.size() < 5) {
            for (int i = history.size() - 1; i >= 0 ; i--) {
                a += history.get(i) + " ";
            }
        }
        else{
            for (int i = history.size() - 1; i >= history.size() - 5 ; i--) {
                a += history.get(i) + " - ";
            }
        }
        return a ;
    }

    @Override
    public String toString() {
        return "Customer [accountID=" + accountID + ", phoneNumber=" + phoneNumber + ", Address=" + Address
                + ", balance=" + balance + ", history=" + history + ", accountType=" + accountType + "]";
    }   
}
