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

    public void grid() {
        for (int row = 0; row < 5; row++) {
            fill(255);
            square(575, 60 + row * 105, 95);
            square(680, 60 + row * 105, 95);

        }

        if (score >= costbox1) {
            fill(140, 255, 140); // green when the upgrade is affordable
        } else {
            fill(255); // default white (box color)
        }
        square(575, 60, 95);

        if (score>= costbox2){
            fill(140, 255, 140);
        } else {
            fill(255);
        }
        square (680,60,95);

        

    }

    
    public void mousePressed() {
        float distance = dist(mouseX, mouseY, 275, 275);

        if (distance <= 100) {
            score += cookiesPerClick;
        }

        upgradePressed(mouseX, mouseY);

    }

    public void keyPressed() {
        if (key == 'p') {
            score += 1000;
        }
    }

   public void text () { // method for all of the text I am using in the grid
    textSize(12);
    text("Cookies increase \n by 3 cookies \n per click \n Cost: " + costbox1 , 575, 80);
    text("Increase cookies \n gained per  \n second by 2  \n Cost: " + costbox2, 680, 80);

    text("Change \nbackground \n color \nCost: 10000 cookies", 575, 395);
   }

    public void upgradePressed(int x, int y) {
        
        if (score >= costbox1 && x >= 575 && x <= 670 && y >= 60 && y <= 155 ) {
            fill (230);
            cookiesPerClick += 3;
            score -= costbox1; //makes the score decrease by the amount the upgrade was bought for
            costbox1+=100; // makes the cost go up by 100 each time its purchased
            
        }
        if (score >= costbox2 && x >= 680 && x <= 775 && y >= 60 && y <= 155 ) {
            cookiesGainedPerSec += 2;
            score -= costbox2;
            costbox2+=200;
        }

        if (score >= 10000 && x >= 575 && x <= 670 && y >= 395 && y <= 500) {
            bgColor = color(random(0, 256), random(0, 256), random(0, 256));
            score -= 10000;
        }
    }
}
