import java.util.ArrayList;
import java.lang.reflect.Array;

public class Accounts {
    static ArrayList<Customer> customers = new ArrayList<>(); 
    static int  date = 1 ; 


    public static void addCustomer(){
        customers.add(Customer.getCustomer());
        System.out.println("customer added ");
    }

    public static int SearchCustomer(String accountID){

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getAccountID().equals(accountID)) {
                return i ;
            }
        }
        return -1 ; 

    }

    public static void removeCustomer(int index){
        customers.remove(index);
    }

    public static void removeCustomer(String accountID){
        customers.remove(SearchCustomer(accountID));
    }


    public static void showCustomers(){
        for (int i = 0; i < customers.size(); i++) {
            System.out.println(customers.get(i).toString());
        }
    }




}
