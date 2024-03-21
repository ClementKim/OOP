package simplenetworking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleChatServer {
	private ArrayList<PrintWriter> clientWriters = new ArrayList<>();

	public void go() {
		try	{
			ServerSocket server = new ServerSocket(4242);

			while (true) {
				Socket s = server.accept();
				echoMessages(s);
			}
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	private PrintWriter writer;

	ReentrantLock lock = new ReentrantLock();
	private void echoMessages(Socket sock) {
		new Thread(()-> {
			BufferedReader reader;
			try {
				this.lock.lock();
				writer = new PrintWriter(sock.getOutputStream());
				clientWriters.add(writer);
				this.lock.unlock();


				try {
					reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));

					while (true) {
						String s = reader.readLine();

						this.lock.lock();
						for (PrintWriter w : clientWriters) {
							w.println(s);
							w.flush();
						}
						this.lock.unlock();
					}

				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}).start();

	}
}