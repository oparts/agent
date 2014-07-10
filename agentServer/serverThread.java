import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.net.Socket; 
import java.net.SocketAddress; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
class serverThread implements Runnable { 
   private Socket sock; 
   private SocketAddress clientAddress; 
   private Object logger; 
   public serverThread(Socket clientSock) { 
      this.sock = clientSock;  
   } 

   public void run() { 
       System.out.println("00");
      try { 
         BufferedReader br = new BufferedReader(new InputStreamReader(this.sock.getInputStream())); 
         String message = ""; 
         while(true) { 
            message = br.readLine(); 
            System.out.println("11");
            if(message.equals("exit"))
            {
            	System.out.println("11");
            	break;       
            }            
         } 
         } catch (IOException ex) { 
         Logger.getLogger(serverThread.class.getName()).log(Level.SEVERE, null, ex); 
      }
      System.out.println("22");
   } 
} 