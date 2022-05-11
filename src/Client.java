import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	private DatagramSocket udpSocket;
    private InetAddress serverAddress;
    private int port;
    private Scanner scanner;
    
    public Client(String destinationAddr, int port){
    	
        try {
			this.serverAddress = InetAddress.getByName(destinationAddr);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
        
        this.port = port;
        
        try {
			udpSocket = new DatagramSocket(this.port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
        scanner = new Scanner(System.in);
    }
    
    public int start(){
        String in;
        
        while (true) {
            in = scanner.nextLine();
            
            DatagramPacket p = new DatagramPacket(
                in.getBytes(), in.getBytes().length, serverAddress, port);
            
            try {
				this.udpSocket.send(p);
			} catch (IOException e) {
				e.printStackTrace();
			}                    
        }
    }
}