package Polyline_Editor;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PolylineEditor {
    Polyline pline;
    private MyDrawPanel drawPanel;

    private PrintWriter writer;
    private BufferedReader reader;

    public class IncomingReader implements Runnable{
        public void run(){
            System.out.println("ready to recieve.");
            String msg;
            try {
                while ((msg = reader.readLine()) != null){
                    String[] tokens = msg.split(":");
                    if (tokens[0].equals(name)) continue;
                    if (tokens[1].equals("clear")) {
                        pline.clear();
                        drawPanel.repaint();
                    }

                    else if (tokens[1].equals("closed")){
                        pline.setClosed();
                        drawPanel.repaint();

                        pline = new Polyline(caster);
                        pline.clear();
                        drawPanel.setPolyline(pline);
                        drawPanel.addMouseListener(pline);
                        drawPanel.addMouseMotionListener(pline);
                    }

                    else {
                        pline.executeCommand(tokens[1]);
                        drawPanel.repaint();
                    }
                }
            } catch (Exception e){
            }
        }
    }

    private String	name;
    public PolylineEditor(Socket s, String n) {
        name = n;
        setUpNetworking(s);
        go();
        Thread t = new Thread(new PolylineEditor.IncomingReader());
        t.start();
    }

    private boolean setUpNetworking(Socket s) {
        try {
            writer = new PrintWriter(s.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch	(IOException e){
            return false;
        }
        return true;
    }

    class Broadcaster {
        public void broadcastCommand(String cmd){
            writer.println(name + ":" + cmd);
            writer.flush();
        }
    }

    private Broadcaster caster = new Broadcaster();
    public void go(){
        JFrame frame = new JFrame("Polyline Editor: " + name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        drawPanel = new MyDrawPanel();
        pline = new Polyline(caster);
        drawPanel.setPolyline(pline);
        drawPanel.addMouseListener(pline);
        drawPanel.addMouseMotionListener(pline);

        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

        JButton clearButton = new JButton("clear");
        panel.add(clearButton);

        JButton closeButton = new JButton("closed");
        panel.add(closeButton);

        frame.getContentPane().add((BorderLayout.SOUTH), panel);
        clearButton.addActionListener((event)->{
                pline.clear();
                drawPanel.repaint();
                caster.broadcastCommand("clear");
        });

        closeButton.addActionListener((event)->{
            pline.setClosed();
            drawPanel.repaint();

            pline = new Polyline(caster);
            pline.clear();
            drawPanel.setPolyline(pline);
            drawPanel.addMouseListener(pline);
            drawPanel.addMouseMotionListener(pline);

            caster.broadcastCommand("closed");
        });

        BorderLayout layout = (BorderLayout)frame.getContentPane().getLayout();
        layout.setHgap(10);
        layout.setVgap(10);

        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}
