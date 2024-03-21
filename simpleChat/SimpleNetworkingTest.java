package simpleChat;


import Polyline_Editor.PolylineEditor;
import Polyline_Editor.polyEditorClient;

public class SimpleNetworkingTest {
	public static void main(String[] args) {

		new Thread(() -> new SimpleChatServer().go()).start();

		try {
			Thread.sleep(1000);
		} catch (Exception ex) {
		}

		new Thread(() -> new polyEditorClient().go("TalkerA")).start();
		new Thread(() -> new polyEditorClient().go("TalkerB")).start();
		new Thread(() -> new polyEditorClient().go("TalkerC")).start();

	}
}
