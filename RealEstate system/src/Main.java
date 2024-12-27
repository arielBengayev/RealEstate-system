import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // users //
        User user1 = new User("ariel", "ariel123", "0521111111", false);
        User user2 = new User("moshe", "moshe123", "0523333333", false);
        Stack<User> users = new Stack<>();
        users.push(user1); users.push(user2);

        // addresses //
        Address address1 = new Address("ashkelon", "street1");
        Address address2 = new Address("tel aviv", "street1");
        Address address3 = new Address("tel aviv", "street2");
        Address address4 = new Address("jerusalem", "street1");
        Stack<Address> addresses = new Stack<>();
        addresses.push(address1); addresses.push(address2); addresses.push(address3); addresses.push(address4);

        // properties //
        Property property1 = new Property(address1, 3, 500000, "regular", false, 4, 2, user1);
        Property property2 = new Property(address2, 5, 1000000, "private", false, 5, 0, user2);
        Property property3 = new Property(address3, 3, 800000, "regular", false, 7, 3, user1);
        Stack<Property> properties = new Stack<>();
        properties.push(property1); properties.push(property2); properties.push(property3);

        RealEstate realEstate = new RealEstate(users, addresses, properties);

        realEstate.mainMenu(realEstate.printMainMenu());

    }
}