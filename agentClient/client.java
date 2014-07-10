
/*import java.io.BufferedReader; 
import java.io.BufferedWriter; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.io.OutputStreamWriter; 
import java.net.InetSocketAddress; 
import java.net.Socket; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
public class client { 
   public static void main(String[] args) { 
      try { 
         try (Socket sock = new Socket()) { 
            sock.connect(new InetSocketAddress(8888)); 
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream())); 
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
            
            String message; 
            while(true) { 
               message = br.readLine(); 
               if(message.equals("exit")) { 
                  bw.write(message); 
                  bw.flush(); 
               } 
               bw.write(message); 
               bw.flush(); 
            } 
         } 
         } catch (IOException ex) { 
         Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex); 
      } 
   } 
} 
*/



import java.io.*;  
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader; 
import java.io.InputStreamReader; 
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Timer;

public class client extends Thread {
	parameters para;

	public client() {
		super();
	}

	public void run() {
		try
		{
//	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	            
	        String data; 
	        data = Integer.toString(para.getSpendableTime()-para.getExpectedTime()); 
	        
			while (true)
			{				
				DatagramPacket packet = new DatagramPacket(data.getBytes(), data.getBytes().length, InetAddress
						.getByName("10.8.8.23"),9999);

				DatagramSocket socket = new DatagramSocket();
				
				socket.send(packet);
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

	public static void main(String args[]) { 
		// Web, Image, LB, DB
		String serverType = "LB";
	    String value1 = ""; 
		String value2 = "";	
		
		int expectedTime = 0;
		int spendableTime = 0;
		int samplingTime = 1000;
		int thresholdScore = 5;
		
	    try{
		    File readFile1 = new File("/home/jy/agent/expectedTime.txt");	    
			BufferedReader inFile1 = new BufferedReader(new FileReader(readFile1));
		    String sLine1 = null;
		    
		    File readFile2 = new File("/home/jy/agent/spendableTime.txt");
			BufferedReader inFile2= new BufferedReader(new FileReader(readFile2));
		    String sLine2 = null;
				
		    while((sLine1 = inFile1.readLine()) != null)
		    {
		    	StringTokenizer st1 = new StringTokenizer(sLine1, ",");
			    
			    while(st1.hasMoreTokens())
			    {
			    	if(st1.nextToken().equals(serverType))
			    	{
			    		value1 = st1.nextToken();
			    		expectedTime = Integer.parseInt(value1);
			    		System.out.println("Expected Time = " + expectedTime);
			    	}
			    }		        
		    }
		    
//		    while((sLine2 = inFile2.readLine()) != null)
//		    {
		    sLine2 = inFile2.readLine();
		    spendableTime = Integer.parseInt(sLine2);
			System.out.println("Spendable Time = " + spendableTime);
			    
//		    }
	    }
	    catch(Exception ex){
	    	ex.printStackTrace();
	    }
	    
		
		parameters param = new parameters(expectedTime, spendableTime, samplingTime, thresholdScore);
//		client client = new client();		
//		client.start();
		
		Timer timer = new Timer();
		Timer timer2 = new Timer();
		
		Detect detect = new Detect();
		Transmit trans = new Transmit();
		
		timer.schedule(detect,  0, spendableTime);
		timer2.schedule(trans,  0, samplingTime);
		
//		Thread thread = new Thread(new Job());
 //       thread.start();  
		
			    
	}
}
