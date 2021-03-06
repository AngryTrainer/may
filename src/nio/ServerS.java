package nio;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

class ServerS extends Thread
{
    Socket s;
    int num;

    public static void main(String args[])
    {
        try
        {
            int i = 0; // счётчик подключений

            // привинтить сокет на локалхост, порт 3128
            ServerSocket server = new ServerSocket(3134, 0,
                    InetAddress.getByName("192.168.0.105"));

            System.out.println("server is started");

            // слушаем порт
            while(true)
            {
                // ждём нового подключения, после чего запускаем обработку клиента
                // в новый вычислительный поток и увеличиваем счётчик на единичку
                new ServerS(++i, server.accept());
                
                System.out.println("Connections: "+i);
            }
        }
        catch(Exception e)
        {System.out.println("init error: "+e);} // вывод исключений
    }

    public ServerS(int num, Socket s)
    {
        // копируем данные
        this.num = num;
        this.s = s;
        // и запускаем новый вычислительный поток (см. ф-ю run())
        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }

    public void run()
    {
        try
        {
            // из сокета клиента берём поток входящих данных
            InputStream is = s.getInputStream();
            // и оттуда же - поток данных от сервера к клиенту
            OutputStream os = s.getOutputStream();
            // буффер данных в 64 килобайта
            byte buf[] = new byte[64*1024];
            // читаем 64кб от клиента, результат - кол-во реально принятых данных
            int r = is.read(buf);
            // создаём строку, содержащую полученную от клиента информацию
            String data = new String(buf, 0, r);
            System.out.println("Accepted: "+data);
            // добавляем данные об адресе сокета:
            data = "Server returns: "+num+": "+"\n"+data;
            // выводим данные:
            os.write(data.getBytes());
            // завершаем соединение
            s.close();
        }
        catch(Exception e)
        {System.out.println("init error: "+e);} // вывод исключений
    }
}