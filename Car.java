import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Car inherited by RaceCar, can knock down the pedestrian it touches, 
 * allowed to change lane within a cool down
 * 
 * @author Yixin Cai
 * @version 2022-10-24
 */
public class Car extends Vehicle
{   
    private boolean isPoliceCared;
    private boolean shouldSpwanPoliceCar;
    
    /**
     * The constructor of Car
     * @param origin The VehicleSpawner
     */
    public Car(VehicleSpawner origin) {
        super(origin); // call the superclass' constructor
        // set instance variables
        isPoliceCared = false;
        shouldSpwanPoliceCar = false;
        maxSpeed = 1.5 + ((Math.random() * 30)/5);
        speed = maxSpeed;
        yOffset = 0;
        gifImages = new GifImage[]{
            new GifImage("carLeft.gif"),
            new GifImage("carRight.gif")
        }; 
        gifImage = gifImages[direction == 1 ? 1 : 0];
        // pre-load sound file
        sound = new GreenfootSound("car.wav");
    }

    /**
     * Act - do whatever the Car wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        super.act();
        VehicleWorld vw = (VehicleWorld)getWorld();
        // spwan a police car if should spwan a police car when the VehicleSpwaner is not spwaning car
        if (shouldSpwanPoliceCar && !origin.isTouchingVehicle()) {
            vw.spawnPoliceCar(origin);
            // turn the shouldSpwanPoliceCar to False, since one car can only activate one police car
            shouldSpwanPoliceCar = false;
            isPoliceCared = true;
        }
    }
    
    /**
     * When a Car hit's a Pedestrian, it should knock it over
     * @return boolean True if it hits the pedestrian, Flase otherwise
     */
    public boolean checkHitPedestrian() {
        int dx = (int)speed + getImage().getWidth()/2;
        Pedestrian p = (Pedestrian)getOneObjectAtOffset(dx * direction, 0, Pedestrian.class);
        
        if (p != null && p.isAwake()){
            p.knockDown();
            // make sure one car can only activate one police car, and skeleton is not included
            if (!(p instanceof Skeleton) && !isPoliceCared ) {
                shouldSpwanPoliceCar = true;
            }
            return true;
        }
        
        return false;
    }
    
    /**
     * To allow spawning a PoliceCar next time hitting a pedestrian
     */
    public void resetIsPoliceCared() {
        isPoliceCared = false;
    }
}
