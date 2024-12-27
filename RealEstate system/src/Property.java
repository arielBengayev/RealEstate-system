public class Property {
    private Address address;
    private int rooms;
    private int price;
    private String type;
    private boolean forRent;
    private int propertyNumber;
    private int floor;
    private User user;

    public Property(Address address, int rooms, int price, String type, boolean forRent, int propertyNumber, int floor, User user) {
        this.address = address;
        this.rooms = rooms;
        this.price = price;
        this.type = type;
        this.forRent = forRent;
        this.propertyNumber = propertyNumber;
        this.floor = floor;
        this.user = user;
    }

    public Property(User user, Address address) {
        this.user = user;
        this.address = address;
    }

    public Property(User user) { this.user = user; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
    public int getRooms() { return rooms; }
    public void setRooms(int rooms) { this.rooms = rooms; }
    public double getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public boolean isForRent() { return forRent; }
    public void setForRent(boolean forRent) { this.forRent = forRent; }
    public int getPropertyNumber() { return propertyNumber; }
    public void setPropertyNumber(int houseNumber) { this.propertyNumber = houseNumber; }
    public int getFloor() { return floor; }
    public void setFloor(int floor) { this.floor = floor; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    @Override
    public String toString() {
        return "address - " + address.toString() + "\nrooms - " + rooms + "\nprice - " + price + "\ntype - " + type +
                "\npropertyNumber - " + propertyNumber + "\nfloor - " + floor + "\ncontact info - " + user.toString() +
                "\nforRent? " +(forRent ? "yes" : "no");
    }
}
