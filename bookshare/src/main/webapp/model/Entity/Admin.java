public class Admin {
    private String userName;
    private String password;
    private String registeredDate;
 
     public String getUsername() {
         return userName;
     }
 
     public void setUsername(String userName) {
         this.userName = userName;
     }
 
     public String getPassword() {
         return password;
     }
 
     public void setPassword(String password) {
         this.password = password;
     }
 
     public String getRegisteredDate() {
         return registeredDate;
     }
 
     public void setRegisteredDate(String registeredDate) {
         this.registeredDate = registeredDate;
     }
 
     @Override
     public String toString() {
         return "Admin{" +
             "username='" + userName + '\'' +
             ", pasword='" + password + '\'' +
             ", registeredDate='" + registeredDate + "}";
     }
 }
 
 