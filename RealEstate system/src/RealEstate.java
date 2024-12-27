import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class RealEstate {
    Scanner scanner = new Scanner(System.in);
    private Stack<User> users = new Stack<User>();
    private Stack<Property> properties = new Stack<>();
    private Stack<Address> addresses = new Stack<>();

    public RealEstate(Stack<User> users, Stack<Address> addresses, Stack<Property> properties) {
        this.users = users;
        this.addresses = addresses;
        this.properties = properties;
    }

    public Stack<User> getUsers() { return users; }
    public void setUsers(Stack<User> users) { this.users = users; }

    // main menu //
    public void mainMenu(int userChoice){
        switch (userChoice){
            case 1 -> createAccount();
            case 2 -> logIn();
            case 3 -> exit();
        }
    }
    public int printMainMenu(){
        System.out.println("Welcome to Real-estate system!");
        System.out.println("Press 1 to SignUp");
        System.out.println("Press 2 to Login");
        System.out.println("Press 3 to exit");
        int userChoice = scanner.nextInt();
        return userChoice;
    }

    // sign up //
    public void createAccount() {

        User userToAdd = new User();

        System.out.print("Please enter a user name: ");
        String userName = scanner.next();

        while (isUserNameExist(userName)) userName = scanner.next();
        userToAdd.setUserName(userName);

        System.out.print("Please enter a strong password (includes numbers, min 8 characters): ");
        String password = scanner.next();

        while (!isStrong(password)) password = scanner.next();
        userToAdd.setPassword(password);

        System.out.print("Please enter a israeli phone number: ");
        String phoneNumber = scanner.next();

        while (!isValid(phoneNumber) || isPhoneNumberExist(phoneNumber))
            phoneNumber = scanner.next();

        userToAdd.setPhoneNumber(phoneNumber);

        System.out.print("Are you agent (yes/no)? ");
        String agent = scanner.next();

        while (!isAgentHandle(agent)) agent = scanner.next();

        if (isAgent(agent)) userToAdd.setAgent(true);
        else userToAdd.setAgent(false);

        users.push(userToAdd);
        System.out.println("---------Login----------");

        logIn();

    }
    public boolean isUserNameExist(String userName) {

        for (User user : this.users) {
            if (userName.equals(user.getUserName())) {
                System.out.print("User name already exist, try again: ");
                return true;
            }
        } return false;

    }
    public boolean isPhoneNumberExist(String phoneNumber) {

        for (User user : this.users) {
            if (phoneNumber.equals(user.getPhoneNumber())) {
                System.out.print("This phone number is already used, try again: ");
                return true;
            }
        } return false;

    }
    public boolean isStrong(String password) {

        if (password.length() < 8) {
            System.out.print("Not strong enough password, try again: ");
            return false;
        }
        for (char i : password.toCharArray())
            if (i >= '0' && i <= '9') return true;
        System.out.print("Not strong enough password, try again: ");
        return false;

    }
    public boolean isValid(String phoneNumber) {

        if (phoneNumber.length() < 10 || !phoneNumber.startsWith("05")) {
            System.out.print("Illegal phone number, try again: ");
            return false;
        } return true;

    }
    public boolean isAgentHandle(String agent) {

        if (agent.equals("yes") || agent.equals("no")) return true;
        System.out.print("Only yes or no, try again: ");
        return false;
    }
    public boolean isAgent(String agent) {

        if (agent.equals("yes")) return true;
        return false;

    }

    // login //
    public void logIn() {

        System.out.print("Please enter your user name: ");
        String userName = scanner.next();
        System.out.print("Please enter your password: ");
        String password = scanner.next();

        while (validUserNameAndPassword(userName, password) == null) {
            System.out.println("Incorrect password or user name, try again");
            System.out.print("Please enter your user name: ");
            userName = scanner.next();
            System.out.print("Please enter your password: ");
            password = scanner.next();
        }

        System.out.println("--------Menu--------");

        logInMenu(validUserNameAndPassword(userName, password));

    }
    public User validUserNameAndPassword(String userName, String password) {

        for (User user : this.users) {
            if (user.getUserName().equals(userName)) {
                if (user.getPassword().equals(password)) return user;
            }
        } return null;

    }
    public void logInMenu(User user) {

        printMenu(user);
        int userChoice = scanner.nextInt();

        switch (userChoice) {
            case 1 -> postNewProperty(user);
            case 2 -> removeProperty(user);
            case 3 -> printMyProperties(user);
            case 4 -> printAllProperties(user);
            case 5 -> search(user);
            case 6 -> backToMainMenu();
        }

    }
    public void printMenu(User user) {

        System.out.println("Hello " + user.getUserName() + " what do you want to do?");
        System.out.println("press 1 to post new property");
        System.out.println("press 2 to delete exist property");
        System.out.println("press 3 to show all your properties");
        System.out.println("press 4 to show all properties in the system");
        System.out.println("press 5 to search property by parameters");
        System.out.println("press 6 to log out and back to maim menu");

    }

    // post new property //
    public void postNewProperty(User user) {

        if (!canPostProperty(user)) {
            System.out.println("-------------------------------------------");
            System.out.print("You overed your posts limit");
            System.out.println("--------Menu--------");
            logInMenu(validUserNameAndPassword(user.getUserName(), user.getPassword()));
        }

        Property propertyToAdd = new Property(user);
        System.out.println("-----------\nAll cities:\n-----------");
        Stack<String> userAddresses = new Stack<>();

        for (Address address : this.addresses) {
            if (!userAddresses.contains(address.getCity())) {
                userAddresses.push(address.getCity());
                System.out.println(address.getCity());
            }
        }

        isCityExist(propertyToAdd);

        if (propertyType(propertyToAdd) == 1) {
            System.out.print("Which floor the property? ");
            propertyToAdd.setFloor(scanner.nextInt());
        }

        System.out.print("How many rooms the property have? ");
        propertyToAdd.setRooms(scanner.nextInt());
        System.out.print("What is the property number? ");
        propertyToAdd.setPropertyNumber(scanner.nextInt());
        System.out.print("The property is for rent? (yes/no) ");

        if (scanner.next() == "yes") propertyToAdd.setForRent(true);
        else propertyToAdd.setForRent(false);

        System.out.print("Please enter the property price ");
        propertyToAdd.setPrice(scanner.nextInt());

        this.properties.push(propertyToAdd);
        System.out.println("-------------------------------------------");
        System.out.println("Your property successfully added!");

        System.out.println("--------Menu--------");
        logInMenu(validUserNameAndPassword(user.getUserName(), user.getPassword()));

    }
    public boolean canPostProperty(User user) {

        if (user.isAgent()) {
            int agentUser = 0;

            for (Property property : this.properties) {
                if (property.getUser() == user) agentUser++;
                if (agentUser == 10) return false;
            }
        } else {
            int regularUser = 0;

            for (Property property : this.properties) {
                if (property.getUser() == user) regularUser++;
                if (regularUser == 3) return false;
            }
        } return true;

    }
    public boolean isCityExist(Property property) {

        System.out.print("Please enter a city from the list: ");
        String city = scanner.next();
        Address address = new Address("","");

        for (Address address1 : this.addresses) {
            if (city.equals(address1.getCity())) {
                System.out.println("----------\nall streets\n----------");
                address.setCity(city);

                for (Address address2 : this.addresses) {
                    if (city.equals(address2.getCity()))
                        System.out.println(address2.getStreet());
                }

                isStreetExist(address, property);

                return true;
            }
        } return isCityExist(property);

    }
    public boolean isStreetExist(Address address, Property property) {

        System.out.print("Please enter a street from the list: ");
        String street = scanner.next();

        for (Address address1 : this.addresses) {
            if (street.equals(address1.getStreet())) {
                address.setStreet(street);
                this.addresses.push(address);
                property.setAddress(address);
                return true;
            }
        } return isStreetExist(address, property);

    }
    public int propertyType(Property property) {

        int type;

        do {
            System.out.println("Please choose a property type:");
            System.out.println("press 1 for regular building property");
            System.out.println("press 2 for penthouse property");
            System.out.println("press 3 for privet property");
            type = scanner.nextInt();
        } while (type != 1 && type != 2 && type != 3);

        if (type == 1) property.setType("regular building property");
        else if(type == 2) property.setType("penthouse property");
        else property.setType("privet property");

        return type;

    }

    // delete exist property // לסיים
    public void removeProperty(User user) {

        HashMap<Integer, Address> userProperties = new HashMap<Integer, Address>();

        for (int i=0; i< this.properties.size(); i++){
            if (properties.get(i).getUser() == user){
                userProperties.put(i, properties.get(i).getAddress());
            }
        }

        if (userProperties.isEmpty()) {
            System.out.println("-------------------------------------------");
            System.out.print("You have not posted any properties yet");
            System.out.println("--------Menu--------");
            logInMenu(validUserNameAndPassword(user.getUserName(), user.getPassword()));
        } else {
            System.out.println("Please choose a property to delete");
            int ord = 1;
            for (Address address : userProperties.values()){
                System.out.println(ord + ". " + address.toString());
                ord++;
            }
        }

        canDeleteProperty(userProperties, user);

    }

    public boolean canDeleteProperty(HashMap<Integer,Address> userProperties, User user) {

        System.out.print("Please enter a city: ");
        String city = scanner.next().toLowerCase();
        System.out.print("Please enter a street: ");
        String street = scanner.next().toLowerCase();

            for (Map.Entry<Integer, Address> entry : userProperties.entrySet()) {
                if (city.equals(entry.getValue().getCity()) && street.equals(entry.getValue().getStreet())) {
                    this.properties.remove(entry.getKey());
                    System.out.println("-------------------------------------------");
                    System.out.println("your property successfully removed!");
                    System.out.println("--------Menu--------");
                    logInMenu(validUserNameAndPassword(user.getUserName(), user.getPassword()));
                }
            }

        return canDeleteProperty(userProperties, user);

    }

    // show all my properties //
    public void printMyProperties(User user) {

        for (Property property : properties) {
            if (property.getUser().equals(user)){
                System.out.println("---------------");
                System.out.println(property.toString() + "\n---------------");
            }
        }

        System.out.println("--------Menu--------");
        logInMenu(validUserNameAndPassword(user.getUserName(), user.getPassword()));

    }

    // show all properties in the system //
    public  void printAllProperties(User user) {

        for (Property property : properties)
            System.out.println(property.toString() + "\n---------------");

        System.out.println("--------Menu--------");
        logInMenu(validUserNameAndPassword(user.getUserName(), user.getPassword()));

    }

    // search properties by parameters //
    public void search(User user) {

        System.out.print("Do you want to buy a property or rent?(buy/rent) ");
        String forRent = scanner.next();
        boolean forRentToBool = forRent.equals("rent");
        System.out.print("Which property do you want? (regular, penthouse, private) ");
        String type = scanner.next();
        System.out.print("How many rooms? ");
        int rooms = scanner.nextInt();
        System.out.print("Minimum price? ");
        int minPrice = scanner.nextInt();
        System.out.print("Maximum price? ");
        int maxPrice = scanner.nextInt();
        System.out.println("---------------");

        Stack<Property> searchedProperties = find(forRent, forRentToBool, type, rooms, minPrice, maxPrice);

        for (Property property : searchedProperties)
            System.out.println(property.toString() + "\n---------------");

        System.out.println("--------Menu--------");
        logInMenu(validUserNameAndPassword(user.getUserName(), user.getPassword()));

    }
    public Stack<Property> find(String forRent, boolean forRentToBool, String type, int rooms, int minPrice, int maxPrice) {

        Stack<Property> searchedProperties = new Stack<Property>();

        for (Property property : properties) {
            int countAns = 0;

            if (property.isForRent() == forRentToBool) countAns++;
            if (property.getType().equals(type)) countAns++;
            if (property.getRooms() == rooms) countAns++;
            if (property.getPrice() >= minPrice) countAns++;
            if (property.getPrice() <= maxPrice) countAns++;

            if (countAns == 5)
                searchedProperties.push(property);
        }

        return searchedProperties;

    }

    // back to main menu //
    public void backToMainMenu(){ mainMenu(printMainMenu()); }
    public  void  exit() {
        System.out.println("------------------------------------");
        System.out.println("Thank you for using our system!");
        System.out.println("------------------------------------");
        return;
    }

}