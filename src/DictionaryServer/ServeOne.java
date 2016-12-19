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
			System.out.println("New thread start. ID:"+id+" Port:"+(4500+id));
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
		      WordCards.test();
		      Socket SClient = socket.accept();   
		      DataInputStream sinputstream = new DataInputStream(SClient   
		         .getInputStream());   
		      DataOutputStream soutputstream = new DataOutputStream(SClient   
		         .getOutputStream());   
		     /* if(WordCards.getPort()==(4500+ClientID))
		      {
		    	  soutputstream.writeUTF("#4#"+WordCards.getWordSend()+"#"+WordCards.getSource());  
		    	  WordCards.sendFinished();
		      }*/
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
		        	{
		    		  soutputstream.writeUTF("#1#login success");
		        	  WordCards.insert(strs[2], String.valueOf(4500+ClientID));
		        	}
		    	  else
		    	    soutputstream.writeUTF("#1#user name or password error"); 
		    	
		        	
		        }
		      else if (strs[1].equals("2"))
		        {	
		    	    if(strs[2].equals("1"))
		    	    {
		    	    	soutputstream.writeUTF("#2#1"+BaiduMain.BaiduTranslate(strs[3])+"#"+DataBase.GetPraiseNum(strs[3], 1));
		    	    }
		    	    else if(strs[2].equals("2"))
		    	    {
		    	    	soutputstream.writeUTF("#2#2"+YoudaoMain.YoudaoTranslate(strs[3])+"#"+DataBase.GetPraiseNum(strs[3], 2));
		    	    }
		    	    else
		    	    { 
		        	     soutputstream.writeUTF("#2#3"+BingMain.BingTranslate(strs[3])+"#"+DataBase.GetPraiseNum(strs[3], 3));
		    	    }
		        }
		      else if (strs[1].equals("3"))
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
		      else if (strs[1].equals("4"))
		        {	
                    WordCards.sendWords(strs[2],strs[3],strs[4]);
		        	soutputstream.writeUTF("#4# send word card successfully");
		        }
		      else if (strs[1].equals("5"))
		        {	
                  WordCards.sendWords(strs[2],strs[3],strs[4]);
		        	soutputstream.writeUTF("#4#send word card successfully");
		        }
		      else if (strs[1].equals("6"))
		        {	
                    SendMail.mail(DataBase.GetEmail(strs[2]),DataBase.GetPassword(strs[2]));
		        	soutputstream.writeUTF("#6#mail has been sent");
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
