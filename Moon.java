import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Moon is an effect. A moon gif will show on the World. It moves from
 * left to right and rise in the first half and fall in the second half.
 * While the moon is moving, the background image will be changed to
 * dark mode. Right after moon appears, all of the vihecles, the vehicle
 * spawners and lanes will change direction.
 * 
 * @author Yixin Cai
 * @version 2022-10-24
 */
public class Moon extends Effect
{
    // instance variable
    private int speed;
    
    /**
     * Constructor of Moon
     * @param duration The number of acts that it exists
     */
    public Moon(int duration) {
        super(duration);
        speed = 2;
        gifImage = new GifImage("halloweenMoon.gif");
        setImage(gifImage.getCurrentImage());
    }
    
    /**
     * This method is called by the Greenfoot system when this actor has been inserted into the world.
     * @param w The World
     */
    public void addedToWorld (World w){
        VehicleWorld vw = (VehicleWorld)getWorld();
        vw.startMoon();
    }
    
    /**
     * Act - do whatever the Moon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        moveMoon();
    }
    
    /**
     * Move the moon. Moon gif will should move from left to right.
     * Rise during the first half and fall during the second half.
     */
    private void moveMoon() {
        // x position increment based on tik
        int x = 75 + (int) ( (650 * tik / (double) duration)  );
        if (x < 400) {
            // y position increment during the first half
            int y = 75 + (int) ( (150 / (double) duration) * (150 - tik) );
            setLocation(x, y);
        }
        else {
            // y position decrement during the second half
            int y = 75 - (int) ( (150 / (double) duration) * (150 - tik) );
            setLocation(x, y);
        }
    }
    
    /**
     * Method to end the effect
     */
    protected void endEffect() {
        VehicleWorld vw = (VehicleWorld)getWorld();
        vw.stopMoon();
    }
}
