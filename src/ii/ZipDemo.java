package ii;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
/*
 * 616 Programming on Java
 * 
 */
public class ZipDemo {

	public static void main(String[] args) throws IOException {
		System.out.println("Start zipping...");
		FileOutputStream out=new FileOutputStream("demozip.zip");
		ZipOutputStream zip=new ZipOutputStream(out);
		ZipEntry f1=new ZipEntry("1.txt");
		ZipEntry f2=new ZipEntry("2.txt");
		zip.putNextEntry(f1);
		zip.write("Hello".getBytes());
		zip.putNextEntry(f2);
		zip.write("Hi there".getBytes());
		zip.close();
		System.out.println("Zipped");
		System.out.println("Reading zip...");
		
		FileInputStream fis=new FileInputStream("demozip.zip");
		ZipInputStream unZip=new ZipInputStream(fis);
		unZip.getNextEntry();//вернет null если больше нет файлов
		int d;
		//читаем первый файл
		while((d=unZip.read())!=-1){
			System.out.print((char)d);
		}
		System.out.println("\n----------------");
		// читаем второй файл
		unZip.getNextEntry();
		while((d=unZip.read())!=-1){
			System.out.print((char)d);
		}
	}

}
