import processing.core.PApplet;
import processing.core.PImage;

public class EntityController {
    private PApplet felineko;

    EntityController (PApplet felineko){
        this.felineko = felineko;
    }

    public void drawEntity(String name, int x, int y){
        PImage newSprite = felineko.loadImage(name + ".png");
        felineko.image(newSprite, x, y);
    }

    public void applyGravity(Map map, Player hero){
        hero.increYBy(2*hero.getFallSpeed());
        hero.increFallSpeed();

        for (int i = 0; i<Player.WIDTH; i++) {
            if (map.getTile((hero.getX()+i)/30, (hero.getY()+Player.HEIGHT)/30).getType().equals("GROUND")) {
                hero.increYBy(-(hero.getY()+Player.HEIGHT)%30);
                hero.resetFallSpeed();
                hero.resetJumpSpeed();
            }
        }
    }


}