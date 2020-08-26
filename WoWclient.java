import java.io.*;
import java.net.*;
 
public class WoWclient {
	
	public static void main(String[] args) {
    	/* If the number of arguments in the command line are less than 2, proper Syntax was not used */
    	if (args.length < 2) {
            System.out.println("Syntax: WoWclient <hostname> <port>");
            return;
        }
    	/* declaring the two arguments */
    	String hostname = args[0]; //first arg.
    	int port = Integer.parseInt(args[1]); //2nd arg.
    	
    	/* Relatively Large buffer to store the response */
        byte[] buffer = new byte[1024]; 
        DatagramPacket response = new DatagramPacket(buffer, buffer.length);

    	 try {
    		 InetAddress address = InetAddress.getByName(hostname);
             DatagramSocket socket = new DatagramSocket();
             
             while(true) {
  
                 DatagramPacket request = new DatagramPacket(new byte[1], 1, address, port);
                 
                 /* communication with the server */
                 socket.send(request);
                 socket.receive(response);
  
                 String WordofWisdom = new String(buffer, 0, response.getLength());
  
                 System.out.println(WordofWisdom);
                 System.out.println("****************************************");
  
                 Thread.sleep(5000); //every 5 seconds based on the assignment
                if (response.getData().equals(null)) {
                	break;
                }
             }
             
             socket.close(); //if socket is not closed, there will be warnings...
          
         /*
          * Following Errors are handled
          * Timeout, IOException, InterruptedException
          */
             
         } catch (SocketTimeoutException exception1) {
             System.err.println("Timeout error: " + exception1.getMessage());
             exception1.printStackTrace();
         } catch (IOException exeception2) {
             System.err.println("Client error: " + exeception2.getMessage());
             exeception2.printStackTrace();
         } catch (InterruptedException exception3) {
             System.err.println(" Connection Interrupted: " + exception3.getMessage());
        	 exception3.printStackTrace();
         }
 //    	 System.out.println("Server has stopped");
    	 System.exit(1);

     }	
    }

