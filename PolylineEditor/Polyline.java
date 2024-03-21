import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Polyline extends MouseAdapter {
    private ArrayList<Point> mPts = new ArrayList<>();
    private boolean mClosed = false;
    private Point selectedPoint = null;
    private int temp_cnt = 0;

    public void clear(){
        mPts.clear();
    }

    public boolean isClosed(){
        return mClosed;
    }

    public void setClosed(){
        mClosed = true;
    }

    public int getNumPts(){
        return mPts.size();
    }

    public Point getPoint(int i){
        return mPts.get(i);
    }

    public void mousePressed(MouseEvent e){
        int x = e.getX();
        int y = e.getY();

        if (!mClosed) {
            for (Point point : mPts)
                if ((Math.abs(point.getX() - x) < 10) && (Math.abs(point.getY() - y) < 10)) {
                    selectedPoint = point;
                    temp_cnt++;
                }

            if (temp_cnt == 0) {
                mPts.add(new Point(x, y));
                selectedPoint = null;
                ((JPanel) e.getSource()).repaint();
            } else {
                temp_cnt--;
            }
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (!mClosed)
            if (selectedPoint != null) {
                selectedPoint.setX(e.getX());
                selectedPoint.setY(e.getY());
                ((JPanel) e.getSource()).repaint();
            }
    }
}
