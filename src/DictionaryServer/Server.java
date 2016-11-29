package DictionaryServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;
public class Server {
    static String[][] users= new String[16][3];
    static int UserCount=0;

    private static void AddUser(String fileName, String content) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void praise(int Dictype,String word)
    {
    	try {
            FileWriter writer = new FileWriter("words.txt", true);
            if(Dictype==1)
            writer.write(word+"	1	0	0\r\n");
            else if(Dictype==2)
                writer.write(word+"	0	1	0\r\n");
            else if(Dictype==3)
                writer.write(word+"	0	0	1\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
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
			     users[UserCount][2]=array[2];
			     UserCount++;
			}
			input.close();
    }
    private static boolean CheckLogin(String UserName,String password)
    {
    	for(int temp=0;temp<UserCount;temp++)
    	{
    		if(UserName.equals(users[temp][0])&&password.equals(users[temp][1]))
    			return true;
    	}
    	return false;
		
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  System.out.println("Loading user information");
		  LoadUserInformation();
		  System.out.println("Server is staring!");
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
		      String[] strs = keyword.split("#");
		      if(strs[1].equals("0"))
		         {
		    	   AddUser("users.txt",strs[2]+"	"+strs[3]+"	"+strs[4]+"\r\n");	
		    	   soutputstream.writeUTF("#0#sign up success");
		         }
		      else if (strs[1].equals("1"))
		        {
		    	  
		    	  if(CheckLogin(strs[2],strs[3]))
		        	soutputstream.writeUTF("#1#login success");
		    	  else
		    	    soutputstream.writeUTF("#1#user name or password error"); 
		        	
		        }
		      else if (strs[1].equals("2"))
		        {	
		    	    if(strs[2].equals("1"))
		    	    {
		    	    	soutputstream.writeUTF("#2#1"+BaiduMain.BaiduTranslate(strs[3]));
		    	    }
		    	    else if(strs[2].equals("2"))
		    	    {
		    	    	soutputstream.writeUTF("#2#2"+YoudaoMain.YoudaoTranslate(strs[3]));
		    	    }
		    	    else
		    	    { 
		        	soutputstream.writeUTF("#2#3"+"");
		    	    }
		        }
		      else if (strs[0].equals("3"))
		        {	
		    	    if(strs[2].equals("1"))
		    	    {
		    	    	praise(1,strs[3]);
		    	    }
		    	    else if(strs[2].equals("2"))
		    	    {
		    	    	praise(2,strs[3]);
		    	    }
		    	    else
		    	    { 
		    	    	praise(3,strs[3]);
		    	    }
		        	soutputstream.writeUTF("#3#"+strs[3]+"praise successfully");
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