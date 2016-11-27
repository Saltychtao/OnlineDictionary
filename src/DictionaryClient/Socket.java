package DictionaryClient;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class Socket {
	private  String serverName = "localhost";
	private  int port = 4500;
	public String meaning1;
	public String meaning2;
	public String meaning3;
	
	
	public Socket()
	{
		;
	}
	
	public  Socket(String server,int port)
	{
		this.serverName = server;
		this.port = port;
	}
	
	public String get(String keyword,int dicType)
	{
		String result = "no result";
		try
		{
			java.net.Socket client = new java.net.Socket(serverName,port);
			 DataInputStream cinputstream = new DataInputStream(client.getInputStream());   
			 DataOutputStream coutputstream = new DataOutputStream(client.getOutputStream());   
			 coutputstream.writeUTF("#2"+"#" + String.valueOf(dicType) + "#" + keyword);
			 String explanation = new String (cinputstream.readUTF());   
			 result = explanation; 
			 
			 cinputstream.close();   
			 coutputstream.close();   
			 client.close();   
		}
		catch(UnknownHostException uhe)    
		{    
		    System.out.println("Unknown host: " + serverName);   
		    uhe.printStackTrace();    
		}
		catch(java.io.EOFException efoe)
		{
			
		}
		catch(IOException ioe)    
		{    
		    System.out.println("IOException: " + ioe);    
		    ioe.printStackTrace();    
		}      
		return result;   
	}   
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
