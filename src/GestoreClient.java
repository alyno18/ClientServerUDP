import java.net.InetAddress;
import java.net.UnknownHostException;

public class GestoreClient {

	public static void main(String[] args) {
		Client cli = new Client(args[0], Integer.parseInt(args[1]));
		
        try {
			System.out.println("-- Running UDP Client at " + InetAddress.getLocalHost() + " --");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
        
        cli.start();
	}
}