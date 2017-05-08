package ii;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RAF {
	private String path;

	private RandomAccessFile file;

	public RAF(String path) {
		this.path = path;
	}

	public long goTo(int num) throws IOException {
		file = new RandomAccessFile(path, "r");
		file.seek(num);
		// получаем текущее состояние курсора в файле
		long pointer = file.getFilePointer();
		file.close();
		return pointer;
	}

	public String read() throws IOException {
		file = new RandomAccessFile(path, "r");
		String res = "";
		int b = file.read();
		// побитово читаем символы и плюсуем их в строку
		while (b != -1) {
			res = res + (char) b;
			b = file.read();
		}
		file.close();

		return res;
	}

	// читаем файл с определенного символа
	public String readFrom(int numberSymbol) throws IOException {
		// открываем файл для чтения
		file = new RandomAccessFile(path, "r");
		String res = "";

		// ставим указатель на нужный вам символ
		file.seek(numberSymbol);
		int b = file.read();

		// побитово читаем и добавляем символы в строку
		while (b != -1) {
			res = res + (char) b;

			b = file.read();
		}
		file.close();

		return res;
	}

	// запись в файл
	public void write(String st) throws IOException {
		file = new RandomAccessFile(path, "rw");
		// записываем строку переведенную в биты
		file.write(st.getBytes());
		// закрываем файл, после чего данные записываемые данные попадут в файл
		file.close();
	}

	public static void main(String[] args) throws IOException {
		RAF worker = new RAF("testfile.txt");
		 
		// пишем передаваемый текст в файл
	        worker.write("Give me a break from your take and your take. \n" +
	                "Come on and give me a break,\n" +
	                "what do you want from me?\n" +
	                "Feeding the rich with that son of a bitch.\n" +
	                "Well that son of a bitch,\n" +
	                "he looks just like me!\n" +
	                "Yeah, yeah");
	 

	        // переходим на указанный символ в файле
	       // System.out.println(worker.goTo(97));
	       // System.out.println("*******");
	 
	        // читаем весь файл
	      // System.out.println(worker.read());
	        //System.out.println("*******");
	 
	        // читаем файл с указанного символа
	        System.out.println(worker.readFrom(103));


	}

}
