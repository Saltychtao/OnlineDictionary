/*	import java.io.IOException;

	import org.jsoup.Jsoup;
	import org.jsoup.nodes.Document;

	import net.sf.json.JSONObject;


public class Baidu {

	    public static void main(String[] args) {
	        System.out.println(Baidu.doTranslate("apple", "en", "zh"));
	    }

	    // ������
	   public static String doTranslate(String keyword, String from, String to) {
	        String resource = null;
	        try {
	            // �õ���ҳ������
	            Document document = Jsoup
	                    .connect("http://fanyi.baidu.com/transapi?from=" + from + "&to=" + to + "&query=" + keyword)
	                    .ignoreContentType(true).get();
	            // �õ�body������
	            resource = document.getElementsByTag("body").text().toString();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        // ��Դ��ת��jsonobject
	       // System.out.println(resource);
	        JSONObject object = JSONObject.fromObject(resource);
	        String temp = object.getString("data");
	        temp = temp.substring(1, temp.indexOf(",\"result"));
	        temp += "}";
	        JSONObject data = JSONObject.fromObject(temp);
	        // �õ�����������
	        return data.getString("dst");
	    }
	   
     /*
	    public static String doTranslate(String keyword, String from, String to) {
	        String resource = null;
	        try {
	            // �õ���ҳ������
	            Document document = Jsoup
	                    .connect("http://fanyi.baidu.com/#en/zh/" + keyword)
	                    .ignoreContentType(true).get();
	            // �õ�body������
	            resource = document.getElementsByTag("body").text().toString();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        // ��Դ��ת��jsonobject
	        System.out.println(resource);
	        JSONObject object = JSONObject.fromObject(resource);
	        String temp = object.getString("data");
	        temp = temp.substring(1, temp.indexOf(",\"result"));
	        temp += "}";
	        JSONObject data = JSONObject.fromObject(temp);
	        // �õ�����������
	        return data.getString("dst");
	    }
}

*/
