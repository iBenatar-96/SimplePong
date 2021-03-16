import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Paddle extends Rectangle{

    private int id;
    private int yVelocity;
    private int xVelocity;
    int speed = 10;

    Paddle(int xPos, int yPos, int pW, int pH, int id){
        super(xPos,yPos,pW,pH);
        this.id = id;
    }
    public void keyPressed(KeyEvent e){
        switch(id){
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    setYDirection(-speed);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    setYDirection(speed);
                    move();
                }
                break;
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){
                    setYDirection(-speed);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_S){
                    setYDirection(speed);
                    move();
                }
                break;
        }
    }
    public void keyReleased(KeyEvent e){
        switch(id){
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    setYDirection(0);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    setYDirection(0);
                    move();
                }
                break;
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){
                    setYDirection(0);
                    move();
                }
                if(e.getKeyCode()==KeyEvent.VK_S){
                    setYDirection(0);
                    move();
                }
                break;
        }
    }
    public void setYDirection(int yDirection){
        yVelocity = yDirection;
    }
    public void setXDirection(int xDirection){
        xVelocity = xDirection;
    }
    public void move(){
        y = y + yVelocity;
        x = x + xVelocity;
    }
    public void draw(Graphics g){
        g.setColor(Color.white);
        g.fillRect(x,y,width,height);
    }
}
