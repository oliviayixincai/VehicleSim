import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Vehicle inherits SuperSmoothMover, controls the motion algorithms and sound effects.
 * 
 * Modified based on Mr.Cohen's codes. Most of them made by myself.
 * 
 * @author Jordan Cohen, Yixin Cai
 * @version 2022-10-24
 */
public abstract class Vehicle extends SuperSmoothMover implements iSoundMaker
{
    protected double maxSpeed;
    protected double speed;
    protected int direction; // 1 = right, -1 = left
    protected boolean moving;
    protected boolean slowed;
    protected boolean allowLaneChange;
    protected int fromLane;
    protected int toLane;
    protected int delay;
    protected int coolDown;
    protected int maxCoolDown;
    protected int yOffset;
    protected VehicleSpawner origin;
    protected GifImage[] gifImages;
    protected GifImage gifImage;
    protected GreenfootSound sound;
    protected GreenfootSound brakeSound;
    protected GreenfootSound honkSound;
    
    // an abstract method that every subclasses have to implements.
    protected abstract boolean checkHitPedestrian();

    /**
     * The constructor of Vehicle.
     * @param origin A VehicleSpawner
     */
    public Vehicle(VehicleSpawner origin) {
        // set instance variables
        this.origin = origin;
        moving = true;
        slowed = false;
        allowLaneChange = false;
        delay = 0;
        coolDown = 0;
        maxCoolDown = 60;
        if (origin.facesRightward()){
            direction = 1;
        } else {
            direction = -1;
            getImage().mirrorHorizontally();
        }
        // pre-load the sound files
        brakeSound = new GreenfootSound("brake.wav");
        honkSound = new GreenfootSound("honk.wav");
    }
    
    /**
     * This method is called by the Greenfoot system when this actor has been inserted into the world.
     * @param w The World
     */
    public void addedToWorld(World w) {
        setLocation (origin.getX() - (direction * 100), origin.getY() - yOffset);
        VehicleWorld vw = (VehicleWorld)w;
        if (vw.isRaining()){
            speed = maxSpeed / 2;
            slowed = true;
        }
        if (sound != null) {
            setVolume(vw.getVolume());
        }
    }
    
    /**
     * Act - do whatever the Vehicle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if (gifImage != null) {
            setImage(gifImage.getCurrentImage());// update the gif images every act
        }
        drive(); 
        checkHitPedestrian();
        if (checkEdge()){
            removeSelf();
        }
    }

    /**
     * A method used by all Vehicles to check if they are at the edge
     * @return boolean True if vehicle is at the edge, False otherwise
     */
    protected boolean checkEdge() {
        if (direction == 1){
            if (getX() > getWorld().getWidth() + 200){
                return true;
            }
        } else {
            if (getX() < -200){
                return true;
            }
        }
        return false;
    }

    /**
     * Method that deals with movement. Speed can be set by individual subclasses in their constructors.
     */
    public void drive() 
    {
        // Ahead is a generic vehicle - we don't know what type BUT
        // since every Vehicle "promises" to have a getSpeed() method,
        // we can call that on any vehicle to find out it's speed
        Vehicle ahead = (Vehicle) getOneObjectAtOffset(direction * (int)(speed + getImage().getWidth()/2 + 4), 0, Vehicle.class);
        
        if (isChangingLane()) {
            // check lane change completion
            VehicleWorld vw = (VehicleWorld) getWorld();
            int toLaneY = vw.getLaneY(toLane);
            int y = getY();
            int x = getX();
            if (fromLane > toLane) {
                if (y <= toLaneY) {
                    setLocation(x, toLaneY- yOffset);
                    turn(45 * direction);
                    fromLane = -1;
                    toLane = -1;
                }
            }
            else if (fromLane < toLane) {
                if (y >= toLaneY) {
                    setLocation(x, toLaneY - yOffset);
                    turn(45 * -direction);
                    fromLane = -1;
                    toLane = -1;
                }
            }
        }
        else if (delay > 0) {
            // set speed to 0 while in delay
            speed = 0;
        }
        else if (ahead == null) {
            // resume speed if no vehicle ahead
            if (slowed) {
                speed = maxSpeed / 2.0;
            }
            else {
                speed = maxSpeed;
            }
        }
        else if (!ahead.isChangingLane()){
            double oldSpeed = speed;
            speed = ahead.getSpeed();
            
            // check and attempt to change lane
            if (shouldChangeLane()) {
                changeLane();
            }
            
            // play brake sound if slow down
            if (speed < oldSpeed) {
                brakeSound.play();
            }
        }
        
        move(speed * direction);
        
        if (delay > 0) {
            delay--;
        }
        if (coolDown > 0) {
            coolDown--;
        }
    }
    
    /**
     * Check if the vehicle should perform lane change
     * @return boolean True if vehicle should perform lane change, False otherwise
     */
    private boolean shouldChangeLane() {
        if (!allowLaneChange) {
            return false;
        }
        
        if (coolDown > 0) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Attempt to change lane. If a target lane is found, honk and turn toward the target land.
     * Then, temporarily increase speed and set a cool down for next lane change.
     */
    private void changeLane() {
        VehicleWorld vw = (VehicleWorld) getWorld();
        int lane = vw.getLane(getY() + yOffset);
        int nextLane = findLaneToChange(lane);
        if (lane == nextLane) {
            return;
        }

        // honk before making lane change
        honkSound.play();
        
        // lane change
        fromLane = lane;
        toLane = nextLane;
        turn(45 * (toLane - fromLane) * direction);
        speed = Math.min(maxSpeed * 2, 15);
        coolDown = maxCoolDown;
    }
    
    /**
     * Give a lane, find the lane to be changed to.
     * Check left side first, then right side.
     * Return given lane if neither left lane and right lane is available.
     * 
     * @param lane the lane number (zero-indexed)
     * @return int the lane number (zero-indexed) that can be changed to
     */
    private int findLaneToChange(int lane) {
        VehicleWorld vw = (VehicleWorld) getWorld();
        int leftLane = lane - direction;
        int rightLane = lane + direction;
        if (vw.isSameDirection(lane, leftLane)) {
            if (checkLaneSpace(vw.getLaneY(leftLane))) {
                return leftLane;
            }
        }
        if (vw.isSameDirection(lane, rightLane)) {
            if (checkLaneSpace(vw.getLaneY(rightLane))) {
                return rightLane;
            }
        }
        return lane;
    }
    
    /**
     * Check if there is enough space for lane change at the given y position
     * 
     * @param landY the y position of a lane's center
     * @return boolean whether there is enough space
     */
    private boolean checkLaneSpace(int laneY) {
        int dy = laneY - getY();
        int width = getImage().getWidth();
        for (int dx = -width; dx < width; dx += 5) {
            Vehicle vehicle = (Vehicle) getOneObjectAtOffset(dx, dy, Vehicle.class);
            if (vehicle != null) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Check if the vehicle is changing lane
     * @return boolean True if is changing line, False otherwise
     */
    public boolean isChangingLane() {
        return fromLane != toLane;
    }
    
    /**
     * Method to remove object and stop sound
     */
    public void removeSelf() {
        stopSound();
        getWorld().removeObject(this);
    }
    
    /**
     * Start playing sound if there is sound
     */
    public void playSound() {
        if (sound != null) {
            sound.playLoop();
        }
    }
    
    /**
     * Stop playing sound if there is sound
     */
    public void stopSound() {
        if (sound != null) {
            sound.stop();
        }
    }
    
    /**
     * Update volume of the sounds
     * @param volume The current volume
     */
    public void setVolume(int volume) {
        if (sound != null) {
            sound.setVolume(volume);
            brakeSound.setVolume(volume);
            honkSound.setVolume(volume);
        }
    }
    
    /**
     * An accessor that can be used to get this Vehicle's speed. Used, for example, when a vehicle wants to see
     * if a faster vehicle is ahead in the lane.
     * @return double The vehicle's speed
     */
    public double getSpeed(){
        return speed;
    }
    
    /**
     * Slow down vehicle
     */
    public void slowMeDown(){
        this.speed = maxSpeed / 2.0;
        slowed = true;
    }
    
    /**
     * Called by Effects to resume normal speed
     */
    public void resumeNormalSpeed(){
        this.speed = maxSpeed;
        slowed = false;
    }
    
    /**
     * Change direction of the vehicle
     */
    public void changeDirection() {
        this.direction = -this.direction;
        if (gifImage != null) {
            gifImage = gifImages[direction == 1 ? 1 : 0];
        }
        // switch fromLane and toLane
        int tempLane = fromLane;
        fromLane = toLane;
        toLane = tempLane;
    }
}
