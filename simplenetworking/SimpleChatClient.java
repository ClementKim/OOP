package simplenetworking;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

public class SimpleChatClient {
	public void go(String name) {
		try {
			Socket sock = new Socket("127.0.0.1", 4242);
			SwingUtilities.invokeLater(() -> new SimpleChatGUI(sock, name));
		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}