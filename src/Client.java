import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Client {
	private DatagramSocket udpSocket;
	private DatagramPacket in;
    private InetAddress serverAddress;
    private int port;
    private Scanner scanner;
    
    public Client(InetAddress ip, int port){
    	
		this.serverAddress = ip;
        this.port = port;
        
        try {
			udpSocket = new DatagramSocket(this.port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
        scanner = new Scanner(System.in);
    }
    
    public void start(){
        String in = "";
        String q ="quit";
        
        while (!in.equals(q)) {
        	System.out.println("scrivi qualcosa: ");
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
    
    public void receive() {
    	byte[] buf = new byte[256];
    
    	in = new DatagramPacket(buf, buf.length);
    	try {
			udpSocket.receive(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String r = new String(in.getData(), 0, in.getLength());
		System.out.println("CLIENT Messaggio da: " + in.getAddress().getHostAddress() + ": " + r);
    }
}