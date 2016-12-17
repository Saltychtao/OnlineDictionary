package DictionaryServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class DataBase {
	  private static final String SQLSERVER_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	  private static final String SQLSERVER_URL = "jdbc:sqlserver://localhost:1433;databaseName=OlDictionary";
	  
	static boolean SignUp(String name,String password,String email)
	{	  
		 try {
	            Class.forName(SQLSERVER_DRIVER).newInstance();
	            String url = SQLSERVER_URL;
	            Connection con = DriverManager.getConnection(url, "sa", "sa");
	            Statement st = con.createStatement();
	            String sql = "insert into users values('"+name+"','"+password+"','"+email+"')";
	            int rs = st.executeUpdate(sql);
	            st.close();
	            con.close();

	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
		 return true;
		
	}
	
	static boolean SignIn(String name,String password)
	{	  
		boolean LoginFlag=false;
		 try {
	            Class.forName(SQLSERVER_DRIVER).newInstance();
	            String url = SQLSERVER_URL;
	            Connection con = DriverManager.getConnection(url, "sa", "sa");
	            Statement st = con.createStatement();
	            String sql = "select userpassword from users where username = '"+name+"'";
	            ResultSet rs = st.executeQuery(sql);
	            ResultSetMetaData rsmd = rs.getMetaData();
	            int columnCount = rsmd.getColumnCount();
	            while (rs.next()) {
	                for (int i = 1; i <= columnCount; i++) {
	                	if(password.trim().equals(rs.getString(i).trim())) 
	                		{
	                		 LoginFlag=true;
	                		 break;
	                		 }
	 
	                }
	            }
	            st.close();
	            con.close();

	        } catch (Exception e) {
	            e.printStackTrace();
	       
	        }
		 return LoginFlag;
		
	}

}
