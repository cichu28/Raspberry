import java.net.ServerSocket;
import java.net.Socket;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.IOException;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class host_and_LED
{
	public static void main(String srgs[])
	{
		int count = 0;
		final GpioController gpio = GpioFactory.getInstance();	//
		final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.LOW);
		pin.setShutdownOptions(true, PinState.LOW);
			
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
					pin.high();//			
//pin.toggle();//
				
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
