import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
@ManagedBean
@RequestScoped
public class User{
    int id;
    String name;
    String email;
    String password;
    String gender;
    String address;
    String user;
    String lastName;
    
    String ingresos;
    
    ArrayList usersList ;
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    Connection connection;
    String mensaje = "";
    String mensajeError = "";
        
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
    
    public String getMensaje() {
            return mensaje;
    }

    public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
    }

    public String getMensajeError() {
            return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
            this.mensajeError = mensajeError;
    }
    
    
    public String getIngresos() {
            return mensajeError;
    }

    public void setIngresos(String ingresos) {
            this.ingresos = ingresos;
    }
    
    // Used to establish connection
    public Connection getConnection(){
        try{
            this.mensajeError = "";
            Class.forName("com.mysql.jdbc.Driver");   
            connection = DriverManager.getConnection( "jdbc:mysql://jeffri.mysql.database.azure.com:3306/user","aplicacionWeb@jeffri","grados2021..");
        }catch(Exception e){
            System.out.println(e.getMessage());
            this.mensajeError = e.getMessage();
        }
        return connection;
    }
    // Used to fetch all records
    public ArrayList usersList(){
        System.out.print("tabla");
        try{
            usersList = new ArrayList();
            connection = getConnection();
            Statement stmt=getConnection().createStatement();  
            ResultSet rs=stmt.executeQuery("select * from users");  
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setGender(rs.getString("gender"));
                user.setAddress(rs.getString("address"));
                usersList.add(user);
            }
            connection.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return usersList;
    }
    // Used to save user record
    public String save(){
        System.out.println("save");
        int result = 0;
        try{
            connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("insert into users(name,email,password,gender,address, user, lastName) values(?,?,?,?,?,?,?)");
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, gender);
            stmt.setString(5, address);
            stmt.setString(6, user);
            stmt.setString(7, lastName);
            result = stmt.executeUpdate();
            connection.close();
        }catch(Exception e){
            this.mensaje = e.getMessage();
            System.out.println(e);
        }
        
        if(result !=0)
            this.mensaje = "Cliente creado con éxito";
        else 
            this.mensaje = "Error creado cliente"; 
        
        return "create.xhtml?faces-redirect=false";
    }
    // Used to save user record
    public String saveSolicitud(){
        System.out.println("saveIngresos");
        int result = 0;
        try{
            connection = getConnection();
            PreparedStatement stmt = connection.prepareStatement("insert into solicitud(ingresos,nombre,email) values(?,?,?)");
            stmt.setString(1, ingresos);
            stmt.setString(2, name);
            stmt.setString(3, email);
            result = stmt.executeUpdate();
            connection.close();
        }catch(Exception e){
            this.mensaje = e.getMessage();
            System.out.println(e);
        }
        
        if(result !=0)
            this.mensaje = "Solicitud creada con éxito";
        else 
            this.mensaje = "Error creando solicitud"; 
        
        return "solicitud.xhtml?faces-redirect=false";
    }
    // Used to fetch record to update
    public String edit(int id){
        User user = null;
        System.out.println(id);
        try{
            connection = getConnection();
            Statement stmt=getConnection().createStatement();  
            ResultSet rs=stmt.executeQuery("select * from users where id = "+(id));
            rs.next();
            user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setAddress(rs.getString("address"));
            user.setPassword(rs.getString("password"));  
            user.setUser(rs.getString("user"));
            user.setLastName(rs.getString("lastName"));
            System.out.println(rs.getString("password"));
            sessionMap.put("editUser", user);
            connection.close();
        }catch(Exception e){
            System.out.println(e);
        }       
        return "/edit.xhtml?faces-redirect=true";
    }
    // Used to update user record
    public String update(User u){
        //int result = 0;
        try{
            connection = getConnection();  
            PreparedStatement stmt=connection.prepareStatement("update users set name=?,email=?,password=?,gender=?,address=?,user=?,lastName=? where id=?");  
            stmt.setString(1,u.getName());  
            stmt.setString(2,u.getEmail());  
            stmt.setString(3,u.getPassword());  
            stmt.setString(4,u.getGender());  
            stmt.setString(5,u.getAddress());  
            stmt.setString(6, u.getUser());
            stmt.setString(7, u.getLastName());
            stmt.setInt(8,u.getId());  
            stmt.executeUpdate();
            connection.close();
            this.mensaje = "Cliente modificado con éxito";
        }catch(Exception e){
            this.mensaje = e.getMessage();
            System.out.println();
        }
        return "/edit.xhtml?faces-redirect=false";      
    }
    // Used to delete user record
    public void delete(int id){
        try{
            connection = getConnection();  
            PreparedStatement stmt = connection.prepareStatement("delete from users where id = "+id);  
            stmt.executeUpdate();  
        }catch(Exception e){
            System.out.println(e);
        }
    }
    // Used to set user gender
    public String getGenderName(char gender){
        if(gender == 'M'){
            return "Male";
        }else return "Female";
    }
    
    // Validar usuario
    public String getUser(User userValidate) {
        try{
            User user = null;
            connection = getConnection();
            Statement stmt=getConnection().createStatement();  
            ResultSet rs=stmt.executeQuery("select * from users where user = '"+(userValidate.user)+ "' and password = '" +(userValidate.password)+"'");
            if(rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setGender(rs.getString("gender"));
                user.setAddress(rs.getString("address"));
                user.setPassword(rs.getString("password"));  
                user.setUser(rs.getString("user"));
                user.setLastName(rs.getString("lastName"));
                System.out.println(rs.getString("password"));
                sessionMap.put("editUser", user);
                this.mensaje = "";
                connection.close();;
                return "/edit.xhtml?faces-redirect=true"; 
            } else {
                this.mensaje = "Usuario o contraseña inválido";
                connection.close();
            }
        }catch(Exception e){
            this.mensaje = e.getMessage();
            System.out.println(e.getMessage());
        } 
        return "index.xhtml?faces-redirect=false";
    }
}