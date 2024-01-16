import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * PoliceCar only be activated by vehicles that hit pedestrians except for Skeleton,
 * allowed to change lane without cool down, along with sound effect. One car will only 
 * activate one police car no matter how many pedestrians it hits, but this setting will 
 * be refreshed when the direction is reversed by Moon.
 * 
 * @author Yixin Cai 
 * @version 2022-10-24
 */
public class PoliceCar extends Vehicle
{
    /**
     * The constructor of PoliceCar
     * @param origin The VehicleSpawner
     */
    public PoliceCar(VehicleSpawner origin){
        super (origin); // call the superclass' constructor first
        allowLaneChange = true;
        maxCoolDown = 0;
        maxSpeed = 10;
        speed = maxSpeed;
        yOffset = 4;
        gifImages = new GifImage[]{
            new GifImage("policeCarLeft.gif"),
            new GifImage("policeCarRight.gif")
        };
        gifImage = gifImages[direction == 1 ? 1 : 0];
        // pre-load
        sound = new GreenfootSound("police.wav");
        sound.setVolume(0);
        playSound();
    }

    /**
     * Act - do whatever the PoliceCar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }
    
    /**
     * When a PoliceCar hit's a Pedestrian, it won't knock it over
     * @return boolean True if it hits pedestrian, False otherwise
     */
    public boolean checkHitPedestrian() {
        Pedestrian p = (Pedestrian)getOneIntersectingObject(Pedestrian.class);
        if (p != null){
            return true;
        }
        return false;
    }
}
