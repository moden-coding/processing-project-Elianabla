import processing.core.*;

public class App extends PApplet {
    double score = 0;
    int cookiesPerClick = 1;
    PImage cookieclick;
    int row = 0;
    double cookiesGainedPerSec = 0.5;
    int bgColor = color(210, 210, 210);
    int costbox1 = 100;
    int costbox2 = 300;
    int costbox3 = 600;
    int costbox7 = 10000;
    int scene = 0;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void settings() {
        size(800, 600);
    }

    public void setup() {
        cookieclick = loadImage("cookieclick.png");
    }

    public void draw() {
        if (scene == 0) {
            background(123,63,0); // sets a background color for the loading page
            textSize(32);
            fill(193, 154, 107);
            text("Cookie Clicker Game", 260 ,200); // title of game 

            // draw the "play" button
            fill(193, 154, 107);
            rect(340, 300, 120, 50); // position and size of the button

            fill(123,63,0);
            textSize(20);
            text("Play", 375, 330); // text for the button

            fill(193, 154, 107);
            rect(340, 400, 120, 50);

            fill(123,63,0);
            textSize(20);
            text("Instructions", 350, 430);

        } else if (scene  == 2) {
            background(123, 63, 0);
            textSize(32);
            fill (193, 154 , 107);
            text("Instructions: ", 260, 200);

        } else if (scene == 1) {
            background(bgColor);
            image(cookieclick, 175, 175, 200, 200);
            textSize(20);
            fill(255);
            text("Cookies clicked: " + score, 10, 30);
            text(cookiesPerClick + " cookie per click", 10, 60);
            text(cookiesGainedPerSec + " cookies gained per second", 10, 90);

            stroke(255);
            line(550, 0, 550, 600);
            fill(255);
            text("Upgrades", 625, 30);

            grid();
            fill(0);
            text();

            if (frameCount % 60 == 0) {
                score = score * 1.0 + cookiesGainedPerSec;

            }
        }

    }

    public void grid() {
        for (int row = 0; row < 5; row++) {
            fill(255);
            square(575, 60 + row * 105, 95);
            square(680, 60 + row * 105, 95);

        }
        checkForGreen();

    }

    public void mousePressed() {
        if (scene == 0) {
            if (mouseX >= 340 && mouseX <= 460 && mouseY >= 300 && mouseY <= 350) {
                scene = 1; // Switch to the game scene
            }

            if (mouseX>= 340 && mouseX <= 460 && mouseY >= 400 && mouseY <= 450) {
                scene = 2;
            }
            
        } else if (scene == 1) {
            float distance = dist(mouseX, mouseY, 275, 275);
    
            if (distance <= 100) {
                score += cookiesPerClick;
            }
    
            upgradePressed(mouseX, mouseY);
        }
    }

    public void checkForGreen() {
        if (score >= costbox1) {
            fill(140, 255, 140); // green when the upgrade is affordable
        } else {
            fill(255); // default white (box color)
        }
        square(575, 60, 95);

        if (score >= costbox2) {
            fill(140, 255, 140);
        } else {
            fill(255);
        }
        square(680, 60, 95);

        if (score >= costbox3) {
            fill(140, 255, 140);
        } else {
            fill(255);
        }
        square(575, 165, 95);

        if (score >= costbox7) {
            fill(140, 255, 140);
        } else {
            fill(255);
        }
        square(575, 375, 95);
    }

    public void keyPressed() {
        if (key == 'p') {
            score += 1000;
        }
    }

    public void text() { // method for all of the text I am using in the grid
        textSize(12);
        text("Cookies increase \n by 3 cookies \n per click \n Cost: " + costbox1, 575, 80);
        text("Increase cookies \n gained per  \n second by 2  \n Cost: " + costbox2, 680, 80);
        text("Cookies increase \n by 5 cookies \nper click \nCost: " + costbox3, 575, 185);
        text("Change \nbackground \n color \nCost: " + costbox7, 575, 395);
    }

    public void upgradePressed(int x, int y) {

        if (score >= costbox1 && x >= 575 && x <= 670 && y >= 60 && y <= 155) {
            fill(230);
            cookiesPerClick += 3;
            score -= costbox1; // makes the score decrease by the amount the upgrade was bought for
            costbox1 += 100; // makes the cost go up by 100 each time its purchased

        }
        if (score >= costbox2 && x >= 680 && x <= 775 && y >= 60 && y <= 155) {
            cookiesGainedPerSec += 2;
            score -= costbox2;
            costbox2 += 200;
        }

        if (score >= costbox3 && x >= 575 && x <= 670 && y >= 165 && y <= 260) {
            cookiesPerClick += 5;
            score -= costbox3;
            costbox3 += 300;
        }

        if (score >= costbox7 && x >= 575 && x <= 670 && y >= 395 && y <= 500) {
            bgColor = color(random(0, 256), random(0, 256), random(0, 256));
            score -= costbox7;
            costbox7 += 600;
        }
    }
}
