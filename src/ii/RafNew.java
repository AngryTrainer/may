package ii;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RafNew {

	public static void main(String[] args) throws IOException {
		RandomAccessFile	file1 = new RandomAccessFile("testfile.txt", "rw");
		// записываем строку переведенную в биты
		file1.seek(100);
		String st="BLABLA";
		file1.write(st.getBytes());
		// закрываем файл, после чего данные записываемые данные попадут в файл
		file1.close();
		
		
		RandomAccessFile file = new RandomAccessFile("testfile.txt", "r");
		String res = "";
		// ставим указатель на нужный вам символ
		file.seek(0);
		int b = file.read();
		// побитово читаем и добавляем символы в строку
		while (b != -1) {
			res = res + (char) b;

			b = file.read();
		}
		file.close();
		System.out.println(res);
	}

}
