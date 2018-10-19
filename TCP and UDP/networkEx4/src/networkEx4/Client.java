package networkEx4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

	public static void main(String[] args) throws IOException {

		DatagramSocket dgSock = new DatagramSocket();

		String message = "Hello from so and so...";
		byte[] data = message.getBytes();

		DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 8989);

		dgSock.send(packet);

		dgSock.close();
	}
}
