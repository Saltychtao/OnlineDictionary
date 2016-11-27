/*	import java.io.IOException;

	import org.jsoup.Jsoup;
	import org.jsoup.nodes.Document;

	import net.sf.json.JSONObject;


public class Baidu {

	    public static void main(String[] args) {
	        System.out.println(Baidu.doTranslate("apple", "en", "zh"));
	    }

	    // 请求函数
	   public static String doTranslate(String keyword, String from, String to) {
	        String resource = null;
	        try {
	            // 得到网页的内容
	            Document document = Jsoup
	                    .connect("http://fanyi.baidu.com/transapi?from=" + from + "&to=" + to + "&query=" + keyword)
	                    .ignoreContentType(true).get();
	            // 得到body的内容
	            resource = document.getElementsByTag("body").text().toString();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        // 将源码转成jsonobject
	       // System.out.println(resource);
	        JSONObject object = JSONObject.fromObject(resource);
	        String temp = object.getString("data");
	        temp = temp.substring(1, temp.indexOf(",\"result"));
	        temp += "}";
	        JSONObject data = JSONObject.fromObject(temp);
	        // 得到翻译后的内容
	        return data.getString("dst");
	    }
	   
     /*
	    public static String doTranslate(String keyword, String from, String to) {
	        String resource = null;
	        try {
	            // 得到网页的内容
	            Document document = Jsoup
	                    .connect("http://fanyi.baidu.com/#en/zh/" + keyword)
	                    .ignoreContentType(true).get();
	            // 得到body的内容
	            resource = document.getElementsByTag("body").text().toString();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        // 将源码转成jsonobject
	        System.out.println(resource);
	        JSONObject object = JSONObject.fromObject(resource);
	        String temp = object.getString("data");
	        temp = temp.substring(1, temp.indexOf(",\"result"));
	        temp += "}";
	        JSONObject data = JSONObject.fromObject(temp);
	        // 得到翻译后的内容
	        return data.getString("dst");
	    }
}

*/
