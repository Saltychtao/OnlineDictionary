package DictionaryServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;
public class Server {
    static String[][] users= new String[16][2];
    static int UserCount=0;
    private static void LoadUserInformation()
    {
    	    File sourceFile = new File("users.txt");
			if(!sourceFile.exists())
			{
				System.out.println(
				"Dictionary file does not exist");
				System.exit(0);
			}
	        Scanner input = null;
			try {
				input = new Scanner(sourceFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
			while(input.hasNext())
			{
				 String s = input.nextLine();
				 String[] array = new String[2];
				 array = s.split("	");	
				 users[UserCount][0]=array[0];
			     users[UserCount][1]=array[1];
			     UserCount++;
			}
			input.close();
    }
    private static boolean CheckLogin(String UserName,String password)
    {
    	for(int temp=0;temp<UserCount;temp++)
    	{
    		if(UserName==users[temp][0]&&password==users[temp][1])
    			return true;
    	}
		return false;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  System.out.println("Loading user information");
		  System.out.println("Server is staring!");
		  LoadUserInformation();
		  int i = 0;
		  try   
		  {    
		    ServerSocket DictServer = new ServerSocket(4500);
		    while ( i < 100) 
		    { 
		      Socket SClient = DictServer.accept();   
		      DataInputStream sinputstream = new DataInputStream(SClient   
		         .getInputStream());   
		      DataOutputStream soutputstream = new DataOutputStream(SClient   
		         .getOutputStream());   
		      String keyword = new String(sinputstream.readUTF());   
		      System.out.print("get word");
		      String[] strs = keyword.split("##");
		      if (strs[1].equals("1"))
		        {
		    	  
		    	  if(CheckLogin(strs[3],strs[5]))
		        	soutputstream.writeUTF("#1#login success");
		    	  else
		    	    soutputstream.writeUTF("#1#user name or password error"); 
		        	
		        }
		      else if (strs[1].equals("2"))
		        {	
		    	    String result1=BaiduMain.BaiduTranslate(strs[3]);
				    String result2=YoudaoMain.YoudaoTranslate(strs[3]);
				    String result3 = "";
		        	soutputstream.writeUTF("#2#1"+result1);
		        	soutputstream.writeUTF("#2#2"+result2);
		        	soutputstream.writeUTF("#2#3"+result3);
		        }
		      else if (strs[1].equals("3"))
		        {	
		        	soutputstream.writeUTF("iii");
		        }
		      soutputstream.close();   
		      soutputstream.close();   
		      SClient.close();
		      i++;
		     }
		   DictServer.close();
		    } 
		    catch(IOException e)   
		    {   
		           
		    }   
	}
		   
}  