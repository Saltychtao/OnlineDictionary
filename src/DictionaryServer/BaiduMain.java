package DictionaryServer;

import net.sf.json.JSONObject;

public class BaiduMain {

    private static final String APP_ID = "20161127000032801";
    private static final String SECURITY_KEY = "JsaUhiKRLbXA1X5O0Gm3";
   
    public static String BaiduTranslate(String keyword) {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String query = keyword;
       // System.out.println(api.getTransResult(query, "auto", "zh"));
        
     JSONObject object = JSONObject.fromObject(api.getTransResult(query, "auto", "zh"));
     System.out.println("Baidu:");
     String str = object.getString("trans_result");
     object = JSONObject.fromObject(str.substring(1, str.length()-1));
     str= object.getString("dst");
     System.out.println(str);
     return str;
    }
}
