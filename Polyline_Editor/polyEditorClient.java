package Polyline_Editor;

import java.net.Socket;

public class polyEditorClient {
	public void go(String name) {
		try {
			Socket sock = new Socket("127.0.0.1", 4242);
			new PolylineEditor(sock, name);
		} catch (Exception e){
		}
	}

}
