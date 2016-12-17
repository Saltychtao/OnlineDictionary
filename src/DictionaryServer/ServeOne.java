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

public class ServeOne extends Thread{
	private ServerSocket socket;
	int ClientID;
	public ServeOne(int id)
	{
		ClientID=id;
		try {
			socket =  new ServerSocket(4500+id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		start();
	}
	
	public void run(){
		  System.out.println("Server is staring!"+ClientID);
		  int i = 0;
		  try   
		  {    
		    while ( i < 100) 
		    { 
		      Socket SClient = socket.accept();   
		      DataInputStream sinputstream = new DataInputStream(SClient   
		         .getInputStream());   
		      DataOutputStream soutputstream = new DataOutputStream(SClient   
		         .getOutputStream());   
		      String keyword = new String(sinputstream.readUTF());   
		      System.out.print("get word");
		      String[] strs = keyword.split("#");
		      if(strs[1].equals("0"))
		         {
		    	   DataBase.SignUp(strs[2],strs[3],strs[4]);
		    	   soutputstream.writeUTF("#0#sign up success");
		    	 
		         }
		      else if (strs[1].equals("1"))
		        { 
		    	  if(DataBase.SignIn(strs[2],strs[3]))
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
		    	    	DataBase.Praise(strs[3],1);
		    	    }
		    	    else if(strs[2].equals("2"))
		    	    {
		    	    	DataBase.Praise(strs[3],2);
		    	    }
		    	    else
		    	    { 
		    	    	DataBase.Praise(strs[3],3);
		    	    }
		        	soutputstream.writeUTF("#3#"+strs[3]+"praise successfully");
		        }
		      sinputstream.close();   
		      soutputstream.close();   
		      SClient.close();
		      i++;
		      
		     }
		   
		    socket.close();
		    } 
		    catch(IOException e)   
		    {   
		           
		    }   
	
	}
}
