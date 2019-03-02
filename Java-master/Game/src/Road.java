import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Road extends JPanel implements ActionListener, Runnable {
    Timer t = new Timer(20, this);
    Image img = new ImageIcon("/Users/enter/Desktop/Game/out/production/Game/files/Road1SquarePic.jpg").getImage();
    Player p = new Player();
    Thread newEnemies = new Thread(this);
    //Thread audio = new Thread(new Audio());
    Audio audio = new Audio(new File("out/production/Game/files/getlow.wav"));
    java.util.List<Enemy> enemies = new ArrayList<Enemy>();

    public Road() {
        t.start();
        newEnemies.start();
//        audio.start();
        audio.play();
        addKeyListener(new Key());
        setFocusable(true);
    }

    @Override
    public void run() {
        while (true) {
            Random r = new Random();
            try {
                Thread.sleep(r.nextInt(4000));
                enemies.add(new Enemy(600, r.nextInt(600), r.nextInt(10), this));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class Key extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            addKeyListener(p);
            p.keyPressed(e);

        }

        public void keyReleased(KeyEvent e) {

        }
    }

    public void paint(Graphics g) {
        g = (Graphics2D) g;
        ((Graphics2D) g).drawImage(img, p.backgr1, 0, null);
        ((Graphics2D) g).drawImage(img, p.backgr2, 0, null);
        ((Graphics2D) g).drawImage(p.img, p.x, p.y, null);
        double v = (100 / Player.MAX_V) * p.v;
        g.setColor(Color.pink);
        Font font = new Font("Arial", Font.ITALIC, 20);
        g.setFont(font);
        g.drawString("Speed:" + v + "mph.", 50, 30);
        double s = 1 * p.s;
        g.setColor(Color.pink);
        Font font_1 = new Font("Arial", Font.ITALIC, 20);
        g.setFont(font_1);
        g.drawString("Score:" + s + "miles.", 300, 30);
        Iterator<Enemy> i = enemies.iterator();
        while (i.hasNext()) {
            Enemy e = i.next();
            if (e.x >= 2400 || e.x <= -2400) {
                i.remove();
            } else {
                e.move();

                ((Graphics2D) g).drawImage(e.img, e.x, e.y, null);
            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        p.move();
        repaint();
        testCollisionsWithEnemies();
        testWin();

    }

    private void testWin() {
        if (p.s >= 20000) {
            JOptionPane.showMessageDialog(null, "You won.HOW????");
            System.exit(0);
        }
    }

    private void testCollisionsWithEnemies() {
        Iterator<Enemy> i = enemies.iterator();
        while (i.hasNext()) {
            Enemy e = i.next();
            if (p.getRect().intersects(e.getRect())) {
                i.remove();
                JOptionPane.showMessageDialog(null, "Looser");
                System.exit(0);
            }

        }

    }
}
