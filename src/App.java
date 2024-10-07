import processing.core.*;

public class App extends PApplet {
    double score = 0;
    double cookiesPerClick = 1;
    PImage cookieclick;
    int row = 0;
    double cookiesGainedPerSec = 0;
    int bgColor = color(210, 210, 210);
    int costbox1 = 75;
    int costbox2 = 200;
    int costbox3 = 600;
    int costbox4 = 900;
    int costbox5 = 1200;
    int costbox6 = 2000;
    int costbox7 = 3500;
    int costbox9 = 10000;
    int costbox10 = 20000;
    int scene = 0;
    int lastCookie = 0;
    PImage house;
    PImage cookie2;
    PImage cookie3;
    PImage cookie4;
    PImage currentImage;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void settings() {
        size(800, 600);
    }

    public void setup() {
        cookieclick = loadImage("cookieclick.png");
        house = loadImage("house.png");
        cookie2 = loadImage("cookie 2.png");
        cookie3 = loadImage("cookie 3.png");
        cookie4 = loadImage("cookie 4.png");
        currentImage = cookieclick;
    }

    public void draw() {
        if (scene == 0) {
            background(123, 63, 0); // sets a background color for the loading page
            textSize(32);
            fill(193, 154, 107);
            text("Cookie Clicker Game", 260, 200); // title of game

            // draw the "play" button
            fill(193, 154, 107);
            rect(340, 300, 120, 50); // position and size of the button

            fill(123, 63, 0);
            textSize(20);
            text("Play", 380, 330); // text for the button

            fill(193, 154, 107);
            rect(340, 400, 120, 50);

            fill(123, 63, 0);
            textSize(20);
            text("Instructions", 350, 430);

        } else if (scene == 2) {
            background(123, 63, 0);
            image(house, 20, 550, 40, 40);
            textSize(32);
            fill(193, 154, 107);
            text("Instructions: ", 290, 230);
            text("Click the cookies and work on \n upgrading them by using the \n upgrades bar on the right.", 200,
                    300);

        } else if (scene == 1) {
            background(bgColor);
            image(currentImage, 175, 175, 200, 200);
            image(house, 20, 550, 40, 40);
            textSize(20);
            fill(255);
            text("Cookies clicked: " + score, 10, 30);
            text(cookiesPerClick + " cookies per click", 10, 60);
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

            if (mouseX >= 340 && mouseX <= 460 && mouseY >= 400 && mouseY <= 450) {
                scene = 2;
            }

        } else if (scene == 1) {
            if (mouseX >= 20 && mouseX <= 60 && mouseY >= 550 && mouseY <= 590) {
                scene = 0;
            }
            float distance = dist(mouseX, mouseY, 275, 275);

            if (distance <= 100) {
                score += cookiesPerClick;

            }

            upgradePressed(mouseX, mouseY);
        } else if (scene == 2) {
            if (mouseX >= 20 && mouseX <= 60 && mouseY >= 550 && mouseY <= 590) {
                scene = 0;
            }
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

        if (score >= costbox4) {
            fill(140, 255, 140);
        } else {
            fill(255);
        }
        square(680, 165, 95);

        if (score >= costbox5) {
            fill(140, 255, 95);
        } else {
            fill(255);
        }

        square(575, 270, 95);

        if (score >= costbox6) {
            fill(140, 255, 95);
        } else {
            fill(255);
        }

        square(680, 270, 95);

        if (score >= costbox7) {
            fill(140, 255, 95);
        } else {
            fill(255);
        }

        square(575, 375, 95);

        if (score >= costbox9) {
            fill(140, 255, 140);
        } else {
            fill(255);
        }
        square(575, 480, 95);

        if (score >= costbox10) {
            fill(140, 255, 140);
        } else {
            fill(255);
        }

        square(680, 480, 95);
    }

    public void keyPressed() {
        if (key == 'p') {
            score += 1000;
        }

    }

    public void text() { // method for all of the text I am using in the grid
        textSize(12);
        text("Cookies increase \n by 1 cookie \n per click \n Cost: " + costbox1, 575, 80);
        text("Increase cookies \n gained per  \n second by 0.5 \n Cost: " + costbox2, 680, 80);
        text("Cookies increase \n by 3 cookies \nper click \nCost: " + costbox3, 575, 185);
        text("Increase cookies \n gained per \n second by 1 \n Cost: " + costbox4, 680, 185);
        text("Cookies increase \n by 5 cookies \n per click \n Cost: " + costbox5, 575, 290);
        text("Increase cookies \n gained per \n second by 3 \n Cost: " + costbox6, 680, 290);
        text("Cookies increase \n by 7 cookies \nper click \n Cost: " + costbox7, 575, 395);

        text("Change \nbackground \n color \nCost: " + costbox9, 575, 500);
        text("Change cookie \n kind \n \n Cost: " + costbox10, 680, 500);

    }

    public void upgradePressed(int x, int y) {

        if (checkBoxDimensions(score, costbox1, 575, 60)) {
            cookiesPerClick += 1;
            score -= costbox1; // makes the score decrease by the amount the upgrade was bought for
            costbox1 *= 1.2; // makes the cost go up by 100 each time its purchased

        }
        if (checkBoxDimensions(score, costbox2, 680, 60)) {
            cookiesGainedPerSec += 0.5;
            score -= costbox2;
            costbox2 *= 1.3;
        }

        if (checkBoxDimensions(score, costbox3, 575, 165)) {
            cookiesPerClick += 3;
            score -= costbox3;
            costbox3 *= 1.4;
        }

        if (checkBoxDimensions(score, costbox4, 680, 165)) {
            cookiesGainedPerSec += 1;
            score -= costbox4;
            costbox4 *= 1.5;
        }

        if (checkBoxDimensions(score, costbox5, 575, 270)) {
            cookiesPerClick += 5;
            score -= costbox5;
            costbox5 *= 1.5;
        }
        if (checkBoxDimensions(score, costbox6, 680, 270)) {
            cookiesGainedPerSec += 3;
            score -= costbox6;
            costbox6 *= 1.5;
        }
        if (checkBoxDimensions(score, costbox7, 575, 375)) {
            cookiesPerClick += 7;
            score -= costbox7;
            costbox7 *= 1.5;
        }

        if (checkBoxDimensions(score, costbox9, 575, 480)) {
            bgColor = color(random(0, 256), random(0, 256), random(0, 256));
            score -= costbox9;
            costbox9 += 600;
        }

        if (checkBoxDimensions(score, costbox10, 775, 480)) {
            randomCookie();
            score -= costbox10;
            costbox10 += 1000;
        }
    }


    public boolean checkBoxDimensions(double s, int c, int xPos, int yPos) {
        if (s >= c && mouseX >= xPos && mouseY >= yPos && mouseY <= yPos + 95) {
            return true;
        } else {
            return false;
        }
    }

    public void randomCookie() {
        int choice = (int) random(4);

        if (choice == lastCookie) {
            choice = (int) random(4);
        }
        lastCookie = choice;

        if (choice == 0) {
            currentImage = cookieclick;
        } else if (choice == 1) {
            currentImage = cookie2;
        } else if (choice == 2) {
            currentImage = cookie3;
        } else if (choice == 3) {
            currentImage = cookie4;
        }
    }
}
