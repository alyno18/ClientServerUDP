import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;

public class Server {
	private DatagramSocket udpSocket;
	private DatagramPacket in;
    private int port;
 
    public Server(int port){
        this.port = port;
        
        try {
			this.udpSocket = new DatagramSocket(this.port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
    }
    public void listen(){
        try {
			System.out.println("-- server in ascolto: " + InetAddress.getLocalHost() + "--");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
        
        String msg;
        
        while (true) {
            
            byte[] buf = new byte[256];
            in = new DatagramPacket(buf, buf.length);
            try {
				udpSocket.receive(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
            msg = new String(in.getData());
            
            System.out.println("SERVER Messaggio da. " + in.getAddress().getHostAddress() + ": " + msg);
        }
    }
    
    public void send() {
    	Date d = new Date();
    	InetAddress clientAddress = in.getAddress();
		int clientPort = in.getPort();
		String msg = d.toString();
		DatagramPacket out = new DatagramPacket(msg.getBytes(), msg.length(), clientAddress, clientPort);
		try {
			udpSocket.send(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}