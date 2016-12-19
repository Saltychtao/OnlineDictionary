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

import org.apache.commons.httpclient.HttpException;

public class Server extends Thread{
    static int ClientCount=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Server is staring!");
		ServerSocket DictServer;
		int i = 0;
		try {
			DictServer = new ServerSocket(4500);
			 while ( i < 100) 
			    { 
			      Socket SClient = DictServer.accept();   
			      DataInputStream sinputstream = new DataInputStream(SClient   
			         .getInputStream());   
			      DataOutputStream soutputstream = new DataOutputStream(SClient   
			         .getOutputStream());   
			      String keyword = new String(sinputstream.readUTF());   
			     // System.out.print("get word");
			      String[] strs = keyword.split("#");
			      if(strs[1].equals("99"))
			         {
			    	  ClientCount++;
			    	  new ServeOne(ClientCount);
			    	  soutputstream.writeUTF(""+(4500+ClientCount)); 
			    	 // soutputstream.writeUTF("#99#"+(4500+ClientCount)); 
			         }
			      sinputstream.close();   
			      soutputstream.close();   
			      SClient.close();
			      i++;
			      
			     }
			DictServer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
		   
}  