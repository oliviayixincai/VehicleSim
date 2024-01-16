import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Pedestrian is an abstract class inherits SuperSmoothMover. It is
 * inherited by three subclasses Child, Skeleton, Walkman. It provides
 * methods for managing movement and behaviours for interecting with
 * vehicles, such as moving to the other side of the lanes, know down
 * behaviour and healing behaviour.
 * 
 * Modified based on Mr.Cohen's codes. Most of them made by myself.
 * 
 * @author Jordan Cohen, Yixin Cai
 * @version 2022-10-24
 */
public abstract class Pedestrian extends SuperSmoothMover
{
    protected double speed;
    protected double maxSpeed;
    protected int direction;
    protected boolean awake;
    protected GifImage gifImage;
    protected double angle;
    
    /**
     * Constructor of pedestrian with the simplest function. 
     */
    public Pedestrian() {
        this(false);
    }
    
    /**
     * Constructor of pedestrian with the function of create penguin. 
     * @param isPenguin True if wants to create penguin.
     */
    public Pedestrian(boolean isPenguin) {
        angle = 0;
        if (isPenguin) {
            gifImage = new GifImage("penguin.gif");
        }
    }

    /**
     * Act - do whatever the Pedestrian wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if (gifImage != null) {
            setImage(gifImage.getCurrentImage());
        }
        
        if (awake){
            if (getOneObjectAtOffset(0, (int)(direction * getImage().getHeight()/2 + (int)(direction * speed)), Vehicle.class) == null){
                // move pedestrian to the next location
                int distance = (int)(speed*direction);
                double dx = Math.sin(Math.toRadians(angle)) * distance;
                double dy = Math.cos(Math.toRadians(angle)) * distance;
                setLocation(getX() + dx, getY() + dy);
            }
            // remove pedestrian after crossing the road
            if (direction == -1 && getY() < 100){
                getWorld().removeObject(this);
            } else if (direction == 1 && getY() > 550){
                getWorld().removeObject(this);
            }
        }
    }
    
    /**
     * Method to cause this Pedestrian to become knocked down - stop moving, turn onto side
     */
    public void knockDown() {
        speed = 0;
        setRotation (90);
        awake = false;
    }
    
    /**
     * Method to allow a downed Pedestrian to be healed
     */
    public void healMe() {
        speed = maxSpeed;
        setRotation (0);
        awake = true;
    }
    
    /**
     * Method to return True if is awake, False otherwise
     * @return boolean True if is awake, False otherwise
     */
    public boolean isAwake() {
        return awake;
    }
}
