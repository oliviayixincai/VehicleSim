import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * VolumeButton is used to turn up or down for all sounds, inherits Actor.
 * 
 * @author Yixin Cai
 * @version 2022-10-24
 */
public class VolumeButton extends Actor
{
    // instance variables
    private GreenfootImage image;
    private boolean isUp;
    
    /**
     * The constructor of VolumeButton, set images
     * @param isUp True if the volume if the volume turns up, Flase otherwise
     */
    public VolumeButton(boolean isUp) {
        this.isUp = isUp;
        if (isUp) {
            image = new GreenfootImage("volumeUp.png");
        }
        else {
            image = new GreenfootImage("volumeDown.png");
        }
        setImage(image);
    }
    
    /**
     * Act - do whatever the VolumeIcon wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            VehicleWorld vw = (VehicleWorld) getWorld();
            // update the volume
            vw.updateVolume(isUp);
        }
    }
}
