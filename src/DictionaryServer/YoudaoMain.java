package DictionaryServer;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;



public class YoudaoMain {
	private static String url = "http://fanyi.youdao.com/openapi.do";
    private static String keyfrom = "NjuDictionary";
    private static String key = "2037871503";
    
    public static String YoudaoTranslate(String keyword)
    {
	CloseableHttpClient hc = HttpClientBuilder.create().build();
    HttpGet httpGet = new HttpGet();
    httpGet = new HttpGet(url+"?keyfrom="+keyfrom+"&key="+key+"&type=data&doctype=json&version=1.1&q="+keyword);
    CloseableHttpResponse response = null;
    JSONObject object = null;
  
    try {
		response = hc.execute(httpGet);
		object = JSONObject.fromObject(EntityUtils.toString(response.getEntity()));
	} catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally
	{
		httpGet.releaseConnection();
	}
    System.out.println("Youdao:");
    object= object.getJSONObject("basic");
    String str= object.getString("explains");
    str=str.substring(2, str.length()-2);
    return str;
    }
}
