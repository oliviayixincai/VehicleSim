import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Witch inherits Actor, set a gif on the World for a short period.
 * 
 * @author Yixin Cai
 * @version 2022-10-24
 */
public class Witch extends Actor
{
    // instance variables
    private int tik;
    private GifImage witchGif = new GifImage("witch.gif");
    
    /**
     * The constructor of Witch
     * @param tik A int to calculate the acts
     */
    public Witch(int tik) {
        setImage(witchGif.getCurrentImage());
        this.tik = tik;
    }
    
    /**
     * Act - do whatever the Witch wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (tik > 0) {
            tik--;
            setImage(witchGif.getCurrentImage());
        }
        else {
            // the tik is 0 means the time is off, object will be removed
            VehicleWorld vw = (VehicleWorld)getWorld();
            vw.removeObject(this);
        }
    }
}
