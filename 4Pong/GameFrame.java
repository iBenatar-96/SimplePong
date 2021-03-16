import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GameFrame extends JFrame{

    GamePanel panel;

    GameFrame() throws InterruptedException {
         panel = new GamePanel();
         this.add(panel);
         this.setTitle("4Pong Game");
         this.setResizable(false);
         this.setBackground(Color.black);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.pack();
         this.setVisible(true);
         this.setLocationRelativeTo(null);
    }
}
