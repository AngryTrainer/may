package myurl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SendRequest {

	public static void main(String[] args) throws IOException {
		URL url=new URL("http://code-master.com.ua/post.php");
		HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
		urlc.setRequestMethod("POST");
		urlc.setDoOutput(true);
		urlc.setDoInput(true);
		PrintWriter pw=new PrintWriter(new OutputStreamWriter(urlc.getOutputStream()));
		pw.print(URLEncoder.encode("login",StandardCharsets.UTF_8.toString()));
		pw.flush();
		if(urlc.getResponseCode()==HttpURLConnection.HTTP_OK){
			System.out.println("OK");
		}
		//InputStream in=urlc.getInputStream();
		//System.out.println(in.read());
		BufferedReader bin=new BufferedReader(new InputStreamReader(urlc.getInputStream(),StandardCharsets.UTF_8));
		String line;
		while((line=bin.readLine())!=null){
			System.out.println(line);
		}
		bin.close();
		System.out.println("Closed...");
	}

}
