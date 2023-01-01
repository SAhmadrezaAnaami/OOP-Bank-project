import java.lang.reflect.Array;
import java.util.Scanner;

public class menu {
 
    private CEO ceo ;


    private int option ; 

    private Scanner S = new Scanner(System.in);
    public menu() {
        ceo = new CEO("ahmad", "anaami", "123");
    }


    public void showMainMenu() {
        do {
            System.out.println("---------------------------------------------");
            System.out.println();
            System.out.println("Enter as CEO : 1");
            System.out.println("Enter as employee : 2");
            System.out.println("Enter as customer : 3");
            System.out.println("For Quit : 4");
            System.out.println();
            System.out.println("---------------------------------------------");

            option = Integer.parseInt(S.nextLine());
            process(option);



        } while (option != 4);
    }


    public void process (int option){

        switch (option) {
            case 1:
                CEOPass();
                break;
            case 2:
                EmployeePass();
                break;
            case 3:
                customerPass();
                break;
        }
    }
    private String user , pass ; 


    
    public void CEOPass(){
        String ceoOption;
        do {   
            System.out.println("plz Enter user name :");
            user = S.nextLine();
            System.out.println("plz Enter user pass :");
            pass = S.nextLine();
            if (ceo.getFirstName().equals(user) && ceo.getUserPass().equals(pass)) {
                do {
                    
                    System.out.println("---------------------------------------------");
                    System.out.println();
                    System.out.println("add employee : 1");
                    System.out.println("remove employee : 2");
                    System.out.println("ghorekeshy : 3");
                    System.out.println("add interest : 4");
                    System.out.println("go next day : 5");
                    System.out.println("show Employee : 6");
                    System.out.println("show customers : 7");
                    System.out.println("lock account : 8");
                    System.out.println("unlock account : 9");
                    System.out.println("For Quit : 10");
                    System.out.println();
                    System.out.println("---------------------------------------------");

                    System.out.print("enter option :");
                    ceoOption = S.nextLine();


                    ceoOptionProcess(ceoOption);

                } while (!ceoOption.equals("10"));
                break;
            }
            else{
                System.out.println("user or pass is incorrect / wanna try again say 1 for quit say 0");
                option = Integer.parseInt(S.nextLine());
            }

        } while (option != 0);
    }

    public void ceoOptionProcess(String option){
        switch (option) {
            case "1":
                ceo.addEmployee();
                break;
            case "2":
                System.out.println("Enter employee ID");
                ceo.removeEmployee(S.nextLine());
                break;
            case "3":
                ceo.DrawAnAccountEvery30();
                break;
            case "4":
                ceo.addInterest();
                break;
            case "5":
                ceo.goNextDay();
                break;
            case "6":
                ceo.showEmployee();
                break;
            case "7":
                Accounts.showCustomers();
                break;
            case "8":
                System.out.println("Enter account ID");
                String id = S.nextLine();
                System.out.println("Enter lockTime");
                int time = Integer.parseInt(S.nextLine());
                ceo.lockAccount(id, time);
                break;
            case "9":
                System.out.println("Enter account ID");
                String id2 = S.nextLine();
                ceo.unLockAccount(id2);
                break;
        }
    }

    int employeeIndex;
    int employeeOption;
    public void EmployeePass(){
        do {   
            System.out.println("plz Enter ID :");
            user = S.nextLine();
            System.out.println("plz Enter user pass :");
            pass = S.nextLine();
            for (int i = 0; i < ceo.getEmployees().size(); i++) {
                if ((ceo.getEmployees().get(i).getEmployeeID()).equals(user) && (ceo.getEmployees().get(i).getUserPass().equals(pass))) {
                    employeeIndex = i;
                    break;
                }
                else{
                    employeeIndex = -1;
                }
            }
            if (employeeIndex != -1) {
                do {
                    System.out.println("---------------------------------------------");
                    System.out.println();
                    System.out.println("add account : 1");
                    System.out.println("remove account : 2");
                    System.out.println("show customers : 3");
                    System.out.println("option------ : 4");
                    System.out.println("For Quit : 5");
                    System.out.println();
                    System.out.println("---------------------------------------------");

                    employeeOption = Integer.parseInt(S.nextLine());
                    employeeOptionProcess(employeeOption);


                } while (employeeOption != 5);
                break;
            }
            else{
                System.out.println("user or pass is incorrect / wanna try again say 1 for quit say 0");
                option = Integer.parseInt(S.nextLine());
            }


        } while (option != 0);
    }
    public void employeeOptionProcess(int option){
        switch (option) {
            case 1:
                Accounts.addCustomer();
                break;
            case 2:
                System.out.println("Enter account ID");
                Accounts.removeCustomer(S.nextLine());
                break;

            case 3:
                Accounts.showCustomers();
                break;
            case 4:
                break;

        }
    }

    int customerIndex;
    int customerOption;
    public void customerPass(){
        do {   
            System.out.println("plz Enter account ID :");
            user = S.nextLine();
            System.out.println("plz Enter user pass :");
            pass = S.nextLine();
            for (int i = 0; i < Accounts.customers.size(); i++) {
                if ((Accounts.customers.get(i).getAccountID()).equals(user) && (Accounts.customers.get(i).getUserPass().equals(pass))) {
                    customerIndex = i;
                    break;
                }
                else{
                    customerIndex = -1;
                }
            }
            if (customerIndex != -1) {
                do {
                    System.out.println("---------------------------------------------");
                    System.out.println();
                    System.out.println("see balance : 1");
                    System.out.println("add money : 2");
                    System.out.println("Withdraw : 3");
                    System.out.println("send Money : 4");
                    System.out.println("get 5 History : 5");
                    System.out.println("option------ : 6");
                    System.out.println("For Quit : 7");
                    System.out.println();
                    System.out.println("---------------------------------------------");
                    customerOption = Integer.parseInt(S.nextLine());
                    customerOptionProcess(customerOption , customerIndex );


                } while (customerOption != 7);
                break;
            }
            else{
                System.out.println("user or pass is incorrect / wanna try again say 1 for quit say 0");
                option = Integer.parseInt(S.nextLine());
            }


        } while (option != 0);
    }

    public void customerOptionProcess(int option , int index){
        switch (option) {
            case 1:
                System.out.println("your balance : " + Accounts.customers.get(index).getBalance());
                break;
            case 2:
                System.out.println("Enter money amount :");
                Accounts.customers.get(index).addMoney(Integer.parseInt(S.nextLine()));
                break;
            case 3:
                System.out.println("Enter money amount :");
                Accounts.customers.get(index).Withdraw(Integer.parseInt(S.nextLine()));
                break;
            case 4:
                System.out.println("Enter money amount :");
                Double amount = Double.valueOf(S.nextLine());
                System.out.println("Enter accountID amount :");
                String accountID = S.nextLine();
                Accounts.customers.get(index).sendMoney(amount, accountID);
                break;
            case 5:
                System.out.println(Accounts.customers.get(index).get5History());
                break;
            case 6:
                break;

        }
    }





    public static void main(String[] args) {
        menu m = new menu();
        m.showMainMenu();
    }
}
