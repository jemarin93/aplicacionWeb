/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import java.sql.Connection;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam; 

/**
 *
 * @author alejandro.ochoa
 */
@WebService(serviceName = "WebServiceUser")
public class WebServiceUser {

    Connection connection;
    int id;
    String name;
    String email;
    String password;
    String gender;
    String address;
    String user;
    String lastName;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    } 
    
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    } 
    
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    } 
    
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return getUserQuery();
    }
    
    // Used to establish connection
    /*public Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");   
            connection = DriverManager.getConnection( "jdbc:mysql://jeffri.mysql.database.azure.com:3306/user","aplicacionWeb@jeffri","grados2021..");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return connection;
    }*/
    
    // Validar usuario
    private String getUserQuery() {
        try{
            String user = null; 
            /*connection = getConnection();
            Statement stmt=getConnection().createStatement();  
            ResultSet rs= stmt.executeQuery("select * from users where user = 'aochoa' and password = '123'");
            if(rs.next()) {
                user = rs.getInt("id") + " " + rs.getString("name") + " " + 
                rs.getString("email") + " " + rs.getString("gender") + " " + 
                rs.getString("address") + " " + rs.getString("password") + " " +   
                rs.getString("user") + " " + rs.getString("lastName");
                connection.close();;*/
                user = "1" + " aochoa" ;
                return user; 
            /*} else {
                connection.close();
            }*/
        }catch(Exception e){
            System.out.println(e.getMessage());
        } 
        return user;
    }
}
