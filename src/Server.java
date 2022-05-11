import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Server {
	private DatagramSocket udpSocket;
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
			System.out.println("-- Running Server at " + InetAddress.getLocalHost() + "--");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
        
        String msg;
        
        while (true) {
            
            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
				udpSocket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
            msg = new String(packet.getData()).trim();
            
            System.out.println(
                "Message from " + packet.getAddress().getHostAddress() + ": " + msg);
        }
    }
}