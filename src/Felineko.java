import processing.core.PApplet;
import processing.core.PImage;
import processing.sound.SoundFile;

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
        file.play();
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
                System.out.println(hero.getJumpCounter());
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

                if (keys[2]) {
                    entityController.jump(map, hero);
                    if (entityController.onGround(map, hero)){
                        hero.setJumpCounter(hero.getJumpCounter()+1);
                    }
                }

                if (!keys[2] && entityController.onGround(map, hero)){
                    hero.setJumpCounter(0);
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
