public class GestoreServer {
	
	public static void main(String[] args) {
		Server srv = new Server(Integer.parseInt(args[0]));
        srv.listen();
        srv.send();
	}
}