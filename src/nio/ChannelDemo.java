package nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ChannelDemo {

	public static void main(String[] args) throws IOException {
		//Чтение
		
		Path path = Paths.get("C:/Adware/deg.txt");
		FileChannel ch = (FileChannel) Files.newByteChannel(path);
		//резервируем память для буфера, если  allocateDirect() -вне кучи
		ByteBuffer buffer = ByteBuffer.allocate(256);
		int count = 0;
		do {
			try {
				count = ch.read(buffer);
				System.out.println("Прочитано: " + count);
			} catch (IOException ex) {}

			if (count != -1) {
				// подготовить буфер для чтения
				//rewind()- Этот метод возвращает указатель текущего элемента в начало буфера,
				//так как после чтения он указывает на последний элемент
				buffer.rewind();
				// чтение байтов в буфер и отображение их как символов на экране
				for (int i = 0; i < count; i++){
					System.out.print((char) buffer.get() + " ");
				}	
			}
		} while (count != -1);
		ch.close();
		
		//Запись
		Path p = Paths.get("C:/Adware/write.txt");
		 try(FileChannel chn = (FileChannel) Files.newByteChannel(p, StandardOpenOption.WRITE))
		    {
		        //резервируем буфер
		        ByteBuffer buff = ByteBuffer.allocate(26);       
		        //записываем несколько байтов в буфер
		        for(int i = 0; i < 26; i++)
		            buff.put((byte) ('A' + i));
		        
		        //подготовить буфер для записи
		        buff.rewind();        
		        //записываем данные
		        chn.write(buff);
		    }
		
	}

}
