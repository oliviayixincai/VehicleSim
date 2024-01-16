import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Effect is an abstract class that provides methods for managing visual and auditory
 * effects, such as fading in and fading out, removing ones' self after 
 * a certain number of acts, and controlling sound of the effect.
 * 
 * @author Yixin Cai
 * @version 2022-10-24
 */
public abstract class Effect extends Actor implements iSoundMaker
{
    protected GifImage gifImage;
    protected GreenfootImage image;
    protected GreenfootSound sound;
    
    protected boolean fadeIn;
    protected boolean fadeOut;
    protected int fadeDuration;
    protected int duration;
    protected int tik;
    
    /**
     * Constructor of Effect
     * @param duration The number of acts it exsits
     */
    public Effect(int duration) {
        this(duration, false);
    }
    
    /**
     * Constructor of Effect
     * @param duration The number of acts it exsits
     * @param fade True if it has fade effect, False otherwise
     */
    public Effect(int duration, boolean fade) {
        this(duration, fade, fade);
    }
    
    /**
     * Constructor of Effect
     * @param duration The number of acts it exsits
     * @param fadeOut True if it has fade out effect, False otherwise
     * @param fadeIn True if it has fade in effect, False otherwise
     */
    public Effect(int duration, boolean fadeOut, boolean fadeIn) {
        this(duration, fadeOut, fadeIn, 0);
    }
    
    /**
     * Constructor of Effect
     * @param duration The number of acts it exsits
     * @param fadeOut True if it has fade out effect, False otherwise
     * @param fadeIn True if it has fade in effect, False otherwise
     * @param delay The number of acts need to delay
     */
    public Effect(int duration, boolean fadeOut, boolean fadeIn, int delay) {
        this.duration = duration - delay;
        this.tik = 0 - delay;
        this.fadeOut = fadeOut;
        this.fadeIn = fadeIn;
        fadeDuration = 90;
    }
    
    /**
     * This method is called by the Greenfoot system when this actor has been inserted into the world.
     * @param w The World
     */
    public void addedToWorld(World w) {
        VehicleWorld vw = (VehicleWorld)w;
        if (sound != null) {
            setVolume(vw.getVolume());
        }
    }
    
    /**
     * Act - do whatever the Effect wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if (gifImage != null) {
            setImage(gifImage.getCurrentImage());
        }
        
        tik++;

        if (tik < 0) {
            // do nothing if in delay
            return;
        }
        else if (tik >= duration) {
            // remove self after effect duration
            removeSelf();
        }
        else if (fadeIn && tik < fadeDuration) {
            // fade in
            fade(tik, fadeDuration);
        }
        else if (fadeOut && duration - tik < fadeDuration) {
            // fade out
            fade(duration - tik, fadeDuration);
        }
    }
    
    /**
     * Method to remove object and stop sound
     */
    protected void removeSelf() {
        stopSound();
        endEffect();
        getWorld().removeObject(this);
    }
    
    /**
     * An abstract method to end effect
     */
    protected abstract void endEffect();
    
    /**
     * Update image transparency to achieve fade effect
     * Modified from Mr.Cohen's codes.
     * @param numerator The numerator to calculate transparency
     * @param dinominator The dinominator to calculate transparency
     */
    protected void fade(int numerator, int dinominator) {
        double percent = numerator / (double)dinominator;
        // Transparency 0 -- 255
        int newTransparency = (int)(percent * 255);
        image.setTransparency(newTransparency);
    }
    
    /**
     * Play sound in loop
     */
    public void playSound() {
        if (sound != null) {
            sound.playLoop();
        }
    }
    
    /**
     * Stop sound
     */
    public void stopSound() {
        if (sound != null) {
            sound.stop();
        }
    }
    
    /**
     * Set sound volume
     * @param volume The value of volume
     */
    public void setVolume(int volume) {
        if (sound != null) {
            sound.setVolume(volume);
        }
    }
}

