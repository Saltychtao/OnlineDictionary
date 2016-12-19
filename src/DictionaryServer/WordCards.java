package DictionaryServer;

public class WordCards {
         static public String[][] olusers= new String[16][2];
         static int count;
         private static String WordSend;
         static String source;
         static int port=0;
         static int getPort()
         {return port;}
         static String getSource()
         {
                return source;        	 
         }
         static String getWordSend()
         {
        	 return WordSend;
         }
         static String MakeOLlist()
         {
        	 String olist="";
        	 for(int x=0;x<count;x++)
        	 {
                 olist=olist+"#"+olusers[count][0];           		 
        	 }
        	 return olist;
        	 
         }
         static public void insert(String username,String port)
         {
        	    System.out.println("add user");
                olusers[count][0]=username;
                olusers[count][1]=username;
                count++;
         }
         static public void delete(String username,String port)
         {
                olusers[count][0]="";
                olusers[count][1]="";
                count--;
         }
         static public int getPort(String des)
         {
        	 for(int x=0;x<count;x++)
        	 {	
        		 if(olusers[count][0]==des)
        	     return Integer.parseInt(olusers[count][1]); 
        	 }
        	 return 0;
         }
         static public void sendWords(String word,String des,String src)
         {
        	 port=getPort(des);
        	 WordSend=word;
        	 source=src;
         }
         static public void sendFinished()
         {
             port=0;	 
         }
         static public void test()
         {
             port=4501;
             WordSend="aa";
             source="bb";
         }
}
