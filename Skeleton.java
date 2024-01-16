import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Skeleton is Move in straight line, cannot be healed by ambulance,can be revived by halloween effect.
 * 
 * @author Yixin Cai
 * @version 2022-10-24
 */
public class Skeleton extends Pedestrian
{
    /**
     * Constructor of Skeleton
     * @param direction The int that represents the direction
     */
    public Skeleton(int direction) {
        super();
        // choose a random speed
        maxSpeed = Math.random() * 2 + 1;
        speed = maxSpeed;
        // start as awake 
        awake = true;
        this.direction = direction;
        gifImage = new GifImage("skeleton.gif");
    }
    
    /**
     * Skeleton cannot be healed. Override healMe() in supercalss.
     */
    @Override
    public void healMe () {
        
    }
    
    /**
     * Revive skeleton
     */
    public void revive() {
        awake = true;
        speed = maxSpeed;
        setRotation(0);
    }
}
