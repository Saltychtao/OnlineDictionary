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
		 System.out.println(LoginFlag);
		 return LoginFlag;
		
	}
    
	static boolean Praise(String name,int no)
	{	  
		boolean WordFlag=false;
		 try {
	            Class.forName(SQLSERVER_DRIVER).newInstance();
	            String url = SQLSERVER_URL;
	            Connection con = DriverManager.getConnection(url, "sa", "sa");
	            String sql="";
	            Statement st = con.createStatement();
	            sql = "select wname from words where wname = '"+name+"'";
	            ResultSet rs = st.executeQuery(sql);
	            ResultSetMetaData rsmd = rs.getMetaData();
	            int columnCount = rsmd.getColumnCount();
	            while (rs.next()) {
	                for (int i = 1; i <= columnCount; i++) {
	                	if(name.trim().equals(rs.getString(i).trim())) 
	                		{
	                		 WordFlag=true;
	                		 break;
	                		 }
	 
	                }
	            }
	            if( WordFlag) 
	            {
	            	if(no==1)
		                sql = "update words set Baidu +=1 where wname='"+name+"'";
		            else if(no==2)
		            	sql = "update words set Youdao +=1 where wname='"+name+"'";
		            else if(no==3)
		            	sql = "update words set Bing +=1 where wname='"+name+"'";
	            	st.executeUpdate(sql);
	            	System.out.println("update");
	            }
	            else
	            {
	            	if(no==1)
		                sql = "insert into words values('"+name+"',1,0,0)";
		            else if(no==2)
		            	sql = "insert into words values('"+name+"',0,1,0)";
		            else if(no==3)
		            	sql = "insert into words values('"+name+"',0,0,1)"; ;
	            	st.executeUpdate(sql);
	            	System.out.println("create");
	            }
	            st.close();
	            con.close();

	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
		 return true;
	}
	
	static int GetPraiseNum(String name,int no)
	{ 
		int result=0;
		boolean WordFlag=false;
		 try {
	            Class.forName(SQLSERVER_DRIVER).newInstance();
	            String url = SQLSERVER_URL;
	            Connection con = DriverManager.getConnection(url, "sa", "sa");
	            String sql="";
	            Statement st = con.createStatement();
	            sql = "select wname from words where wname = '"+name+"'";
	            ResultSet rs = st.executeQuery(sql);
	            ResultSetMetaData rsmd = rs.getMetaData();
	            int columnCount = rsmd.getColumnCount();
	            while (rs.next()) {
	                for (int i = 1; i <= columnCount; i++) {
	                	if(name.trim().equals(rs.getString(i).trim())) 
	                		{
	                		 WordFlag=true;
	                		 break;
	                		 }
	 
	                }
	            }
	          
	            if(WordFlag) 
	            {
	            	if(no==1)
		                sql = "select Baidu from words where wname='"+name+"'";
		            else if(no==2)
		            	sql = "select Youdao from words where wname='"+name+"'";
		            else if(no==3)
		            	sql = "select Bing from words where wname='"+name+"'";
	                rs = st.executeQuery(sql);
	 	            rsmd = rs.getMetaData();
	 	            columnCount = rsmd.getColumnCount();
	 	            while (rs.next()) {
	 	                     result=Integer.parseInt(rs.getString(1).trim());
	 	                }
	            }
	            else
	            {
	            	result=0;
	            }
	            st.close();
	            con.close();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		 return result;
	}
}
