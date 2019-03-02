import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame f = new JFrame("Game");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(600, 600);
        f.add(new Road());
        f.setVisible(true);
        
    }

}





