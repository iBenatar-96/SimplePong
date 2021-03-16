import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GamePanel extends JPanel implements Runnable{

    static final int WINDOW_WIDTH = 1500;
    static final int WINDOW_HEIGHT = 900;
    static final Dimension SCREEN_SIZE = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    static final int MARK_DIAMETER = 10;
    private boolean gameStarted;
    Random random;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Paddle paddle1;
    Paddle paddle2;
//    Paddle paddle3;
//    Paddle paddle4;
    Ball ball;
    Score score;

    GamePanel() throws InterruptedException {
        gameStarted = false;
        newPaddles();
        newBall();
        score = new Score(WINDOW_WIDTH,WINDOW_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new ActionListen());
        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();
    }
    public void newBall() {
        random = new Random();
        ball = new Ball((WINDOW_WIDTH/2)-(MARK_DIAMETER/2),random.nextInt((WINDOW_HEIGHT/2)-(MARK_DIAMETER/2)),MARK_DIAMETER,MARK_DIAMETER);
    }
    public void newPaddles(){
        paddle1 = new Paddle(0,(WINDOW_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT,1);
        paddle2 = new Paddle(WINDOW_WIDTH-PADDLE_WIDTH,(WINDOW_HEIGHT/2)-(PADDLE_HEIGHT/2), PADDLE_WIDTH, PADDLE_HEIGHT,2);
 //       paddle3 = new Paddle((WINDOW_WIDTH/2)-(PADDLE_HEIGHT/2),0, PADDLE_HEIGHT,PADDLE_WIDTH,3);
 //       paddle4 = new Paddle((WINDOW_WIDTH/2)-(PADDLE_HEIGHT/2),WINDOW_HEIGHT-PADDLE_WIDTH, PADDLE_HEIGHT,PADDLE_WIDTH,4);
    }
    public void paint(Graphics g){
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }
    public void draw(Graphics g){
        paddle1.draw(g);
        paddle2.draw(g);
//        paddle3.draw(g);
//        paddle4.draw(g);
        ball.draw(g);
        score.draw(g);
    }
    public void move() throws InterruptedException {
        paddle1.move();
        paddle2.move();
//        paddle3.move();
//        paddle4.move();
        if(!gameStarted) {
 //           Thread.sleep(1000);
            gameStarted = true;
        }
        ball.move();
    }
    public void checkCollision(){
   //     if(numOfPaddles == 2){
            if(ball.y<=0){
                ball.setYDirection(-ball.yVelocity);
            }
            if(ball.y >= (WINDOW_HEIGHT-MARK_DIAMETER)){
                ball.setYDirection(-ball.yVelocity);
            }
   //     }
        if(ball.intersects(paddle1)){
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
        }
        if(ball.intersects(paddle2)){
            ball.xVelocity = -ball.xVelocity;
            ball.xVelocity--;
        }
 /*       if(ball.intersects(paddle3)){
            ball.yVelocity = Math.abs(ball.yVelocity);
            ball.yVelocity++;
        }
        if(ball.intersects(paddle4)){
            ball.yVelocity = -ball.yVelocity;
            ball.yVelocity--;
        }*/
        ball.setYDirection(ball.yVelocity);
        ball.setXDirection(ball.xVelocity);
        if(paddle1.y<=0)
            paddle1.y=0;
        if(paddle1.y>=(WINDOW_HEIGHT-PADDLE_HEIGHT))
            paddle1.y = WINDOW_HEIGHT-PADDLE_HEIGHT;
/*        if(paddle3.x<=0)
            paddle3.x=0;
        if(paddle3.x>=(WINDOW_WIDTH-PADDLE_HEIGHT))
            paddle3.x = WINDOW_WIDTH-PADDLE_HEIGHT;*/
        if(paddle2.y<=0)
            paddle2.y=0;
        if(paddle2.y>=(WINDOW_HEIGHT-PADDLE_HEIGHT))
            paddle2.y = WINDOW_HEIGHT-PADDLE_HEIGHT;
/*        if(paddle4.x<=0)
            paddle4.x=0;
        if(paddle3.x>=(WINDOW_WIDTH-PADDLE_HEIGHT))
            paddle3.x = WINDOW_WIDTH-PADDLE_HEIGHT; */
        if(ball.x <= 0){
            score.player2++;
            newPaddles();
            newBall();
            System.out.println("Player 2 Score: " + score.player2);
        }
        if(ball.x >= WINDOW_WIDTH-BALL_DIAMETER){
            score.player1++;
            newPaddles();
            newBall();
            System.out.println("Player 1 Score: " + score.player1);
        }
    }
    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true){ //change it to running!!
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            if(delta >= 1){
                try {
                    move();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                checkCollision();
                repaint();
                delta--;
  //              System.out.println("TEST!");
            }
        }
    }
    public class ActionListen extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
 //           paddle3.keyPressed(e);
 //           paddle4.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
 //           paddle3.keyReleased(e);
 //           paddle4.keyReleased(e);
        }
    }
}
