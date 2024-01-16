import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Walkman - Move in straight line, can be healed by ambulance. 
 * 
 * @author Yixin Cai
 * @version 2022-10-24
 */
public class Walkman extends Pedestrian
{
    /**
     * Constructor of Walkman, default isPenguin is false
     * @param direction The int that represents the direction
     */
    public Walkman(int direction) {
        this(direction, false);
    }
    
    /**
     * Constructor of Walkman with two params
     * @param direction The int that represents the direction
     * @param isPenguin Ture if can turn to penguin, False otherwise
     */
    public Walkman(int direction, boolean isPenguin) {
        super(isPenguin);
        // choose a random speed
        maxSpeed = Math.random() * 2 + 1;
        speed = maxSpeed;
        // start as awake 
        awake = true;
        this.direction = direction;
    }
    
}
