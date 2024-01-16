import greenfoot.*;
import java.util.ArrayList;

/**
 * Halloween is an effect. A Witch will be added into the world for a short period
 * along with music. Fade in the halloween background after witch
 * disappears. After halloween backgroup fade in, revive all of the 
 * skeletons and set bombs in random places for two rounds, then fade
 * out the background and remove itself.
 * 
 * @author Yixin Cai
 * @version 2022-10-24
 */
public class Halloween extends Effect
{
    /**
     * Constructor of Halloween
     * @param duration The number of acts that it exists
     */
    public Halloween(int duration) {
        // call super class constructor with delay
        // so that halloween can appear after witch
        super(duration, true, true, 185);
        image = getImage();
        image.setTransparency(0);
        // pre-load the sound file
        sound = new GreenfootSound("halloween.wav");
    }
    
    /**
     * This method is called by the Greenfoot system when this actor has been inserted into the world.
     * @param w The World
     */
    public void addedToWorld (World w){
        super.addedToWorld(w);
        VehicleWorld vw = (VehicleWorld)w;
        vw.startHalloween();
        // add Witch to world
        vw.addObject(new Witch(185), 120, 100); 
        playSound();
    }
    
    /**
     * Act - do whatever the Halloween wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        VehicleWorld vw = (VehicleWorld)getWorld();
        
        if (tik == fadeDuration) {
            // revive all skeletons
            ArrayList<Skeleton> skeletons = (ArrayList<Skeleton>) vw.getObjects(Skeleton.class);
            for (Skeleton skeleton : skeletons) {
                skeleton.revive();
            }
        }
        
        // set 2 bombs in world before fade in
        if (tik == 0) {
            vw.setBomb(2);
        }
        
        // set 3 bombs in world before fade out
        if (duration - tik == fadeDuration) {
            vw.setBomb(3);
        }

        super.act();
    }
    
    /**
     * Method to end the effect
     */
    protected void endEffect() {
        VehicleWorld vw = (VehicleWorld)getWorld();
        vw.stopHalloween();
    }
}
