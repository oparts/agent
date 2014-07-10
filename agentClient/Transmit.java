import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.StringTokenizer;
import java.util.TimerTask;

class Transmit extends TimerTask {	
	parameters para;
	
    
    Transmit(){    	
    }
    
	public void run(){		
		try
		{	            
	        String data; 
	        data = "SP:" + Integer.toString(para.getSpendableTime()-para.getExpectedTime()); 	        
					
			DatagramPacket packet = new DatagramPacket(data.getBytes(), data.getBytes().length, InetAddress
						.getByName("10.8.8.23"), 9999); // toServer

			DatagramSocket socket = new DatagramSocket();				
			socket.send(packet);
			
			if(para.getFlagReport() == 1)
			{
				data = "current score => " + para.getCurrentScore();
				DatagramPacket packet1 = new DatagramPacket(data.getBytes(), data.getBytes().length, InetAddress
						.getByName("10.1.0.43"), 9999); // to monitoring

				DatagramSocket socket1 = new DatagramSocket();				
				socket1.send(packet1);
				para.setFlagReportFalse();
			}

		} catch (UnknownHostException e)
		{
			e.printStackTrace();
		} catch (SocketException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
}
