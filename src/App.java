import processing.core.*;

public class App extends PApplet {
    double score = 0; // how many cookies clicked overall
    double cookiesPerClick = 1; // how many cookies are gained per click
    int row = 0; // used to make the upgrades grid
    double cookiesGainedPerSec = 0; // how many cookies are gained per second
    int bgColor = color(210, 210, 210); // initial background color

    // values for all of the costs of upgrades
    int costbox1 = 75;
    int costbox2 = 200;
    int costbox3 = 600;
    int costbox4 = 900;
    int costbox5 = 1200;
    int costbox6 = 2000;
    int costbox7 = 3500;
    int costbox8 = 5000;
    int costbox9 = 9000;
    int costbox10 = 13000;

    int scene = 0; // tracks what "scene" / page the game should be on
    int lastCookie = 0; // tracks the cookies most recently used

    // all of the photos i use in my code
    PImage cookieclick;
    PImage house;
    PImage cookie2;
    PImage cookie3;
    PImage cookie4;
    PImage currentImage;
    PImage angryCookie;

    boolean isAngryCookieVisible = false; // shows if the angry cookie is visable or not
    float angryCookieX, angryCookieY; // stores the x and y coordinates of the cookie
    int angryCookieDurationInbetween = 5000; // duration of time each angry cookie lasts for
    int angryCookieDuration = 15000; // duration of each break between angry cookies (15 seconds)
    int lastPhaseChangeTime = 0; // tracks the last time the phase changed

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void settings() {
        size(800, 600);
    }

    public void setup() {
        // loading in all of my images i use throughout the code
        cookieclick = loadImage("cookieclick.png");
        house = loadImage("house.png");
        cookie2 = loadImage("cookie 2.png");
        cookie3 = loadImage("cookie 3.png");
        cookie4 = loadImage("cookie 4.png");
        angryCookie = loadImage("angry cookie.png");
        currentImage = cookieclick;

    }

    public void draw() {
        if (scene == 0) {
            background(123, 63, 0); // sets a background color for the loading page
            textSize(32);
            fill(193, 154, 107); // text color
            text("Cookie Clicker Game", 260, 200); // title of game

            // draw the "play" button
            fill(193, 154, 107); // color of the button
            rect(340, 300, 120, 50); // position and size of the button

            fill(123, 63, 0); // color of the button text
            textSize(20);
            text("Play", 380, 330); // text for the button

            fill(193, 154, 107);
            rect(340, 400, 120, 50); // instructions button

            fill(123, 63, 0);
            textSize(20);
            text("Instructions", 350, 430);
        } else if (scene == 2) { // changes to instructions scene
            background(123, 63, 0);
            image(house, 20, 550, 40, 40);
            textSize(32);
            fill(193, 154, 107);
            text("Instructions: ", 290, 230);
            text("Click the cookies and work on \n upgrading them by using the \n upgrades bar on the right. \n Click on all of the angry cookies \n right away. Watch out!!",
                    200, 300);
        } else if (scene == 1) { //changes to game scene
            background(bgColor); 
            image(currentImage, 175, 175, 200, 200);
            image(house, 20, 550, 40, 40);
            textSize(20);
            fill(255);
            score = Math.round(score * 100.0) / 100.0; //used chat gpt to figure out how to round the score
            text("Cookies clicked: " + score, 10, 30);
            text(cookiesPerClick + " cookies per click", 10, 60);
            text(cookiesGainedPerSec + " cookies gained per second", 10, 90);

            int currentTime = millis();

            // handles angry cookie spawning and disappearance (used chat gpt to figure out
            // how to spawn the angry cookie)
            if (!isAngryCookieVisible && (currentTime - lastPhaseChangeTime >= angryCookieDuration)) {
                // shows angry cookie
                isAngryCookieVisible = true;
                angryCookieX = random(100, 500); // random x position
                angryCookieY = random(100, 500); // random y position
                lastPhaseChangeTime = currentTime;
            } else if (isAngryCookieVisible && (currentTime - lastPhaseChangeTime >= angryCookieDurationInbetween)) {
                // hides angry cookie after its visible duration
                isAngryCookieVisible = false;
                lastPhaseChangeTime = currentTime;
            }

            // displays angry cookie if it's visible
            if (isAngryCookieVisible) {
                image(angryCookie, angryCookieX, angryCookieY, 60, 60);
            }

            stroke(255);
            line(550, 0, 550, 600);
            fill(255);
            text("Upgrades", 625, 30);

            grid(); // method for creating the white upgrade "grid"
            fill(0);
            text();

            if (frameCount % 60 == 0) {
                score += cookiesGainedPerSec; // increase score every second
            }
        }

    }

    public void grid() { // creates the grid used for the upgrades
        for (int row = 0; row < 5; row++) {
            fill(255);
            square(575, 60 + row * 105, 95);
            square(680, 60 + row * 105, 95);

        }
        checkForGreen(); // checks to see if upgrade is purchasable and if yes then will turn green

    }

    public void mousePressed() {
        if (scene == 0) {
            if (mouseX >= 340 && mouseX <= 460 && mouseY >= 300 && mouseY <= 350) {
                scene = 1; // switch to the game scene
            }

            if (mouseX >= 340 && mouseX <= 460 && mouseY >= 400 && mouseY <= 450) {
                scene = 2; // switch to instructions
            }

        } else if (scene == 1) {
            if (mouseX >= 20 && mouseX <= 60 && mouseY >= 550 && mouseY <= 590) {
                scene = 0; // switch to home screen
            }
            float distance = dist(mouseX, mouseY, 275, 275);

            if (distance <= 100) {
                score += cookiesPerClick; // if clicked on cookie the score will increase

            }

            upgradePressed(mouseX, mouseY);

        } else if (scene == 2) {
            if (mouseX >= 20 && mouseX <= 60 && mouseY >= 550 && mouseY <= 590) {
                scene = 0; // switch to home screen
            }

        }

        if (isAngryCookieVisible && mouseX >= angryCookieX && mouseX <= angryCookieX + 60 && mouseY >= angryCookieY
                && mouseY <= angryCookieY + 60) { // if click on angry cookie then score increases
            score *= 1.5;
            score = Math.round(score * 100.0) / 100.0;
            isAngryCookieVisible = false; // changes the angry cookie back to be not visable
        }

        if (isAngryCookieVisible && (mouseX < angryCookieX || mouseX > angryCookieX + 60 || mouseY < angryCookieY
                || mouseY > angryCookieY + 60)) {
            score = score / 2; // if dont click on angry cookie and click elsewhere while its visable your
                               // score halfs
            score = Math.round(score * 100.0) / 100.0; // rounds the values
            isAngryCookieVisible = false; // changes the angry cookie back to be not visable
        }
    }

    public void checkForGreen() { // uses the simplify green method to see it you can buy the upgrade and if yes
                                  // then will turn green
        simplifyGreen(575, 60, costbox1);
        simplifyGreen(680, 60, costbox2);
        simplifyGreen(575, 165, costbox3);
        simplifyGreen(680, 165, costbox4);
        simplifyGreen(575, 270, costbox5);
        simplifyGreen(680, 270, costbox6);
        simplifyGreen(575, 375, costbox7);
        simplifyGreen(680, 375, costbox8);
        simplifyGreen(575, 480, costbox9);
        simplifyGreen(680, 480, costbox10);
    }

    public void simplifyGreen(int x, int y, int cost) { // will take a x and y value and a costbox and check if its
                                                        // purchasable
        if (score >= cost) {
            fill(140, 255, 95); // will fill green if purchasable
        } else {
            fill(255);
        }
        square(x, y, 95);
    }

    // public void keyPressed() {
    // if (key == 'p') {
    // score += 1000;
    // }

    // }

    public void text() { // method for all of the text I am using in the grid
        textSize(12);
        text("Cookies increase \n by 0.5 cookie \n per click \n Cost: " + costbox1, 575, 80);
        text("Increase cookies \n gained per  \n second by 0.5 \n Cost: " + costbox2, 680, 80);
        text("Cookies increase \n by 2 cookies \nper click \nCost: " + costbox3, 575, 185);
        text("Increase cookies \n gained per \n second by 1 \n Cost: " + costbox4, 680, 185);
        text("Cookies increase \n by 4 cookies \n per click \n Cost: " + costbox5, 575, 290);
        text("Increase cookies \n gained per \n second by 3 \n Cost: " + costbox6, 680, 290);
        text("Cookies increase \n by 6 cookies \nper click \n Cost: " + costbox7, 575, 395);
        text("Increase cookies \n gained per \n second by 5 \n Cost: " + costbox8, 680, 395);
        text("Change \nbackground \n color \nCost: " + costbox9, 575, 500);
        text("Change cookie \n kind \n \n Cost: " + costbox10, 680, 500);

    }

    public void upgradePressed(int x, int y) { // if clicked upgrade it will increase the price and increase the cookies
                                               // gained

        if (checkBoxDimensions(score, costbox1, 575, 60)) {
            cookiesPerClick += 0.5;
            score -= costbox1; // makes the score decrease by the amount the upgrade was bought for
            score = Math.round(score * 100.0) / 100.0;
            costbox1 *= 1.3;

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
            cookiesGainedPerSec += 2;
            score -= costbox4;
            costbox4 *= 1.5;
        }

        if (checkBoxDimensions(score, costbox5, 575, 270)) {
            cookiesPerClick += 4;
            score -= costbox5;
            costbox5 *= 1.5;
        }
        if (checkBoxDimensions(score, costbox6, 680, 270)) {
            cookiesGainedPerSec += 3;
            score -= costbox6;
            costbox6 *= 1.5;
        }
        if (checkBoxDimensions(score, costbox7, 575, 375)) {
            cookiesPerClick += 6;
            score -= costbox7;
            costbox7 *= 1.5;
        }

        if (checkBoxDimensions(score, costbox8, 680, 375)) {
            cookiesGainedPerSec += 5;
            score -= costbox8;
            costbox8 *= 1.5;
        }

        if (checkBoxDimensions(score, costbox9, 575, 480)) {
            bgColor = color(random(0, 256), random(0, 256), random(0, 256));
            score -= costbox9;
            costbox9 *= 1.5;
        }

        if (checkBoxDimensions(score, costbox10, 680, 480)) { // uses the method below
            randomCookie();
            score -= costbox10;
            costbox10 *= 1.5;
        }
    }

    public boolean checkBoxDimensions(double s, int c, int xPos, int yPos) { // if score is greater than cost and the
                                                                             // mouse is within the squate then you can
                                                                             // buy the upgrade
        if (s >= c && mouseX >= xPos && mouseX <= xPos + 95 && mouseY >= yPos && mouseY <= yPos + 95) {
            return true;
        } else {
            return false;
        }
    }

    public void randomCookie() { // selects a random cookie for the random cookie upgrade
        int choice = (int) random(4); // gives a random number that will be transfered to a different coookie image

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
