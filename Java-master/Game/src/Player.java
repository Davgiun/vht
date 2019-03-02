import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener {
    Image img_c = new ImageIcon("/Users/enter/Desktop/Game/out/production/Game/files/car1.png").getImage();
    Image img_up = new ImageIcon("/Users/enter/Desktop/Game/out/production/Game/files/car3.png").getImage();
    Image img_down = new ImageIcon("/Users/enter/Desktop/Game/out/production/Game/files/car4.png").getImage();
Image img= img_c;
    public static final int MAX_V = 50;
    public static final int TOP = 15;
    public static final int BOTTOM = 515;
    int v = 0;
    int u = 0;
    int s = 0;
    int backgr1 = 0;
    int backgr2 = 600;
    int x = 30;
    int y = 113;
    int dy = 0;
     public Rectangle getRect() {
         return new Rectangle(x, y, 142, 65);

     }

    public void move() {
        s += v;
        v += u;
        if(v<=0) {
            v=0;
        }
        if(v>=MAX_V){
            v=MAX_V;
        }
        y -= dy;
        if(y<=TOP) {
            y = TOP;
        }
            if(y>=BOTTOM){
                y=BOTTOM;

        }
        if (backgr2 - v <= 0) {
            backgr1 = 0;
            backgr2 = 600;

        } else {
            backgr1 -= v;
            backgr2 -= v;

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //JOptionPane.showMessageDialog(null, "key pressed");
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            u = 1;
            img=img_c;

        }
        if (key == KeyEvent.VK_LEFT) {
            u = -1;
        }
        if (key == KeyEvent.VK_UP) {
            dy = 5;
            img=img_up;
        }
        if (key == KeyEvent.VK_DOWN) {
            dy = -5;
            img=img_down;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //JOptionPane.showMessageDialog(null, "key released");
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
            u = 0;

        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            dy = 0;
            img=img_c;


        }
    }
}
