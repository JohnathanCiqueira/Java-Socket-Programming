

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

class QuoteService {
	Map<String, String> productInfo = new HashMap<String, String>();

	public QuoteService() {
		productInfo.put("a", "100");
		productInfo.put("b", "200");
	}

	public String getQuote(String product) {
		return productInfo.get(product);
	}
}

class ServiceThread extends Thread {
	
	Socket sock;
	
	QuoteService quoteService = new QuoteService();
	
	public ServiceThread(Socket sock) {
		this.sock = sock;
	}
	
	public void run() {
		
		try {
			InputStream in = sock.getInputStream();
			OutputStream out = sock.getOutputStream();
			
			System.out.println("Waiting fro product information from the client.");
			
			byte request[] = new byte[100];
			in.read(request);
			
			String product = new String(request).trim();
			
			System.out.println("Received product name - " + product);
			
			String price = quoteService.getQuote(product);
			if(price == null) {
				price = "Ivalid product";
			}
			
			out.write(price.getBytes());
			
			System.out.println("Response sent...");
			
			sock.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public class Server {

	public static void main(String[] args) throws IOException {

		ServerSocket serSocket = new ServerSocket(9999);

		System.out.println("Started listening to 9999");

		while (true) {
			System.out.println("Waiting for client...");
			Socket sock = serSocket.accept();
			
			// create a new thread to service client.
			System.out.println("Starting a thread which will service the client");
			new ServiceThread(sock).start();
		

		}
	}

}
