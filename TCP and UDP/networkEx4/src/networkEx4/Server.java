package networkEx4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
	
	public static void main(String[] args) throws IOException {
		
		DatagramSocket dgSock = new DatagramSocket(8989);
	
		DatagramPacket packet = new DatagramPacket(new byte[1000], 1000);
		
		dgSock.receive(packet);
		
		System.out.println(new String(packet.getData()));
		System.out.println("Obtained from IP - " + packet.getAddress());
		System.out.println("Obtained from Port - " + packet.getPort());
		
		dgSock.close();
	}
}
