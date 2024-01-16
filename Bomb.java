import greenfoot.*; 
import java.util.ArrayList;

/**
 * Bomb is a trap used to trigger the explosion. If pedestrians
 * or vihicles are intersecting with a bomb, explosion will be triggered.
 * 
 * @author Yixin Cai
 * @version 2022-10-24
 */
public class Bomb extends Actor
{
    
    /**
     * Act - do whatever the Bomb wants to do. This method is 
     * called whenever the 'Act' or 'Run' button gets pressed in 
     * the environment.
     */
    public void act()
    {
        // check if bomb is trigger by checking interesecting pedestrians and vehicles
        ArrayList<Pedestrian> p = (ArrayList<Pedestrian>) getIntersectingObjects(Pedestrian.class);
        ArrayList<Vehicle> v = (ArrayList<Vehicle>) getIntersectingObjects(Vehicle.class);
        if (p.size() > 0 || v.size() > 0) {
            explode();
        }
    }
    
    /**
     * Trigger bomb explosion effect.
     */
    private void explode() {
        Explosion explosion = new Explosion();
        getWorld().addObject(explosion, this.getX() - 8, this.getY() + 10);
        getWorld().removeObject(this);
    }
    
}
