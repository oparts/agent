

/*import java.io.IOException; 
import java.net.ServerSocket; 
import java.net.Socket; 
import java.net.SocketAddress; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
public class server {   
   public static void main(String[] args) { 
      try { 
         int port = 8888; 
         try (ServerSocket serverSock = new ServerSocket(port)) { 
            Logger logger = Logger.getLogger("Racar"); 
            while(true) { 
               Socket clientSock = serverSock.accept(); 
               SocketAddress clientAddress = clientSock.getRemoteSocketAddress(); 
               Thread thread = new Thread(new serverThread(clientSock, clientAddress, logger)); 
               thread.start(); 
               if(clientSock.isConnected()) 
               System.out.println("Connected! Client IP : " + clientAddress); 
            } 
         } 
         } catch (IOException ex) { 
         Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex); 
      } 
   } 
} 
*/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.ServerSocket; 
import java.net.Socket; 
import java.net.SocketAddress; 
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.logging.Level; 
import java.util.logging.Logger; 

public class server extends Thread {
	parameters para;

	private DatagramSocket socket;
	
	static FileWriter spWriter;
	
	static File writeFile;
	static FileWriter fileWriter;
    static BufferedWriter bufWriter;
    static PrintWriter printWriter;

	public server() throws SocketException {
		super();

		socket = new DatagramSocket(9999);
	}

	public void run() {
		String data = null;
		int spendableTime = 0;
		
			
			try{				
				byte[] inbuf = new byte[256];
				DatagramPacket packet = new DatagramPacket(inbuf, inbuf.length);
				
//				spWriter = new FileWriter("D://SP.txt");
				
				writeFile = new File("/home/jy/agent/spendableTime.txt");
				fileWriter = new FileWriter(writeFile);
				bufWriter = new BufferedWriter (fileWriter);	
				printWriter = new PrintWriter(bufWriter,true);
				

				while (true)
				{

					socket.receive(packet);
					
					StringTokenizer st1 = new StringTokenizer(new String(packet.getData(), 0, packet.getLength()), ":");
		    		
					while(st1.hasMoreTokens())
				    {
				    	if(st1.nextToken().equals("SP"))
				    	{
				    		data = st1.nextToken();
				    		spendableTime = Integer.parseInt(data);
//				    		spWriter.write(spendableTime);
//							spWriter.close();
				    		bufWriter.write(data);	
							printWriter.println();
					
				    		System.out.println("SP = " + spendableTime + ", Sender : " + packet.getAddress());
				    	}
				    }
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}


	}
	
	public static void main(String args[]) throws SocketException{
		int expectedTime = 1000;
		int spendableTime = 2000;
		int samplingTime = 1000;
		int thresholdScore = 5;
		
		server server = new server();
		parameters param = new parameters(expectedTime, spendableTime, samplingTime, thresholdScore);
	    
//		Timer timer = new Timer();
		
//	    Job j = new Job();	    
//	    j.init();
//	    timer.schedule(j,  0, 2000);
		
		server.start();
	}
}