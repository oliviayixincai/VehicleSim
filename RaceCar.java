import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * RaceCar inherits Car, can speed up with engine sound effect if it hits pedestrian including Skeleton.
 * 
 * @author Yixin Cai
 * @version 2022-10-24
 */
public class RaceCar extends Car
{
    // accelerated speed after hitting a pedestrian
    private double acceleratedSpeed;

    /**
     * The constructor of RaceCar
     * @param origin The VehicleSpawner
     */
    public RaceCar(VehicleSpawner origin) {
        super(origin); // call the superclass' constructor
        maxCoolDown = 30;
        allowLaneChange = true;
        acceleratedSpeed = maxSpeed * 3;
        gifImages = new GifImage[]{
            new GifImage("raceCarLeft.gif"),
            new GifImage("raceCarRight.gif")
        };
        gifImage = gifImages[direction == 1 ? 1 : 0];
    }
    
    /**
     * When a Car hit's a Pedestrian, it should knock it over and accelerate
     * @return boolean True if it hits pedestrian, False otherwise
     */
    @Override
    public boolean checkHitPedestrian() {
        boolean isHit = super.checkHitPedestrian();
        if (isHit) {
            // accelerate and play sound after hitting pedestrian 
            maxSpeed = acceleratedSpeed;
            playSound();
        }
        return isHit;
    }
}
