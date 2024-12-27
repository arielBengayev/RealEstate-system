public class User {
    private String userName;
    private String password;
    private String phoneNumber;
    private boolean isAgent;

    public User(String userName, String password, String phoneNumber, boolean isAgent) {
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isAgent = isAgent;
    }
    public User(){}

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public boolean isAgent() { return isAgent; }
    public void setAgent(boolean agent) { isAgent = agent; }
    @Override
    public String toString() {
        return "name: " + userName + ", phoneNumber: " + phoneNumber;
    }
}
