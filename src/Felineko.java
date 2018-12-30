import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.sound.SoundFile;

import java.util.ArrayList;
/* Music created using https://www.beepbox.co/#6n31s0kbl00e05t7m0a7g0fj7i0r1o3210T0w1f3d1c4h0v0T0w1f1d1c3h0v2T0w2f1d1c0h0v3T2w1d1v0b4zgQ4x4h4h4h4h4h4h4h4h4z8N4h4h4h4x804h4h4h4p229IMMpFBcu6p_iCu3jmCBwFxMfR4QPRnknRdnmZgjZplcFEZZVRbCDTw9nPmQPPDeYPOg0F5gFDM5cu1V0swuhhpxNjdjCgYidd7i3g1daoUZ0q0Pjng6AcQFEYz19Fym6wOOt551B0
 * BeepBox website.
 */

/**
 * A platformer game.
 */
public class Felineko extends PApplet{
    private boolean[] keys = new boolean[6];
    private PImage menuBackground;
    private int gameScreen = 0;
    private Map map;
    private MapController mapController = new MapController(this);
    private PlayerController playerController;
    private EnemyController enemyController;
    private Player hero;
    private Enemy snake;
    private Enemy snake2;
    private PVector translation = new PVector(0, 0);
    private PVector prevPlayerPos = new PVector(100, 100);
    private SoundFile file;
    private PImage[] healthBar = new PImage[11];
    String audioName = "Felineko.wav";
    String path;

    /**
     * Preset settings of the game.
     */
    public void settings(){
        size(600, 600);

        menuBackground = loadImage("MenuTwo.png");
        menuBackground.resize(600, 0);
        for (int i = 0; i < healthBar.length; i++){
            healthBar[i] = loadImage(Integer.toString(i*10)+".png");
        }
        map = mapController.setUpMap();
        mapController.loadMap(map);
        keys[0]=false;
        keys[1]=false;
        keys[2]=false;
        keys[3]=false;
        keys[4]=false;
        path = sketchPath(audioName);
        file = new SoundFile(this, path);
        ArrayList<Enemy> activeEnemies = new ArrayList<>();
        snake = new Enemy(100, 210, 1, 7, 20, 30, 60, 2, "SNAKE");
        snake2 = new Enemy(240, 540, 1, 7, 20, 30, 60, 2, "SNAKE");
        hero = new Player(25,100, 100, "KNIGHT", 60, 30, 5, "HERO", 20);
        activeEnemies.add(snake);
        activeEnemies.add(snake2);
        playerController = new PlayerController(this, hero);
        enemyController = new EnemyController(this, snake, activeEnemies);
        playerController.loadAttackSprites(hero);
//        file.play();
//        file.loop();
    }

    /**
     * Updates the screen.
     */
    public void draw(){
        switch (gameScreen) {
            case 0:
                background(menuBackground);
                if (mouseX >= 207 && mouseX <= 392 && mouseY >= 380 && mouseY <= 416 && mousePressed) {
                    gameScreen = 1;
                }
                break;
            case 1:
                translate(translation.x+100, translation.y+150);
                playerController.immunityUpdate(hero);
                enemyController.immunityUpdateAll();
                mapController.drawMap(map);
                image(healthBar[hero.getHP()/10],-(translation.x + 100), -(translation.y + 150));
                playerController.checkSpecialCollision(map, hero);
                playerController.applyGravity(map, hero);

                if (keys[1]) {
                    playerController.moveLeft(map, hero);
                }

                if (keys[0]) {
                    playerController.moveRight(map, hero);
                }

                if (!(keys[1] || keys[0])){
                    playerController.notMoving(map, hero);
                }

                if (keys[2]) {
                    playerController.playerJump(map, hero);
                } else {
                    playerController.notJumping(map, hero);
                }

                if (playerController.startedAttack()){
                    playerController.drawAttack(hero);
                }else{
                    playerController.drawEntity(hero.getX(), hero.getY());
                }
                System.out.println(snake2.getHP());

                enemyController.drawEnemies();
                enemyController.updateLocation(map);
                enemyController.attackPlayer(hero);
                translation.x -= hero.getX()-prevPlayerPos.x;
                translation.y -= hero.getY()-prevPlayerPos.y;
                prevPlayerPos.x = hero.getX();
                prevPlayerPos.y = hero.getY();
                break;
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
                hero.setJumpCounter(hero.getJumpCounter()+1);
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
        }
    }

    public static void main(String[] args){
        String[] processingArgs = {"Felineko"};
        Felineko felineko = new Felineko();
        PApplet.runSketch(processingArgs, felineko);
    }
}
