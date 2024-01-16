import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A child can turn the move direction into different angles(20 - 60)
 * while crossing lanes, can be healed by ambulance.
 * 
 * @author Yixin Cai 
 * @version 2022-10-24
 */
public class Child extends Pedestrian
{
    // set instance and constant variables
    private static int CHANGE_COUNT_MAX = 30;
    private int changeCount;
    
    /**
     * Constructor of Child, default boolean isPenguin is False
     * @param direction The int that represents direction
     */
    public Child(int direction) {
        this(direction, false);
    }
    
    /**
     * Constructor of Child with two params
     * @param direction The int that represents direction
     * @param isPenguin Ture if can turn to penguin, False otherwise
     */
    public Child(int direction, boolean isPenguin) {
        super(isPenguin);
        // choose a random speed
        maxSpeed = Math.random() * 2 + 1.25;
        speed = maxSpeed;
        // start as awake 
        awake = true;
        this.direction = direction;
        // count down for move angle change
        changeCount = CHANGE_COUNT_MAX;
    }
    
    /**
     * Act - do whatever the Child wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
        if (changeCount > 0) {
            changeCount--;
        }
        else {
            // move in a random angle (20 - 60), either left or right
            int random = Greenfoot.getRandomNumber(41) + 20;
            if (Greenfoot.getRandomNumber(2) == 1) {
                angle = random;
            }
            else {
                angle = -random;
            }
            changeCount = CHANGE_COUNT_MAX;
        }
    }
}
