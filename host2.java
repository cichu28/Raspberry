import java.net.ServerSocket;
import java.net.Socket;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.IOException;


public class host2
{
	public static void main(String srgs[])
	{
		int count = 0;
		
		//hard code to use port 8080
		try(ServerSocket serverSocket = new ServerSocket(8080))
		{
			System.out.println("I'm waiting here: " + serverSocket.getLocalPort());
			System.out.println("This program will stay monitoring port 8080, until user press Ctrl+C");
			
			
			while(true)
			{
				try(Socket socket = serverSocket.accept())
				{
					count++;
					System.out.println("#" + count + " from " + socket.getInetAddress() + ":" + socket.getPort());
					OutputStream outputStream = socket.getOutputStream();
					try(PrintStream printStream = new PrintStream(outputStream))
					{
						printStream.print("Hello! This is Raspberry Pi! \nYou are:	 " + count);
					}
				}
				catch(IOException ex)
				{
					System.out.println(ex.toString());
				}
			}
		}
		catch(IOException ex)
		{
			System.out.println(ex.toString());
		}
	}
}
