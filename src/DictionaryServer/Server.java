package DictionaryServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		      String[] strs = keyword.split("##");
		      if (strs[1].equals("1"))
		        {	
		        	soutputstream.writeUTF("Æ»¹û");
		        }
		      else if (strs[1].equals("2"))
		        {	
		        	soutputstream.writeUTF("éÙ×Ó");
		        }
		      else if (strs[1].equals("3"))
		        {	
		        	soutputstream.writeUTF("Ïã½¶");
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


