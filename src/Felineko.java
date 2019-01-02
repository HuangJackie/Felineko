/*
 * Used the Processing Library and the Processing Sound Library.
 *
 * Music created using https://www.beepbox.co/#6n31s0kbl00e05t7m0a7g0fj7i0r1o3210T0w1f3d1c4h0v0T0w1f1d1c3h0v2T0w2f1d1c0h0v3T2w1d1v0b4zgQ4x4h4h4h4h4h4h4h4h4z8N4h4h4h4x804h4h4h4p229IMMpFBcu6p_iCu3jmCBwFxMfR4QPRnknRdnmZgjZplcFEZZVRbCDTw9nPmQPPDeYPOg0F5gFDM5cu1V0swuhhpxNjdjCgYidd7i3g1daoUZ0q0Pjng6AcQFEYz19Fym6wOOt551B0
 * BeepBox website.
 */

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.sound.SoundFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * A platformer game.
 */
public class Felineko extends PApplet implements Observer {

    /**
     * List of keys that are pressed.
     */
    private boolean[] keys = new boolean[7];

    /**
     * Background of the menu with a saved game.
     */
    private PImage menuBackgroundHasSave;

    /**
     * Background of the menu without a saved file.
     */
    private PImage menuBackground;

    /**
     * Background of a winning game.
     */
    private PImage menuWin;

    /**
     * Background of a loosing game.
     */
    private PImage menuLose;

    /**
     * Background of the instructions.
     */
    private PImage menuInstructions;

    /**
     * Background of a paused game.
     */
    private PImage menuPause;

    /**
     * Images for the health bar.
     */
    private PImage[] healthBar = new PImage[11];

    /**
     * Images for the number of fish coins collected.
     */
    private PImage[] coinBar = new PImage[6];

    /**
     * Image of the pause button.
     */
    private PImage pause;

    /**
     * Identifies which gameScreen is being shown.
     */
    private int gameScreen = 0;

    /**
     * The tileMap of the Game.
     */
    private Map map;

    /**
     * List of Active Enemies.
     */
    private ArrayList<Enemy> activeEnemies;

    /**
     * Controller class to manage the Map Class.
     */
    private MapController mapController = new MapController(this);

    /**
     * Controller class to manage the Player Class.
     */
    private PlayerController playerController;

    /**
     * Controller class to manage the Enemy Class.
     */
    private EnemyController enemyController;

    /**
     * Factory Class to initialize the Enemies.
     */
    private EnemyFactory enemyFactory = new EnemyFactory();

    /**
     * The Player object the user controls.
     */
    private Player hero;

    /**
     * Amount of pixels to shift the screen by when moving.
     */
    private PVector translation = new PVector(0, 0);

    /**
     * The previous player's position.
     */
    private PVector prevPlayerPos = new PVector(100, 100);

    /**
     * Whether the game was won.
     */
    private boolean win;

    /**
     * Whether the saved game file is present.
     */
    private boolean fileExists;

    /**
     * Name of the saved state of the Player object.
     */
    private static final String HERO_FILE = "hero.ser";

    /**
     * Name of the saved state of the Map object.
     */
    private static final String MAP_FILE = "map.ser";

    /**
     * Name of the saved state of the Enemies object.
     */
    private static final String ENEMIES_FILE = "enemies.ser";

    /**
     * Name of the audio file.
     */
    private static final String AUDIO_FILE = "Felineko.wav";

    /**
     * Preset settings of the game.
     */
    public void settings() {
        size(600, 600);

        menuBackground = loadImage("MenuTwo.png");
        menuWin = loadImage("WINMENU.png");
        menuLose = loadImage("LOSEMENU.png");
        menuInstructions = loadImage("Instructions.png");
        menuPause = loadImage("PAUSEMENU.png");
        menuBackgroundHasSave = loadImage("MenuOne.png");

        menuBackground.resize(600, 0);
        menuInstructions.resize(600, 0);
        menuPause.resize(600, 0);
        menuBackgroundHasSave.resize(600, 0);

        for (int i = 0; i < healthBar.length; i++) {
            healthBar[i] = loadImage(Integer.toString(i * 10) + ".png");
        }
        for (int i = 0; i < coinBar.length; i++) {
            coinBar[i] = loadImage("COIN" + Integer.toString(i) + ".png");
        }
        pause = loadImage("PAUSE.png");

        String path = sketchPath(AUDIO_FILE);
        SoundFile file = new SoundFile(this, path);

        map = mapController.setUpMap();
        mapController.loadMap(map);
        file.loop();
    }

    /**
     * Reset the Entity and Tile Locations of the game.
     */
    private void resetGame() {
        map = mapController.setUpMap();
        mapController.loadMap(map);
        mapController.addObservers();

        activeEnemies = enemyFactory.instantiateEnemies();

        enemyController = new EnemyController(this, activeEnemies);
        enemyController.setUpSprite();
        hero = new Player(400, 1680, 60, 30, 5, "HERO", 20);
        playerController = new PlayerController(this, hero);
        playerController.setUpSprite();
        playerController.addObserver(this);
    }

    /**
     * Load the saved game from serializable files.
     */
    private void loadGame() {
        try {
            FileInputStream fileIn = new FileInputStream(HERO_FILE);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            hero = (Player) objectIn.readObject();
            objectIn.close();

            fileIn = new FileInputStream(MAP_FILE);
            objectIn = new ObjectInputStream(fileIn);

            map = (Map) objectIn.readObject();
            objectIn.close();

            fileIn = new FileInputStream(ENEMIES_FILE);
            objectIn = new ObjectInputStream(fileIn);

            activeEnemies = (ArrayList<Enemy>) objectIn.readObject();
            objectIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        mapController.loadMap(map);
        mapController.addObservers();

        enemyController = new EnemyController(this, activeEnemies);
        enemyController.setUpSprite();
        playerController = new PlayerController(this, hero);
        playerController.setUpSprite();
        playerController.addObserver(this);
    }

    /**
     * Erase the contents of the serializable files.
     */
    private void eraseGame() {
        try {
            FileOutputStream fileOut = new FileOutputStream(HERO_FILE);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.close();

            fileOut = new FileOutputStream(MAP_FILE);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.close();

            fileOut = new FileOutputStream(ENEMIES_FILE);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the screen.
     */
    public void draw() {
        switch (gameScreen) {
            case 0:
                checkExists();
                if (fileExists) {
                    background(menuBackgroundHasSave);
                    if (mouseX >= 220 && mouseX <= 377 && mouseY >= 319 && mouseY <= 353 && mousePressed) {
                        gameScreen = 1;
                        loadGame();
                    }
                } else {
                    background(menuBackground);
                }
                if (mouseX >= 207 && mouseX <= 392 && mouseY >= 380 && mouseY <= 416 && mousePressed) {
                    gameScreen = 1;
                    resetGame();
                } else if (mouseX >= 164 && mouseX <= 430 && mouseY >= 445 && mouseY <= 479 && mousePressed) {
                    gameScreen = 3;
                }
                break;
            case 1:
                translate(translation.x + 100, translation.y + 150);
                playerController.immunityUpdate(hero);
                enemyController.immunityUpdateAll();
                mapController.drawMap(map);
                image(healthBar[hero.getHP() / 10], -(translation.x + 100), -(translation.y + 150));
                image(coinBar[hero.getNumCoin()], -(translation.x - 440), -(translation.y + 150));
                image(pause, -(translation.x - 470), -(translation.y + 150));
                playerController.checkSpecialCollision(map);
                playerController.applyGravity(map, hero);

                if (keys[1]) {
                    playerController.moveLeft(map, hero);
                }

                if (keys[0]) {
                    playerController.moveRight(map, hero);
                }

                if (!(keys[1] || keys[0])) {
                    playerController.notMoving(map, hero);
                }

                if (keys[2]) {
                    playerController.playerJump(map);
                } else {
                    playerController.notJumping(map);
                }

                if (playerController.startedAttack()) {
                    playerController.drawAttack();
                } else {
                    playerController.drawEntity();
                }

                if (hero.getHP() == 0) {
                    gameScreen = 2;
                }

                enemyController.drawEntity();
                enemyController.updateLocation(map);
                enemyController.attackPlayer(hero);
                translation.x -= hero.getX() - prevPlayerPos.x;
                translation.y -= hero.getY() - prevPlayerPos.y;
                prevPlayerPos.x = hero.getX();
                prevPlayerPos.y = hero.getY();

                if ((mouseX >= 570 && mouseX <= 600 && mouseY >= 0 && mouseY <= 30 && mousePressed) || keys[6]) {
                    gameScreen = 4;
                    saveGame();
                }
                break;
            case 2:
                if (keys[5]) {
                    gameScreen = 0;
                    eraseGame();
                }
                if (win) {
                    background(menuWin);
                } else {
                    background(menuLose);
                }
                break;
            case 3:
                background(menuInstructions);
                if (mouseX >= 478 && mouseX <= 513 && mouseY >= 94 && mouseY <= 109 && mousePressed) {
                    gameScreen = 0;
                }
                break;
            case 4:
                background(menuPause);
                if (keys[5]) {
                    gameScreen = 0;
                }
                if (mouseX >= 486 && mouseX <= 537 && mouseY >= 146 && mouseY <= 167 && mousePressed) {
                    gameScreen = 1;
                }
                break;
        }
    }

    @Override
    public void exit() {
        if (gameScreen == 1) {
            saveGame();
        }
        super.exit();
    }

    /**
     * Check if a saved game exists.
     */
    private void checkExists() {
        try {

            FileInputStream fileIn = new FileInputStream(HERO_FILE);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            hero = (Player) objectIn.readObject();
            objectIn.close();
            fileExists = true;
        } catch (EOFException e) {
            fileExists = false;
        } catch (FileNotFoundException e) {
            eraseGame();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Save the current state of the game.
     */
    private void saveGame() {
        try {
            FileOutputStream fileOut = new FileOutputStream(HERO_FILE);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(hero);
            objectOut.close();

            fileOut = new FileOutputStream(MAP_FILE);
            objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(map);
            objectOut.close();

            fileOut = new FileOutputStream(ENEMIES_FILE);
            objectOut = new ObjectOutputStream(fileOut);

            objectOut.writeObject(activeEnemies);
            objectOut.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Recording Key presses.
     */
    public void keyPressed() {
        if (key == CODED) {
            if (keyCode == RIGHT) {
                keys[0] = true;
            } else if (keyCode == LEFT) {
                keys[1] = true;
            } else if (keyCode == UP) {
                keys[2] = true;
            } else if (keyCode == DOWN) {
                keys[3] = true;
            }
        } else if (key == 'z') {
            keys[4] = true;
        } else if (key == ' ') {
            keys[5] = true;
        } else if (key == 'p') {
            keys[6] = true;
        }
    }

    /**
     * Recording Key Releases.
     */
    public void keyReleased() {
        if (key == CODED) {
            if (keyCode == RIGHT) {
                keys[0] = false;
            } else if (keyCode == LEFT) {
                keys[1] = false;
            } else if (keyCode == UP) {
                hero.setJumpCounter(hero.getJumpCounter() + 1);
                hero.resetJumpSpeed();
                keys[2] = false;
            } else if (keyCode == DOWN) {
                keys[3] = false;
            }
        } else if (key == 'z') {
            playerController.initializeAttackFrame();
            enemyController.attackEnemy(hero);
            keys[4] = false;
        } else if (key == ' ') {
            keys[5] = false;
        } else if (key == 'p') {
            keys[6] = false;
        }
    }

    public static void main(String[] args) {
        String[] processingArgs = {"Felineko"};
        Felineko felineko = new Felineko();
        PApplet.runSketch(processingArgs, felineko);
    }

    @Override
    public void update(Observable o, Object arg) {
        gameScreen = 2;
        win = true;
    }
}
