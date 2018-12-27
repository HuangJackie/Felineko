import processing.core.PApplet;
import processing.core.PImage;
import processing.sound.SoundFile;
/* Music created using https://www.beepbox.co/#6n31s0kbl00e05t7m0a7g0fj7i0r1o3210T0w1f1d1c0h0v0T0w2f1d1c0h0v0T0w2f1d1c0h0v0T2w1d1v0b4x8Q4x4h4h4h4h4h4h4h4h4h4h4h4h4h4x804h4h4h4p21KIMMpFBcu6p_iCu3jmCBwFxMfR4QPRnknRdnmZgjZplcFEZZVRbCDTw9nPmQPPDeYPOg0F5gFDM5cu1V0swuhg0CBcssd50g4CC9oq3b9Qkk6k0
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
    private EntityController entityController = new EntityController(this);
    private PlayerController playerController = new PlayerController(this);
    private Player hero;
    private SoundFile file;
    String audioName = "Felineko.wav";
    String path;

    /**
     * Preset settings of the game.
     */
    public void settings(){
        size(600, 600);
        menuBackground = loadImage("MenuTwo.png");
        menuBackground.resize(600, 0);
        map = mapController.setUpMap();
        mapController.loadMap(map);
        keys[0]=false;
        keys[1]=false;
        keys[2]=false;
        keys[3]=false;
        keys[4]=false;
        path = sketchPath(audioName);
        file = new SoundFile(this, path);
//        file.play();
    }

    /**
     * Updates the screen.
     */
    public void draw(){
        switch (gameScreen) {
            case 0:
                background(menuBackground);
                if (mouseX >= 207 && mouseX <= 392 && mouseY >= 380 && mouseY <= 416 && mousePressed) {
                    hero = new Player(25, 5, 100, 100, "KNIGHT");
                    gameScreen = 1;
                }
                break;
            case 1:
                mapController.drawMap(map);
                System.out.println(hero.getJumpCounter() + " " + hero.hasJumpedOnce() + " " + entityController.onGround(map, hero) + " " + hero.isSlidOff());
                entityController.applyGravity(map, hero);

                if (keys[1]) {
                    entityController.moveLeft(map, hero);
                }

                if (keys[0]) {
                    entityController.moveRight(map, hero);
                }

                if (!(keys[1] || keys[0])){
                    entityController.notMoving(map, hero);
                }

                if (hero.hasJumpedOnce() && entityController.onGround(map, hero)){
                    hero.setSlidOff(true);
                }

                if (keys[2] && playerController.canJump(map, hero)) {
                    System.out.println("If");
                    entityController.jump(map, hero);
                    hero.setHasJumpedOnce(true);
                } else if (!keys[2]) {
//                    playerController.updateState(hero);
                    if (entityController.onGround(map, hero)) {
                        hero.setJumpCounter(0);
                        hero.resetFallSpeed();
                        hero.resetJumpSpeed();
                    }
                    hero.setHasJumpedOnce(false);
                    hero.setSlidOff(false);
                }

                entityController.drawEntity("HERO", hero.getX(), hero.getY());
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
