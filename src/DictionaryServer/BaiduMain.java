package DictionaryServer;

import java.io.IOException;
import java.io.StringReader;

import net.sf.json.JSONObject;


import com.google.gson.stream.JsonReader;

public class BaiduMain {

    private static final String APP_ID = "20161127000032801";
    private static final String SECURITY_KEY = "JsaUhiKRLbXA1X5O0Gm3";
    private static StringBuilder b=new StringBuilder();  
    public static void BaiduTranslate(String keyword) {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String query = keyword;
       // System.out.println(api.getTransResult(query, "auto", "zh"));
        
     JSONObject object = JSONObject.fromObject(api.getTransResult(query, "auto", "zh"));
     System.out.println("Baidu:");
     String str = object.getString("trans_result");
     object = JSONObject.fromObject(str.substring(1, str.length()-1));
     str= object.getString("dst");
     System.out.println(str);
   	 /*JsonReader reader = new JsonReader(new StringReader(str));
   	 try {    
   		         // 寮�濮嬭В鏋怞SON鏁扮粍    
   		           reader.beginArray();    
   		            // 寰幆璇诲彇JSON涓殑鏁扮粍    
   		             while(reader.hasNext()){    
   		                 // 寮�濮嬭В鏋怞SON鏁扮粍閲岀殑瀵硅薄    
   		                 reader.beginObject();    
   		                 // 寰幆璇诲彇JSON鏁扮粍涓殑瀵硅薄    
   		               while(reader.hasNext()){    
   		                   String tagName = reader.nextName();    
   		                   if(tagName.equals("src")){    
   		                     System.out.println("src---->" + reader.nextString());    
   		                  }    
   		                    else if(tagName.equals("dst")){    
   		                    	String str2=reader.nextString();
   		                        System.out.println("dst---->" +str2); 
   		                        
   		                        b.append(str2);
   		                        b.append('\n');
   		                    }    
   		                }    
   		                reader.endObject();    
   		            }    
   		            reader.endArray();    
   		         } catch (IOException e) {    
   		             e.printStackTrace();    
   		        }       
        } */
    }
}
