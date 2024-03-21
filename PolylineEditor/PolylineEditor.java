import javax.swing.*;
import java.awt.*;

public class PolylineEditor {
    Polyline pline = new Polyline();

    public static void main(String[] args) {
        PolylineEditor gui = new PolylineEditor();
        gui.go();
    }

    public void go(){
        JFrame frame = new JFrame("Polyline Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        MyDrawPanel drawPanel = new MyDrawPanel();
        pline = new Polyline();
        drawPanel.setPolyline(pline);
        drawPanel.addMouseListener(pline);
        drawPanel.addMouseMotionListener(pline);

        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

        JButton clearButton = new JButton("clear");
        panel.add(clearButton);

        JButton closedButton = new JButton("closed");
        panel.add(closedButton);

        frame.getContentPane().add((BorderLayout.SOUTH), panel);
        clearButton.addActionListener((event)-> {
            pline.clear();
            drawPanel.repaint();
        });

        closedButton.addActionListener((event)-> {
            pline.setClosed();
            drawPanel.repaint();

            pline = new Polyline();
            pline.clear();
            drawPanel.setPolyline(pline);
            drawPanel.addMouseListener(pline);
            drawPanel.addMouseMotionListener(pline);
        });


        BorderLayout layout = (BorderLayout)frame.getContentPane().getLayout();
        layout.setHgap(10);
        layout.setVgap(10);

        frame.setSize(800, 800);
        frame.setVisible(true);
    }
}
