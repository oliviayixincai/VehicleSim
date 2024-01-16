import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Explosion is triggered by Bomb. It can remove all of the pedestrians 
 * and vehicles in the explosion range, along with sound effect.
 * 
 * @author Yixin Cai 
 * @version 2022-10-24
 */
public class Explosion extends Actor implements iSoundMaker
{
    // instance variables
    private GreenfootImage[] images;
    private GreenfootSound sound;
    private int duration;
    
    /**
     * Constructor
     */
    public Explosion() {
        images = new GreenfootImage[12];
        for (int i = 0; i < 12;i++) {
            images[i] = new GreenfootImage("bomb" + i + ".png");
        }
        // pre-load sound file
        sound = new GreenfootSound("bomb.wav");
        duration = 35;
    }
    
    /**
     * This method is called by the Greenfoot system when this actor has been inserted into the world.
     */
    public void addedToWorld(World w) {
        // Update volume after being added to World
        VehicleWorld vw = (VehicleWorld)w;
        if (sound != null) {
            setVolume(vw.getVolume());
        }
    }
    
    /**
     * Act - do whatever the Explosion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (duration % 3 == 0) {
            // update explosion image every 3 acts
            int index = 11 - duration / 3;
            setImage(images[index]);
        }
        if (duration == 0) {
            sound.play();
            explode();
            getWorld().removeObject(this);
        }
        duration--;
    }
    
    /**
     * Trigger explosion. Remove all of pedestrians and vehicles in the explosion range.
     */
    private void explode() {
        ArrayList<Pedestrian> p = (ArrayList<Pedestrian>)getObjectsInRange(130, Pedestrian.class);
        ArrayList<Vehicle> v = (ArrayList<Vehicle>)getObjectsInRange(130, Vehicle.class);
        for (Vehicle vehicle : v){
            vehicle.removeSelf();
        }
        for (Pedestrian pedestrian : p){
            getWorld().removeObject(pedestrian);
        }
    }

    /**
     * Set volume if there is sound
     * @param volume The current volume
     */
    public void setVolume(int volume) {
        if (sound != null) {
            sound.setVolume(volume);
        }
    }
    
    /**
     * Play sound if there is sound
     */
    public void playSound() {
        if (sound != null) {
            sound.play();
        }
    }
    
    /**
     * Stop sound if there is sound
     */
    public void stopSound() {
        if (sound != null) {
            sound.stop();
        }
    }
}
