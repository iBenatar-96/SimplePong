import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Ball extends Rectangle{
    Random random;
    int xVelocity;
    int yVelocity;
    int speed = 7;
    boolean gameStarted = false;

    Ball(int xPos, int yPos, int width, int height){
        super(xPos,yPos,width,height);
        random = new Random();

        int randomXDirection = random.nextInt(2);
        if(randomXDirection == 0)
            randomXDirection--;
        setXDirection(randomXDirection*speed);

        int randomYDirection = random.nextInt(2);
        if(randomYDirection == 0)
            randomYDirection--;
        setYDirection(randomYDirection*speed);
    }
    public void setXDirection(int randXDir){
        xVelocity = randXDir;
    }
    public void setYDirection(int randYDir){
        yVelocity = randYDir;
    }
    public void move(){
        x += xVelocity;
        y += yVelocity;
    }
    public void draw(Graphics g){
        g.setColor(Color.white);
        g.fillOval(x,y,width,height);
    }
}
