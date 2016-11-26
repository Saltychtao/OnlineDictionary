package DictionaryClient;
import java.util.*;
public class UI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket client = new Socket();
		String meaning = "no";
		Scanner keyboard=new Scanner(System.in);   
		boolean exit=false;   
		while(!exit)   
		{   
		    System.out.println("Please input a word");   
		    System.out.println("If you want to exit, input the word: exit!");   
		    String inputword=keyboard.nextLine();   
		    if(inputword.equalsIgnoreCase("exit"))   
		    {   
		        exit=true;   
		    }    
		    else   
		    {   
		        meaning=client.get(inputword, 1)  ;
		        if(meaning.equals("no"))   
		        {   
		        System.out.println("The dictionary does not have this word!");   
		        }   
		        else   
		        {   
		        System.out.println(inputword+" means "+meaning);   
		            System.out.println();   
		        }   
		     }   
		           
		}
		keyboard.close();
	}

}
