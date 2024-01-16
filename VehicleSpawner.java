import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A Spawner object is a place where a Vehicle can spawn. Each spawner is
 * able to check if there is already a Vehicle in the spot to avoid spawning
 * multiple Vehicles on top of each other.
 * 
 * Modified based on Mr.Cohen's codes.
 * 
 * @author Jordan Cohen, Yixin Cai
 * @version 2022-10-24
 */
public class VehicleSpawner extends Actor
{
    public static final Color TRANSPARENT_RED = new Color (255, 0, 0, 128);
    
    public static final int DIST_BETWEEN_CARS = 128;
    
    private GreenfootImage image;
    
    private boolean rightward;
    private boolean visible;
    private int height, width;
    
    /**
     * The constructor of VehicleSpawner, set image and instance variables
     * @param rightward Ture if it is facing right
     * @param laneHeight The height of the lan
     */
    public VehicleSpawner(boolean rightward, int laneHeight) {
        this.rightward = rightward;
        this.height = (int)(laneHeight * 0.75);
        width = DIST_BETWEEN_CARS;
        // set this to true to see the Spawners - might help with understanding of how this works:
        visible = true;
        image = new GreenfootImage("doubleSidedArrow.png");
        if(visible){
            setImage(image);
        }
    }
    
    /**
     * Method to chack if is facing right
     * @return boolean True if it is facing right side, False otherwose
     */
    public boolean facesRightward() {
        return rightward;
    }
    
    /**
     * Method to check if is touching an vehicle
     * @return boolean True it is touching an vehicle, False otherwose
     */
    public boolean isTouchingVehicle () {
        return this.isTouching(Vehicle.class);
    }
    
    /**
     * Method to change the direction
     */
    public void changeDirection() {
        this.rightward = !rightward;
    }
}
