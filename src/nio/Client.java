package nio;

import java.net.InetAddress;
import java.net.Socket;

class Client extends Thread
{
    public static void main(String args[])
    {
        try
        {
            // открываем сокет и коннектимся к localhost:3128
            // получаем сокет сервера
            Socket s = new Socket(InetAddress.getByName("192.168.0.105"), 3134);

            // берём поток вывода и выводим туда первый аргумент
            // заданный при вызове, адрес открытого сокета и его порт
         /*
         args[0] = args[0]+"\n"+s.getInetAddress().getHostAddress()
                            +":"+s.getLocalPort();
                            
                          */  
            String ss = "Second Player ";
            s.getOutputStream().write(ss.getBytes());

            // читаем ответ
            byte buf[] = new byte[64*1024];
            int r = s.getInputStream().read(buf);
            String data = new String(buf, 0, r);

            // выводим ответ в консоль
            System.out.println(data);
        }
        catch(Exception e)
        {System.out.println("init error: "+e);} // вывод исключений
    }
}

