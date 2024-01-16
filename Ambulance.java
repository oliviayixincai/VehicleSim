import greenfoot.*;
import java.util.ArrayList;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Ambulance can heal any pedestrian who is intersecting with 
 * it, won't knock down the pedestrian, is not allowed to change lane
 * along with sound effect.
 * 
 * @author Yixin Cai
 * @version 2022-10-24
 */
public class Ambulance extends Vehicle
{
    public Ambulance(VehicleSpawner origin){
        super (origin); // call the superclass' constructor first
        maxSpeed = 2.5;
        speed = maxSpeed;
        yOffset = 5;
        gifImages = new GifImage[]{
            new GifImage("ambulanceLeft.gif"),
            new GifImage("ambulanceRight.gif")
        };
        //determine which gif to choose based on the direction(-1 or 1)
        gifImage = gifImages[direction == 1 ? 1 : 0];
        // pre-load the sound file
        sound = new GreenfootSound("ambulance.wav");
        playSound();
    }
    
    /**
     * Method to chaeck if it hits pedestrian. Heal them if it hits fallen pedestrians.
     * @return boolean True if it touches pedestrian, False otherwise
     */
    public boolean checkHitPedestrian() {
        //Pedestrian p = (Pedestrian)getOneObjectAtOffset((int)speed + getImage().getWidth()/2, 0, Pedestrian.class);
        Pedestrian p = (Pedestrian)getOneIntersectingObject(Pedestrian.class);
        if (p != null){
            // heals any injured pedestrians that it comes in contact with
            if (!p.isAwake()) {
                p.healMe();
            }
            return true;
        }
        return false;
    }
}
