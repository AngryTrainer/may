package selector;

import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client  extends Thread
{
    public static void main(String args[])
    {
        try
        {

            Socket s = new Socket(InetAddress.getByName("192.168.0.104"), 1236);

            Scanner in=new Scanner(System.in);
            String ss;
            while(true){
            	ss=in.nextLine();
            	if(ss.equals("exit")) break;
            	s.getOutputStream().write(ss.getBytes());
            }
            in.close();
           
        }
        catch(Exception e)
        {System.out.println("init error: "+e);} // вывод исключений
    }
}