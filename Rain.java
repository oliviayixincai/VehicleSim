import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.ArrayList;
/**
 * Rain is an effect. Two colored rain will appear on the World with
 * fade effect and sound effect. The density of the rain will gradually
 * be changed from small to large and then changed back to small. All
 * of the vehicles will be slowed down during the rain.
 * 
 * @author Yixin Cai
 * @version 2022-10-24
 */
public class Rain extends Effect
{
    // instance variable
    private int density;
    
    /**
     * Rain Constructor
     * @param duration The number of acts that it exists
     */
    public Rain (int duration){
        super(duration, true);
        // set up sound
        sound = new GreenfootSound("rain.wav");
        playSound();
    }

    /**
     * This method is called by the Greenfoot system when this actor has been inserted into the world.
     * @param w The World
     */
    public void addedToWorld (World w){
        super.addedToWorld(w);
        density = 2;
        image = drawRain(w.getWidth(), w.getHeight(), density);
        setImage(image);
        // slow down all vehicles after Rain effect starts
        ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) w.getObjects(Vehicle.class);
        for (Vehicle v : vehicles){
            v.slowMeDown();
        }
        VehicleWorld vw = (VehicleWorld)w;
        vw.startRaining();
    }

    /**
     * Act - do whatever the Rain wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        VehicleWorld vw = (VehicleWorld)getWorld();
        // update rain density every 10 acts
        if (tik % 10 == 0) {
            // increase rain density before fade out
            if (tik < duration - fadeDuration) {
                density++;
            }
            // decrease rain density during fade out
            else {
                density -= 2;
            }
            image = drawRain(vw.getWidth(), vw.getHeight(), density);
            setImage(image);
        }
        
        super.act();
    }
    
    /**
     * Method to end the effect
     */
    protected void endEffect() {
        VehicleWorld vw = (VehicleWorld)getWorld();
        vw.stopRaining();
        // resume vehicle speed after Rain effect finishes
        ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) vw.getObjects(Vehicle.class);
        for (Vehicle v : vehicles){
            v.resumeNormalSpeed();
        }
    }

    /**
     * Method to draw Rain. The density should be 1-100. 100 will be almost completely white
     * @param width The width of the Rain range
     * @param height The height of the Rain range
     * @param density The density of the Rain
     * 
     * Modified from Mr.Cohen's codes.
     */
    public static GreenfootImage drawRain(int width, int height, int density){

        Color[] swatch = new Color[64];
        int red = 128;
        int blue = 192;

        // Build a color pallete out of shades of near-white yellow and near-white blue 
        // first half blue tones
        for (int i = 0; i < swatch.length/2; i++) { 
            swatch[i] = new Color(red, 240, 255);
            red += 2;
        }
        // second half yellow tones
        for (int i = swatch.length/2; i < swatch.length; i++) { 
            swatch[i] = new Color(255, 255, blue);
            blue++;
        }

        // The temporary image, my canvas for drawing
        GreenfootImage temp = new GreenfootImage(width, height);
        // Run this loop one time per "density"

        for (int i = 0; i < density; i++) {
            for (int j = 0; j < 100; j++) { // draw 100 circles
                int randSize;
                // Choose a random colour from my swatch, and set its tranparency randomly
                int randColor = Greenfoot.getRandomNumber(swatch.length);;
                int randTrans = Greenfoot.getRandomNumber(220) + 35; // around half transparent
                temp.setColor(swatch[randColor]);

                //setTransparency(randTrans);
                // random locations for our dot
                int randX = Greenfoot.getRandomNumber(width);
                int randY = Greenfoot.getRandomNumber(height);

                int tempVal = Greenfoot.getRandomNumber(250);
                if (tempVal >= 1){
                    temp.drawRect(randX, randY, 0, 8);
                }else{
                    randSize = Greenfoot.getRandomNumber(2) + 2;
                    temp.fillOval(randX, randY, randSize, randSize);
                }
                // silly way to draw a dot..
            }
        }

        return temp;
    }
}