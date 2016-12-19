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
		  int i = 0;
		  try   
		  {    
		    while (i<10000) 
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
		      String[] strs = keyword.split("#");
		      if(strs[1].equals("0"))
		         {
		    	   System.out.println("Client:"+ClientID+"sign up");
		    	   DataBase.SignUp(strs[2],strs[3],strs[4]);
		    	   soutputstream.writeUTF("#0#sign up success");
		         }
		      else if (strs[1].equals("1"))
		        { 
		    	  System.out.println("Client:"+ClientID+"sign in");
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
		    	  System.out.println("Client:"+ClientID+"translate");
		    	    if(strs[2].equals("1"))
		    	    {
		    	    	soutputstream.writeUTF(BaiduMain.BaiduTranslate(strs[3])+"#"+DataBase.GetPraiseNum(strs[3], 1));
		    	    }
		    	    else if(strs[2].equals("2"))
		    	    {
		    	    	soutputstream.writeUTF(YoudaoMain.YoudaoTranslate(strs[3])+"#"+DataBase.GetPraiseNum(strs[3], 2));
		    	    }
		    	    else
		    	    { 
		        	     soutputstream.writeUTF(BingMain.BingTranslate(strs[3])+"#"+DataBase.GetPraiseNum(strs[3], 3));
		    	    }
		        }
		      else if (strs[1].equals("3"))
		        {	
		    	  System.out.println("Client:"+ClientID+"sign praise");
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
		    	   System.out.println("Client:"+ClientID+"send word card");
                    WordCards.sendWords(strs[2],strs[3],strs[4]);
		        	soutputstream.writeUTF("#4#send word card successfully");
		        }
		      else if (strs[1].equals("5"))
		        {	
		    	    System.out.println("Client:"+ClientID+"search word card");
                      if(WordCards.getPort()==(4500+ClientID))
                      { 
                    	  soutputstream.writeUTF("#4#"+WordCards.getWordSend()+"#"+WordCards.getSource());  
    		    	      WordCards.sendFinished();
                      }
                    	  
		        }
		      else if (strs[1].equals("6"))
		        {	
		    	   System.out.println("Client:"+ClientID+"retrieve password");
                    SendMail.mail(DataBase.GetEmail(strs[2]),DataBase.GetPassword(strs[2]));
		        	soutputstream.writeUTF("#6#mail has been sent");
		        }
		      else if (strs[1].equals("7"))
		        {	
		    	  System.out.println("Client:"+ClientID+"make online list");
		    	  String ol=WordCards.MakeOLlist();
		    	  soutputstream.writeUTF("#7"+ol);
		        }
		      else if (strs[1].equals("8"))
		        {	
		    	  System.out.println("Client:"+ClientID+"stop");
                  WordCards.delete(strs[2],String.valueOf(4500+ClientID));
                  
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
