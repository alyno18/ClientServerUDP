import java.net.InetAddress;
import java.net.UnknownHostException;

public class GestoreClient {

	public static void main(String[] args) {
		Client cli = null;
		
		try {
			cli = new Client(InetAddress.getLocalHost(), 2000);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		
        try {
			System.out.println("-- Running UDP Client at " + InetAddress.getLocalHost() + " --");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
        
        cli.start();
        cli.receive();
	}
}