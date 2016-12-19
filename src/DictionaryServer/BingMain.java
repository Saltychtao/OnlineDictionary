package DictionaryServer;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;

import net.sf.json.JSONObject;

public class BingMain {

	private static HttpClient httpClient = new HttpClient();
	static GetMethod getmethod;
    public static String BingTranslate(String keyword) throws HttpException, IOException {
           return downloadPage(keyword);
    }
		
		public static String downloadPage(String keyword) throws HttpException,IOException { 
			       keyword=keyword.replace(" ","+");
	               String path="https://www.bing.com/dict/search?q="+keyword;
                   getmethod = new GetMethod(path);
                   getmethod.getParams().setParameter("http.protocol.cookie-policy",CookiePolicy.BROWSER_COMPATIBILITY);
                   int statusCode = httpClient.executeMethod(getmethod);
                   if(statusCode == HttpStatus.SC_OK){
                            String str=getmethod.getResponseBodyAsString().substring(400,600);
                            if(str.contains("的释义")&&str.contains("网络释义"))
                            str=str.substring(str.indexOf("的释义")+4,str.indexOf("网络释义"));
                            else str="no such word";
                            String pageString = getmethod.getResponseBodyAsString();
                            getmethod.releaseConnection();
                    return str;
             }
            return "Internet Connection Overtime";
         }
}
