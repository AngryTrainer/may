package myurl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class UrlDemo {

	public static void main(String[] args) throws IOException {
		URL url=new URL("http://code-master.com.ua/listener.php");
		BufferedReader bin=new BufferedReader(new InputStreamReader(url.openStream(),StandardCharsets.UTF_8));

		
		
		
		
		String line;
		String result="";
		//System.setOut(new java.io.PrintStream(System.out, true, "utf8"));
		PrintWriter pw=new PrintWriter(new File("C:/Adware/code.txt"));
		while((line=bin.readLine())!=null){
			System.out.println(line);
			result+=line;
		}
		pw.write(result);
		pw.close();
		System.out.println(url.openConnection().getHeaderField("Content-Type"));
		bin.close();
	}

}
