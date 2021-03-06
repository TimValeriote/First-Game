/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;

/**
 *
 * @author valet8115
 */
// make sure you rename this class if you are doing a copy/paste
public class Terp extends JComponent implements KeyListener {

    // Height and Width of our game
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    // sets the framerate and delay for our game
    // you just need to select an approproate framerate
    long desiredFPS = 60;
    long desiredTime = (1000) / desiredFPS;
    //screens
    final int MAIN = 0;
    final int MODE = 1;
    final int HOWTO = 6;
    final int SLVL = 2;
    final int LVL1 = 3;
    final int LVL2 = 4;
    final int LVL3 = 5;
    final int LVL4 = 8;
    final int LVL5 = 9;
    final int NOPE = 7;
    final int END = 11;
    final int SLOSE = 17;
    final int HINT = 18;
    final int TUT = 19;
    final int RMODE = 12;
    final int RLVL1 = 13;
    final int RLVL2 = 14;
    final int RLVL1p1 = 15;
    final int RLVL1p3 = 16;
    
    //images
    BufferedImage main = ImageHelper.loadImage("MainScreen.png");
    BufferedImage mode = ImageHelper.loadImage("Load Screen.png");
    BufferedImage background = ImageHelper.loadImage("background.png");
    BufferedImage b1 = ImageHelper.loadImage("50x10 block.png");
    BufferedImage b2 = ImageHelper.loadImage("50x50 block.png");
    BufferedImage b3 = ImageHelper.loadImage("35x400 block.png");
    BufferedImage b4 = ImageHelper.loadImage("20x400 block.png");
    BufferedImage b5 = ImageHelper.loadImage("100x10 block.png");
    BufferedImage b6 = ImageHelper.loadImage("40x40 block.png");
    BufferedImage lvlselect = ImageHelper.loadImage("lvlselectnew.png");
    BufferedImage nope = ImageHelper.loadImage("Nope_edited-1.png");
    BufferedImage teemo = ImageHelper.loadImage("teemo.png");
    BufferedImage howto = ImageHelper.loadImage("howto.png");
    BufferedImage finish = ImageHelper.loadImage("finish.png");
    BufferedImage gnar = ImageHelper.loadImage("gnar.png");
    BufferedImage ENDpic = ImageHelper.loadImage("END.png");
    BufferedImage rmain = ImageHelper.loadImage("racemainnew.png");
    BufferedImage rbackground = ImageHelper.loadImage("rmodebackground.png");
    BufferedImage rbackground1 = ImageHelper.loadImage("rmodebackground1.png");
    BufferedImage rmodep1win = ImageHelper.loadImage("rmode p1win.png");
    BufferedImage rmodep3win = ImageHelper.loadImage("rmode p2win.png");
    BufferedImage slose = ImageHelper.loadImage("slose.png");
    BufferedImage hint = ImageHelper.loadImage("hintnew.png");
    BufferedImage congrats = ImageHelper.loadImage("tutorial.png");
    BufferedImage powerup = ImageHelper.loadImage("powerup.png");
    int screen = MAIN;
    boolean change = false;
    //players
    Rectangle player1 = new Rectangle(20, 550, 30, 30);
    Rectangle player2 = new Rectangle(20, 250, 30, 30);
    //misc
    int ground = 550;
    int groundr = 590;
    int groundrb = 260;
    int gravity = 2;
    boolean sjumpEnd = false;
    int speedlvl1 = 4;
    int p1speed = 5;
    int p2speed = 5;
    int speed2 = 4;
    int camx = 0;
    int camy = 0;
    int camx3 = 0;
    //single player variables
    int p1dy = 0;
    boolean p1inAir = false;
    int p1sjumpy = 10;
    boolean p1sjumping = false;
    boolean p1sinAir = false;
    boolean p1inAirs = false;
    boolean lvls = false;
    boolean count = false;
    int slives = 7;
    boolean hintchange = false;
    //race mode variables
    int p1diff = 0;
    int p2diff = 0;
    boolean powerupgetp1 = false;
    boolean powerupgetp2 = false;
    boolean powerupshow = true;
    int pupspeed2;
    //player 1 controls
    boolean p1jump = false;
    boolean p1left = false;
    boolean p1right = false;
    boolean p1sjump = false;
    int p1l2count = 3;
    int p1l5count = 2;
    boolean p1nomove = false;
    //player 2 controls for race mode
    boolean p2jump = false;
    boolean p2left = false;
    boolean p2right = false;
    boolean p2inAir = false;
    int p2dy = 0;
    //player 3 controls for race mode
    
    //arrays for blocks in single player
    Rectangle[] rectangles1 = {new Rectangle(150, 450, 50, 50), new Rectangle(100, 450, 50, 50), new Rectangle(250, 450, 50, 50), new Rectangle(450, 400, 50, 50), new Rectangle(400, 400, 50, 50), new Rectangle(600, 300, 50, 50),
        new Rectangle(550, 300, 50, 50), new Rectangle(750, 200, 50, 50), new Rectangle(1000, 400, 50, 50), new Rectangle(950, 400, 50, 50), new Rectangle(1050, 400, 50, 50), new Rectangle(1100, 400, 50, 50), new Rectangle(1150, 400, 50, 50)};
    Rectangle[] rectangles2 = {new Rectangle(150, 250, 50, 10), new Rectangle(200, 250, 50, 10), new Rectangle(450, 450, 50, 10), new Rectangle(600, 400, 50, 10), new Rectangle(750, 150, 50, 10),
        new Rectangle(1000, 475, 50, 10), new Rectangle(1200, 400, 50, 10), new Rectangle(1250, 400, 50, 10), new Rectangle(1300, 400, 50, 10)};
    Rectangle[] rectangles3 = {new Rectangle(100, 500, 35, 400), new Rectangle(200, 400, 35, 400), new Rectangle(300, 325, 35, 400),
        new Rectangle(500, 500, 35, 400), new Rectangle(600, 350, 35, 400)};
    Rectangle[] rectangles4 = {new Rectangle(100, 500, 50, 10), new Rectangle(0, 350, 50, 10), new Rectangle(100, 200, 50, 10), new Rectangle(0, 50, 50, 10), new Rectangle(100, -100, 50, 10), new Rectangle(300, -50, 50, 10),
        new Rectangle(520, 150, 50, 10), new Rectangle(775, 400, 50, 10),};
    Rectangle[] rectangles5 = {new Rectangle(100, 400, 40, 40), new Rectangle(220, 300, 40, 40), new Rectangle(350, 200, 40, 40),
        new Rectangle(500, 450, 40, 40), new Rectangle(675, 300, 40, 40), new Rectangle(800, 300, 40, 40), new Rectangle(950, 525, 40, 40), new Rectangle(1150, 525, 40, 40)};
    Rectangle[][] allRectangles = {rectangles1, rectangles2, rectangles3, rectangles4, rectangles5};
    int curLevel = 0;
    //lvl 1 misc block
    Rectangle l1finish = new Rectangle(1050, 300, 50, 100);
    //lvl 2 misc block
    Rectangle l2finish = new Rectangle(1250, 300, 50, 100);
    //lvl 3 labeled as L4 but really L3 misc blocks
    Rectangle l4b5 = new Rectangle(400, 200, 20, 400);
    Rectangle l4b9 = new Rectangle(800, 500, 200, 30);
    Rectangle l4finish = new Rectangle(950, 400, 50, 100);    
    //lvl 4 labeled as L3 bu really L4 misc blocks
    Rectangle l3b0 = new Rectangle(150, -100, 50, 900);
    Rectangle l3b9 = new Rectangle(1000, 450, 200, 10);
    Rectangle l3finish = new Rectangle(1050, 350, 50, 100);
    //lvl 5 misc blocks
    Rectangle l5b3 = new Rectangle(350, -450, 40, 590);
    Rectangle l5b9 = new Rectangle(1300, 530, 100, 30);
    //end of single player blocks/rectangles
    //start of race mode blocks/rectagnles
    Rectangle[] recrace1 = { new Rectangle (275, 490, 50, 100), new Rectangle (500, 520, 30, 70),new Rectangle (700, 550, 40, 40),new Rectangle (740, 510, 40, 80), new Rectangle (780, 470, 40, 120), new Rectangle(820, 430, 40, 160),new Rectangle (860, 430, 350, 160),new Rectangle (920, 370, 30, 60),new Rectangle (1100, 360, 50, 70),
    new Rectangle (1470, 510, 240, 20),new Rectangle (1670, 530, 40, 60),new Rectangle (1820, 480, 75, 20),new Rectangle (1990, 460, 75, 20),new Rectangle (2160, 440, 75, 20),new Rectangle (2320, 420, 400, 20),new Rectangle (2100, 530, 40, 60),new Rectangle (2350, 540, 50, 50),new Rectangle (2575, 540, 40, 50),new Rectangle (2700, 500, 20, 90),
    new Rectangle (0, 0, 0, 0),new Rectangle (0, 0, 0, 0),new Rectangle (0, 0, 0, 0),new Rectangle (0, 0, 0, 0),new Rectangle (0, 0, 0, 0),new Rectangle (0, 0, 0, 0),new Rectangle (0, 0, 0, 0),new Rectangle (0, 0, 0, 0),new Rectangle (0, 0, 0, 0),new Rectangle (0, 0, 0, 0),};
    Rectangle base1 = new Rectangle (0, 290, 3200, 10);
    Rectangle base2 = new Rectangle (0, 590, 3200, 10);
    Rectangle top = new Rectangle (10,-10,3200,10);
    Rectangle r1p1collide = new Rectangle (0, 200, 3200, 100);
    Rectangle r2p1collide = new Rectangle (0, 200, 3200, 100);
    Rectangle r1p2collide = new Rectangle (0, -50, 3200, 50);
    Rectangle r2p2collide = new Rectangle (0, -50, 3200, 50);
    Rectangle[] recrace1b;
    
    Rectangle[] recrace2 = { new Rectangle (300, 500, 50, 90), new Rectangle (600, 460, 40, 150),new Rectangle (600, 390, 40, - 100 ),new Rectangle (825, 560, 250, 40), new Rectangle (1100, 400, 100, 20), new Rectangle(1275, 380, 75, 20),new Rectangle (1400, 370, 75, 20),new Rectangle (1525, 380, 75, 20),new Rectangle (1650, 400, 75, 20),
    new Rectangle (1150, 540, 30, 50),new Rectangle (1300, 540, 30, 50),new Rectangle (1450, 550, 30, 40),new Rectangle (1600, 550, 30, 40),new Rectangle (1750, 550, 30, 40),new Rectangle (2000, 460, 40, 150),new Rectangle (2000, 390, 40, - 100),new Rectangle (2200, 550, 40, 40),new Rectangle (2240, 510, 40, 80),new Rectangle (2280, 470, 40, 120),
    new Rectangle (2320, 430, 40, 160),new Rectangle (2360, 390, 200, 200),new Rectangle (2430, 350, 40, 70),new Rectangle (2700, 500, 50, 90),new Rectangle (0, 0, 0, 0),new Rectangle (0, 0, 0, 0),new Rectangle (0, 0, 0, 0),new Rectangle (0, 0, 0, 0),new Rectangle (0, 0, 0, 0),new Rectangle (0, 0, 0, 0),};
    Rectangle[] recrace2b;
    
    Rectangle pup1 = new Rectangle (1550, 540, 40, 40);
    Rectangle pup2 = new Rectangle (1550, 240, 40, 40);
    int pupspeed = 1;
    
    //dialog boxes
    Object[] modeSelect = {"Single Player", "Co-Op", "Race Mode", "How To Play"};
    Object[] lvlSelect = {"Tutorial Level", "1", "2", "3", "4"};
    Object[] rmodeSelect = {"1", "2"};
    // drawing of the game happens in here
    // we use the Graphics object, g, to perform the drawing
    // NOTE: This is already double buffered!(helps with framerate/speed)

    @Override
    public void paintComponent(Graphics g) {
//        for (int i =0; i < allRectangles[curLevel].length; i ++)
//        {
//           allRectangles[curLevel][i].intersects(player1);
//        }
        // always clear the screen first!
        g.clearRect(0, 0, WIDTH, HEIGHT);

        // GAME DRAWING GOES HERE
        if (screen == 2) {
            g.drawImage(lvlselect, 0, 0, null);
        }
        if (screen == 0) {
            g.drawImage(main, 0, 0, null);
        }
        if (screen == 1) {
            g.drawImage(mode, 0, 0, null);
        }
        if (screen == 7) {
            g.drawImage(nope, 0, 0, null);
        }
        if (screen == 6) {
            g.drawImage(howto, 0, 0, null);
        }

        if (screen == 3) {
            drawSL1(g);
        }
        if (screen == 4) {
            drawSL2(g);
        }
        if (screen == 5) {
            drawSL3(g);
        }
        if (screen == 8) {
            drawSL4(g);

        }
        if (screen == 9) {
            drawSL5(g);
        }
        if (screen == END) {
            g.drawImage(ENDpic, 0, 0, null);
        }
        if (screen == RMODE) {
            g.drawImage(rmain, 0, 0, null);
        }
        if (screen == RLVL1) {
            drawRL1(g);
        }
        if (screen == 14){
            drawRL2(g);
        }
        if (screen == 15){
            g.drawImage(rmodep1win, 0, 0, null);
        }
        if (screen == 16){
            g.drawImage(rmodep3win, 0, 0, null);
        }
        if (screen == 17){
            g.drawImage(slose, 0, 0, null);
        }
        if (screen == 18){
            g.drawImage(hint, 0, 0, null);
        }
        if (screen == 19){
            g.drawImage(congrats, 0, 0, null);
        }
        


        // GAME DRAWING ENDS HERE
    }

    // The main game loop
    // In here is where all the logic for my game will go
    public void run() {
        // Used to keep track of time used to draw and update the game
        // This is used to limit the framerate later on
        long startTime;
        long deltaTime;
//        if (screen == 1){
//        String s = (String)JOptionPane.showInputDialog(null, "Please select a mode", "Mode", JOptionPane.QUESTION_MESSAGE, null, modeSelect, "Single Player");
//        }
        // the main game loop section
        // game will end if you set done = false;
        recrace1b = new Rectangle[recrace1.length];
        for(int i = 0; i < recrace1.length; i++)
        {
            recrace1b[i] = (Rectangle)recrace1[i].clone();
            recrace1b[i].y -= 300;
        }
        recrace2b = new Rectangle[recrace2.length];
        for(int i = 0; i < recrace2.length; i++)
        {
            recrace2b[i] = (Rectangle)recrace2[i].clone();
            recrace2b[i].y -= 300;
        }
        
        boolean done = false;
        while (!done) {
            // determines when we started so we can keep a framerate
            startTime = System.currentTimeMillis();

            // all your game rules and move is done in here
            // GAME LOGIC STARTS HERE 
            //screen logic
            String l = "";
            String s = "";
            String r = "";
            if (change) {
                if (screen == MAIN) {
                    screen = MODE;
                    change = false;
                }
            } else if (screen == 1) {
                s = (String) JOptionPane.showInputDialog(null, "Please select a mode", "Mode Select", JOptionPane.QUESTION_MESSAGE, null, modeSelect, "Single Player");
                if (s == modeSelect[0]) {
                    screen = 2;
                    change = false;
                } else if (s == modeSelect[1]) {//if they select co-op then the screen goes to the nope screen
                    screen = 7;
                    change = false;
                } else if (s == modeSelect[2]) {//if they select race mode then the screen goes to the nope screen
                    screen = 12;
                    change = false;
                } else if (s == modeSelect[3]) {//if they select how to then the screen goes to the how to screen
                    screen = 6;
                    change = false;
                }
            } else if (screen == 2) {
                l = (String) JOptionPane.showInputDialog(null, "Please select a level", "Level Select", JOptionPane.QUESTION_MESSAGE, null, lvlSelect, "Level Select");
                if (l == lvlSelect[0]) {//level 1
                    screen = 3;
                    change = false;
                } else if (l == lvlSelect[1]) {//level 2
                    screen = 4;
                    change = false;
                } else if (l == lvlSelect[2]) {//level 3
                    screen = 5;
                    change = false;
                } else if (l == lvlSelect[3]) {//level 4
                    screen = 8;
                    change = false;
                } else if (l == lvlSelect[4]) {//level 5
                    screen = 9;
                    change = false;
                }
            } else if (screen == 12) {
                r = (String) JOptionPane.showInputDialog(null, "Please select a race level", "Level", JOptionPane.QUESTION_MESSAGE, null, rmodeSelect, "Level Select");
                if (r == rmodeSelect[0]) {
                    screen = 13;
                    change = false;

                } else if (r == rmodeSelect[1]) {
                    screen = 14;
                    change = false;
                }
            }


            if (screen == 6) {//if player is on the howto and they hit the change button, the game goes to mode screen
                if (change) {
                    screen = 0;
                    change = false;
                }
            }
            if (screen == 7) {//if player is on the nope and they hit the change button, the game goes to mode screen
                if (change) {
                    screen = 0;
                    change = false;
                }
            }
            if (screen == 11) {//if player is on the end and they hit the change button, the game goes to mode main screen 

                if (change) {
                    player1.x = 20;
                    player1.y = 550;
                    screen = 0;
                    change = false;
                    slives = 7;
                    
                }
            }
            if (screen == 15) {//if player is on the end and they hit the change button, the game goes to mode main screen 

                if (change) {
                    screen = 0;
                    change = false;
                }
            }
            if (screen == 16) {//if player is on the end and they hit the change button, the game goes to mode main screen 

                if (change) {
                    screen = 0;
                    change = false;
                }
            }
            if (screen == 17){
                if (change){
                    screen = 0;
                    change = false;
                    slives = 7;
                }
            }
            if (screen == 18){
                if(change){
                    screen = 4;
                    change = false;
                }
            }
            if (screen == 19){
                if(hintchange){
                    screen = 18;
                    hintchange = false;
                }else if(change){
                    screen = 4;
                    change = false;
                }
            }

            if (screen == 3 || screen == 4 || screen == 5 || screen == 8 || screen == 9 || screen == 10) { //if the player is in single player mode         
                
                if (slives == 0){
                    screen = 17;
                }
                
                if (screen == 3) {//players cant stall jump on level 1
                    p1sjump = false;
                }
                if (screen == 5) {//players cannot stall jump on level 3 
                    p1sjump = false;
                }
                if (screen == 8) {//players cannot stall jump on level 4 
                    p1sjump = false;
                }

                //single player logic

                if (player1.x < WIDTH / 2) { //camx logic
                    camx = 0;
                } else {
                    camx = player1.x - WIDTH / 2;
                }
                if (player1.y > HEIGHT / 2) {
                    camy = 0;
                } else {
                    camy = player1.y - HEIGHT / 2;
                }
                //jumping and moving
                p1dy = p1dy + gravity;

                if (p1right && !p1inAirs) {//player 1 move right as long as he/she is not sjumping
                    player1.x = player1.x + p1speed;
//                  for (Rectangle rect: allRectangles)
//                {
//                      if (player1.intersects(rect))
//                    {
//                          player1.y = rect.x+rect.width;
//                    }
//                }
//                  
//                    
//                        for (int j = 0; j < allRectangles[curLevel].length; j++) {
//                            
//                        }                   
                }
                if (p1left && !p1inAirs) {//player 1 move left as long as he/she is not sjumping
                    player1.x = player1.x - p1speed;
//                  for (Rectangle rect: allRectangles)
//                {
//                      if (player1.intersects(rect))
//                    {
//                          player1.y = rect.x+rect.width;
//                    }
//                }
                }
                if (p1sjumpy > 0) {
                    p1dy = 0;
                    p1sjumpy -= gravity;
                }
                if (p1jump && !p1inAir) {//player 1 jumps
                    p1sjumping = false;
                    p1dy = -30;
                    p1inAir = true;
                    p1inAirs = false;
                }
                if (p1sjumping) {
                    p1dy = 0;
                    player1.y -= p1sjumpy;
                }

                player1.y += p1dy;
                if (player1.y > ground) {
                    player1.y = ground;
                    p1dy = 0;
                    p1inAir = false;
                }
                if (p1sjump & !p1sjumping & !p1inAir) {//if player 1 sjumps and player 1 is not already sjumping and player 1 is not in the air then they can sjump

                    p1dy = 0;
                    p1sjumpy = 30;
                    p1sjumping = true;
                    p1inAirs = true;
                }
                if (p1dy > 30) {
                    p1dy = 30;
                }


                //lvl data and commands
                if (player1.x >= 100 && player1.y == 550) {//if the player hits the ground he/she is sent back to start
                    player1.x = 20;
                    slives = slives - 1;
                    

                }
                if (screen == 3) {
                    if (player1.y <= 0) {//cannot go into the ceiling on level 1
                        player1.y = 0;
                    }
                } else if (screen == 4) {
                    if (player1.y <= 0) {//cannot go into the ceiling on level 2
                        player1.y = 0;
                    }
                } else if (screen == 5) {
                    if (player1.y <= 0) {//cannot go into ceiling on level 3
                        player1.y = 0;
                    }
                }
                if (player1.x <= 0) {//cannot go past x = 0
                    player1.x = 0;
                }
                if (screen == 3 && player1.x == 1050) {//ends lvl 1 at 1050 coords and starts lvl 2
                    screen = TUT;
                    player1.x = 20;
                    player1.y = 550;
                }
                if (screen == 4 && player1.x == 1250) {//ends lvl 2 at 1250 coords and starts lvl 3
                    screen = LVL3;
                    player1.x = 20;
                    player1.y = 550;
                }
                if (screen == 5 && player1.x == 950) {//ends lvl 3 at 950 coords and starts lvl 4
                    screen = LVL4;
                    player1.x = 20;
                    player1.y = 550;
                }
                if (screen == 8 && player1.x == 1100) {//end lvl 4 at 1100 coords and starts lvl 5
                    screen = LVL5;
                    player1.x = 20;
                    player1.y = 550;
                }
                if (screen == 9 && player1.x == 1350) {//ends level 5 at 1400 and start the end screen 
                    screen = END;
                }


                //collisions for single player
                if (screen == 3) {//collisions for level 1
                    for (int i = 0; i < rectangles1.length; i++) { // this for loop handles all collisions with the rectangles in level 1
                        if (player1.intersects(rectangles1[i])) {
                            handleCollision(player1, rectangles1[i]);
                        }
                    }
                } else if (screen == 4) {//collisions for level 2

                    for (int i = 0; i < rectangles2.length; i++) {// this for loop handles all collisions with the rectangles in level 2
                        if (player1.intersects(rectangles2[i])) {
                            handleCollision(player1, rectangles2[i]);
                        }
                    }

                }
                if (screen == 5) {//collisions for level 3
                    for (int i = 0; i < rectangles3.length; i++) {// this for loop handles all collisions with the rectangles in level 3
                        if (player1.intersects(rectangles3[i])) {
                            handleCollision(player1, rectangles3[i]);
                        }
                    }

                    if (player1.intersects(l4b9)) {// this if statment handles a collision with an extra block in level 3
                        handleCollision(player1, l4b9);

                    }
                }
                if (screen == 8) {//collisions for level 4
                    for (int i = 0; i < rectangles4.length; i++) {// this for loop handles all collisions with the rectangles in level 4
                        if (player1.intersects(rectangles4[i])) {
                            handleCollision(player1, rectangles4[i]);
                        }
                    }
                    if (player1.intersects(l3b0)) {// this if statment handles a collision with an extra block in level 4
                        handleCollision(player1, l3b0);

                    }
                    if (player1.intersects(l3b9)) {// this if statment handles a collision with an extra block in level 4
                        handleCollision(player1, l3b9);

                    }
                }
                if (screen == 9) {//collisions for level 5
                    for (int i = 0; i < rectangles5.length; i++) {// this for loop handles all collisions with the rectangles in level 5
                        if (player1.intersects(rectangles5[i])) {
                            handleCollision(player1, rectangles5[i]);
                        }
                    }


                    if (player1.intersects(l5b3)) {// this if statment handles a collision with an extra block in level 5
                        handleCollision(player1, l5b3);
                    }
                    if (player1.intersects(l5b9)) {// this if statment handles a collision with an extra block in level 5
                        handleCollision(player1, l5b9);
                    }
                }

            } else if (screen == 13 || screen == 14) {//this is race mode logic:
                
                p1diff = player1.x - player2.x;
                
                p2diff = player2.x - player1.x;
                
                p1dy = p1dy + gravity;
                p2dy = p2dy + gravity;

                if (p1right) {//player 1 move right
                    player1.x = player1.x + p1speed;
                }
                if (p2right) {//player 2 move right
                    player2.x = player2.x + p2speed;
                }
                if (p1left) {//player 1 move left
                    player1.x = player1.x - p1speed;
                }
                if (p2left) {//player 2 move left
                    player2.x = player2.x - p2speed;
                }
                
                if (p2jump && !p2inAir){
                    p2dy = -25;
                    p2inAir = true;
                }
                
                player2.y += p2dy;
                
                if(player2.y > groundrb){
            
                    player2.y = groundrb;
                    p2dy = 0; 
                    p2inAir = false; 
                }
                
                if (p1jump && !p1inAir){
                    p1dy = -25;
                    p1inAir = true;
                }
                
                player1.y += p1dy;
                
                if(player1.y > groundr){
            
                    player1.y = groundr;
                    p1dy = 0; 
                    p1inAir = false; 
                }
                
                
                if (player1.x <= 0){
                    player1.x = 0;
                }
                
                if (player2.x <= 0){
                    player2.x = 0;
                }
                if (player1.intersects(pup1) && powerupshow == true){
                    powerupgetp1 = true;
                    powerupshow = false;
                    p1speed = 7;
                }
                if (player2.intersects(pup2) && powerupshow == true){
                    powerupgetp2 = true;
                    powerupshow = false;
                    p2speed = 7;
                }
                if (p1speed == 7 && player1.x >= 2000){
                    p1speed = 5;
                    powerupgetp1 = false;
                    powerupshow = true;
                }
                if (p2speed == 7 && player2.x >= 2000){
                    p2speed = 5;
                    powerupgetp2 = false;
                    powerupshow = true;
                }
                

                if (player1.x < WIDTH / 2) {
                    camx = 0;
                } else {
                    camx = player1.x - WIDTH / 2;
                }

                if (player2.x < WIDTH / 2) {
                    camx3 = 0;
                } else {
                    camx3 = player2.x - WIDTH / 2;
                }
                if (screen == 13){
                    if(pup1.y <= 530){
                        pupspeed *= -1;
                    } 
                    if (pup1.y >= 550){
                        pupspeed *= -1;
                    }
                    pup1.y = pup1.y + pupspeed;
                }
                 if (screen == 13){
                    if(pup2.y <= 230){  
                        pupspeed2 *= -1;
                    } 
                    if (pup2.y >= 250){
                        pupspeed2 *= -1;
                    }
                    pup2.y = pup2.y + pupspeed;
                }
                if(p1diff >= 415 || player1.x == 2800 ){
                    p1speed = 5;
                    p2speed = 5;
                    player1.x = 20;
                    player1.y = 560;
                    player2.x = 20;
                    player2.y = 260;
                    screen = 15;
                }
                if(p2diff >= 415 || player2.x == 2800){
                    p1speed = 5;
                    p2speed = 5;
                    player1.x = 20;
                    player1.y = 560;
                    player2.x = 20;
                    player2.y = 260;
                    screen = 16;
                }
                
                
                
                
                
                
                
                
                //collisions for player 1
                if (screen == 13) {//collisions for race level 1
                    for (int i = 0; i < recrace1.length; i++) { // this for loop handles all collisions with the rectangles in level 1
                        if (player1.intersects(recrace1[i])) {
                            handleCollision(player1, recrace1[i]);
                        }
                    }
                    if (player1.intersects(base1)) {// this if statment handles a collision with an extra block in level 4
                        handleCollision(player1, base1);

                    }
                    if (player1.intersects(base2)) {// this if statment handles a collision with an extra block in level 4
                        handleCollision(player1, base2);

                    }
                    if (player1.intersects(r1p1collide)) {// this if statment handles a collision with an extra block in level 4
                        handleCollision(player1, r1p1collide);

                    }
                    
                }
                // collisions for player 2
                if (screen == 13) {//collisions for race level 1
                    for (int i = 0; i < recrace1b.length; i++) { // this for loop handles all collisions with the rectangles in level 1
                        if (player2.intersects(recrace1b[i])) {
                            
                            handleCollision2(player2, recrace1b[i]);
                        }
                    }
                    if (player2.intersects(base1)) {// this if statment handles a collision with an extra block in level 4
                        handleCollision2(player2, base1);

                    }
                    if (player2.intersects(base2)) {// this if statment handles a collision with an extra block in level 4
                        handleCollision2(player2, base2);

                    }
                    if (player2.intersects(r1p2collide)) {// this if statment handles a collision with an extra block in level 4
                        handleCollision2(player2, r1p2collide);

                    }
                }
                
                
                if (screen == 14) {//collisions for race level 2
                    for (int i = 0; i < recrace2.length; i++) { // this for loop handles all collisions with the rectangles in level 1
                        if (player1.intersects(recrace2[i])) {
                            handleCollision(player1, recrace2[i]);
                        }
                    }
                    if (player1.intersects(base1)) {// this if statment handles a collision with an extra block in level 4
                        handleCollision(player1, base1);

                    }
                    if (player1.intersects(base2)) {// this if statment handles a collision with an extra block in level 4
                        handleCollision(player1, base2);

                    }
                    if (player1.intersects(r2p1collide)) {// this if statment handles a collision with an extra block in level 4
                        handleCollision(player1, r2p1collide);

                    }
                    
                }
                // collisions for player 2
                if (screen == 14) {//collisions for race level 2 
                    for (int i = 0; i < recrace2b.length; i++) { // this for loop handles all collisions with the rectangles in level 1
                        if (player2.intersects(recrace2b[i])) {
                            
                            handleCollision2(player2, recrace2b[i]);
                        }
                    }
                    if (player2.intersects(base1)) {// this if statment handles a collision with an extra block in level 4
                        handleCollision2(player2, base1);

                    }
                    if (player2.intersects(base2)) {// this if statment handles a collision with an extra block in level 4
                        handleCollision2(player2, base2);

                    }
                    if (player2.intersects(r2p2collide)) {// this if statment handles a collision with an extra block in level 4
                        handleCollision2(player2, r2p2collide);

                    }
                }
                
                
                

            }



            // GAME LOGIC ENDS HERE 

            // update the drawing (calls paintComponent)
            repaint();



            // SLOWS DOWN THE GAME BASED ON THE FRAMERATE ABOVE
            // USING SOME SIMPLE MATH
            deltaTime = System.currentTimeMillis() - startTime;
            if (deltaTime > desiredTime) {
                //took too much time, don't wait
            } else {
                try {
                    Thread.sleep(desiredTime - deltaTime);
                } catch (Exception e) {
                };
            }

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creates a windows to show my game
        JFrame frame = new JFrame("My Game");

        // creates an instance of my game
        Terp game = new Terp();
        // sets the size of my game
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // adds the game to the window
        frame.add(game);

        // sets some options and size of the window automatically
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // shows the window to the user
        frame.setVisible(true);
        frame.addKeyListener(game);
        // starts my game loop
        game.run();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W) {
            p1jump = true;
        } else if (keyCode == KeyEvent.VK_A) {
            p1left = true;
        } else if (keyCode == KeyEvent.VK_D) {
            p1right = true;
        } else if (keyCode == KeyEvent.VK_SPACE) {
            p1sjump = true;
        } else if (keyCode == KeyEvent.VK_G) {
            change = true;
        } else if (keyCode == KeyEvent.VK_UP) {
            p2jump = true;
        } else if (keyCode == KeyEvent.VK_LEFT) {
            p2left = true;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            p2right = true;
        } else if (keyCode == KeyEvent.VK_H){
            hintchange = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W) {
            p1jump = false;
        } else if (keyCode == KeyEvent.VK_A) {
            p1left = false;
        } else if (keyCode == KeyEvent.VK_D) {
            p1right = false;
        } else if (keyCode == KeyEvent.VK_SPACE) {
            p1sjump = false;
        } else if (keyCode == KeyEvent.VK_UP) {
            p2jump = false;
        } else if (keyCode == KeyEvent.VK_LEFT) {
            p2left = false;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            p2right = false;
        }
    }

    void handleCollision(Rectangle player, Rectangle block) {
        Rectangle overlap = player.intersection(block);

        if (overlap.height > overlap.width) {
            if (player.x < block.x) {
                player.x -= overlap.width;
            } else if (player.x + player.width > block.x + block.width) {
                player.x += overlap.width;
            }
        } else {
            if (p1dy > 0) {
                player.y -= overlap.height;
                p1inAir = false;
            } else {
                player.y += overlap.height;
            }
            p1dy = 0;
        }
    }
    void handleCollision2(Rectangle player, Rectangle block) {
        Rectangle overlap = player.intersection(block);

        if (overlap.height > overlap.width) {
            if (player.x < block.x) {
                player.x -= overlap.width;
            } else if (player.x + player.width > block.x + block.width) {
                player.x += overlap.width;
            }
        } else {
            if (p2dy > 0) {
                player.y -= overlap.height;
                p2inAir = false;
            } else {
                player.y += overlap.height;
            }
            p2dy = 0;
        }
    }

    void drawSL1(Graphics g) { // this method draws race level 1
        g.drawImage(background, 0, 0, null);
        g.setColor(Color.GREEN);
        g.drawImage(teemo, player1.x - camx, player1.y, null);

        for (int i = 0; i < rectangles1.length; i++) {
            g.drawImage(b2, rectangles1[i].x - camx, rectangles1[i].y, null);
        }
        g.drawImage(finish, l1finish.x - camx, l1finish.y, null);
        g.fillRect(0 - camx, 583, 100, 100);
        g.drawString("You have " + slives + " lives left", 20, 20);

    }

    void drawSL2(Graphics g) { // this method draws race level 2
        g.drawImage(background, 0, 0, null);
        g.setColor(Color.GREEN);
        g.drawImage(teemo, player1.x - camx, player1.y, null);
        for (int i = 0; i < rectangles2.length; i++) {
            g.drawImage(b1, rectangles2[i].x - camx, rectangles2[i].y, null);
        }

        g.drawImage(finish, l2finish.x - camx, l2finish.y, null);
        g.fillRect(0 - camx, 583, 100, 100);
        g.drawString("You have " + slives + " lives left", 20, 20);
    }

    void drawSL3(Graphics g) { // this method draws race level 3
        g.drawImage(background, 0, 0, null);
        g.setColor(Color.GREEN);
        g.drawImage(teemo, player1.x - camx, player1.y, null);

        for (int i = 0; i < rectangles3.length; i++) {
            g.drawImage(b3, rectangles3[i].x - camx, rectangles3[i].y, null);
        }
        g.setColor(Color.GREEN);
        g.drawImage(b4, l4b5.x - camx, l4b5.y, null);
        g.fillRect(l4b9.x - camx, l4b9.y, l4b9.width, l4b9.height);
        g.drawImage(finish, l4finish.x - camx, l4finish.y, null);
        g.fillRect(0 - camx, 583, 100, 100);
        g.drawString("You have " + slives + " lives left", 20, 20);
    }

    void drawSL4(Graphics g) { // this method draws race level 4
        g.drawImage(background, 0, 0, null);
        g.setColor(Color.GREEN);
        g.drawImage(teemo, player1.x - camx, player1.y - camy, null);

        for (int i = 0; i < rectangles4.length; i++) {
            g.drawImage(b1, rectangles4[i].x - camx, rectangles4[i].y - camy, null);
        }
        g.fillRect(l3b0.x - camx, l3b0.y - camy, l3b0.width, l3b0.height);
        g.fillRect(l3b9.x - camx, l3b9.y - camy, l3b9.width, l3b9.height);
        g.fillRect(0 - camx, 583 - camy, 100, 100);
        g.drawString("You have " + slives + " lives left", 20, 20);
    }

    void drawSL5(Graphics g) { // this method draws race level 5
        g.drawImage(background, 0, 0, null);
        g.setColor(Color.GREEN);
        g.drawImage(teemo, player1.x - camx, player1.y, null);

        for (int i = 0; i < rectangles5.length; i++) {
            g.drawImage(b6, rectangles5[i].x - camx, rectangles5[i].y, null);
        }
        g.fillRect(l5b3.x - camx, l5b3.y, l5b3.width, l5b3.height);
        g.fillRect(l5b9.x - camx, l5b9.y, l5b9.width, l5b9.height);
        g.fillRect(0 - camx, 583, 100, 100);
        g.drawString("You have " + slives + " lives left", 20, 20);
    }

    void drawRL1(Graphics g) {// this method draws race level 1
        
        g.drawImage(rbackground, 0 - camx, 280, null);
        g.drawImage(rbackground1, 0 - camx3, -310, null);
        g.setColor(Color.GREEN);
        g.fillRect(base1.x, base1.y, base1.width, base1.height);
        g.fillRect(base2.x, base2.y, base2.width, base2.height);
        
        for (int i = 0; i < recrace1.length; i++) {
            g.fillRect(recrace1b[i].x - camx3, recrace1b[i].y, recrace1b[i].width, recrace1b[i].height);
        }
        for (int i = 0; i < recrace1.length; i++) {
            g.fillRect(recrace1[i].x - camx, recrace1[i].y, recrace1[i].width, recrace1[i].height);
        }
        
        g.drawImage(teemo, player1.x -camx,player1.y, null);//main player 1
        g.drawImage(gnar, player2.x -camx3,player2.y, null);//main player 2
        
        g.drawImage(teemo, player1.x - camx3, player1.y - 300, null);//player 2 ghost
        g.drawImage(gnar, player2.x - camx, player2.y + 300, null);//player 2 ghost     
        //player 1 is teemo
        //player 2 is gnar
        //player 1 uses w,a,d
        //player 3 uses up,left,right
        g.setColor(Color.GREEN);
        g.setColor(Color.RED);
        g.drawImage(finish, 2800 - camx, 490, null);
        g.drawImage(finish, 2800 - camx3, 190, null);
        if (powerupshow == true){
            g.drawImage(powerup, pup1.x - camx, pup1.y, null);
        }
         if (powerupshow == true){
            g.drawImage(powerup, pup2.x - camx3, pup2.y, null);
        }
        
    }
    void drawRL2(Graphics g) {// this method draws race level 1
        
        g.drawImage(rbackground, 0 - camx, 280, null);
        g.drawImage(rbackground1, 0 - camx3, -310, null);
        g.setColor(Color.GREEN);
        g.fillRect(base1.x, base1.y, base1.width, base1.height);
        g.fillRect(base2.x, base2.y, base2.width, base2.height);
        
        for (int i = 0; i < recrace1.length; i++) {
            g.fillRect(recrace2b[i].x - camx3, recrace2b[i].y, recrace2b[i].width, recrace2b[i].height);
        }
        for (int i = 0; i < recrace1.length; i++) {
            g.fillRect(recrace2[i].x - camx, recrace2[i].y, recrace2[i].width, recrace2[i].height);
        }
        
        g.drawImage(teemo, player1.x -camx,player1.y, null);//main player 1
        g.drawImage(gnar, player2.x -camx3,player2.y, null);//main player 2
        
        g.drawImage(teemo, player1.x - camx3, player1.y - 300, null);//player 2 ghost
        g.drawImage(gnar, player2.x - camx, player2.y + 300, null);//player 2 ghost     
        //player 1 is teemo
        //player 2 is gnar
        //player 1 uses w,a,d
        //player 3 uses up,left,right
        g.setColor(Color.GREEN);
        g.setColor(Color.RED);
        g.drawImage(finish, 2800 - camx, 490, null);
        g.drawImage(finish, 2800 - camx3, 190, null);
        
    }
}