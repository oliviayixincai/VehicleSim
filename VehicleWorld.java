import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * <h1>The new and vastly improved 2022 Vehicle Simulation Assignment.</h1>
 * <p> This is the first redo of the 8 year old project. Lanes are now drawn dynamically, allowing for
 *     much greater customization. Pedestrians can now move in two directions. The graphics are better
 *     and the interactions smoother.</p>
 * <p> The Pedestrians are not as dumb as before (they don't want straight into Vehicles) and the Vehicles
 *     do a somewhat better job detecting Pedestrians.</p>
 * 
 * <h2>Credit:</h2>
 * 
 * <p>
 * Halloween music:
 * Spooky Ride by Twin Musicom | http://www.twinmusicom.org/
 * Music promoted by https://www.chosic.com/free-music/all/
 * Creative Commons CC BY 4.0
 * https://creativecommons.org/licenses/by/4.0/
 * </p>
 * <p>
 * Rain music:
 * https://www.chosic.com/free-music/all/
 * https://mixkit.co/free-sound-effects/pop/
 * </p>
 * <p>Car sound clips: https://mixkit.co/free-sound-effects</p>
 * <p>Background picture: https://www.pinterest.ca/pin/863565297290578829/</p>
 * <p>Gif images: https://www.canva.com/</p>
 * <p>Bomb gif: https://gfycat.com/constantmadgalapagosalbatross</p>
 * <p>Code: Mr.Cohen's starter codes and other demos.</p>
 * 
 * <h1>Reflection</h1>
 * 
 * <p>My scenario is a simulation of a world that has effects and interactions between pedestrians and vehicles. <b>See detailed features in each Class.</b></p>
 * 
 * <h2>Pedestrian</h2>
 * <p>Pedestrians will be randomly spawned from one side of the road and move to the other side.</p>
 * <p>Pedestrians can be knocked down if hit by Car or RaceCar, and healed by Ambulance except for Skeleton.</p>
 * <p>There are three types of pedestrians, Walkman, Child and Skeleton.</p>
 * <ul>
 *  <li>Walkman: walk staight, can be knocked down and healed.</li>
 *  <li>Skeleton: walk straight, can be knocked down, cannot be healed, revive during halloween.</li>
 *  <li>Child: walk with unpredicted changing angles(20 - 60), can be knocked down and healed.</li>
 * </ul>
 * 
 * 
 * <h2>Vehicle</h2>
 * <p>Vehicles, except for PoliceCar, will be randomly spawned at the beginning of the lane and move along the lane.</p>
 * <p>PoliceCar will appear in the same lane once a Car or RaceCar hits a pedestrian.</p>
 * <p>RaceCar and PoliceCar will change lane if nearby lane is clear when stuck behind a slower vehicle</p>
 * <p>Vehicles will slow down when they are stuck behind a slower vehicle.</p>
 * <p>Vehicles will have brake sound effect when slow down.</p>
 * <p>Vehicles will have honk sound effect before change lane.</p>
 * <p>Vehicles will increase speed during lane change.</p>
 * <p>There are five types of vehicles, Ambulance, Bus, Car, RaceCar, and PoliceCar.</p>
 * <ul>
 *  <li>Ambulance: heals Walkman and Child</li>
 *  <li>Bus: pick up Walkman and Child and stop one second after</li>
 *  <li>Car: can hit pedestrian</li>
 *  <li>RaceCar: can hit pedestrian and increase speed after, can change lane</li>
 *  <li>PoliceCar: high speed, can change lane</li>
 * </ul>
 * 
 * <h2>Effect</h2>
 * <p>Effects will be randomly spawned and last for a short period.</p>
 * <p>Effects have auditory and/or visual effects.</p>
 * <p>There are three world-wide effects, Halloween, Moon and Rain.</p>
 * 
 * <h2>Halloween</h2>
 * <p>A witch gif will appear on the top left of the world for a short period.</p>
 * <p>After witch disappear, halloween background will fade in and fade out.</p>
 * <p>After halloween backgroup fade in, revive all of the skeletons.</p>
 * <p>Set two rounds of bombs in random places.</p>
 * 
 * <h2>Moon</h2>
 * <p>At the top of the world, a moon gif will move from left to right.</p>
 * <p>Rise during the first half and fall during the second half.</p>
 * <p>Background image will be in dark mode.</p>
 * <p>Right after moon appears, all lanes, vihecles and vehicle spawners will change direction.</p>
 * 
 * <h2>Rain</h2>
 * <p>Two colored rain with fade in and fade out.</p>
 * <p>Rain density will gradually increase before fading out and decrease while fading out.</p>
 * <p>All vehicles will be slowed down during the rain effect.</p>
 * <p>Child and Walkman spawned during the effect will have penguin appearance.</p>
 * 
 * <h2>Bomb</h2>
 * <p>Bombs are traps to trigger explosions. If pedestrians or vihicles are in contact with a bomb, explosion will be triggered.</p>
 * 
 * <h2>Explosion</h2>
 * <p>Explosions are triggered by bombs and will remove all of the pedestrians and vehicles in the explosion range, along with sound effect.</p>
 * 
 * <h2>Volume Control</h2>
 * <p>Lable and Buttons at the top right of the world.</p>
 * 
 * <h2>Sounds</h2>
 * <p>World background sound while not in effect</p>
 * <p>Halloween effect sound</p>
 * <p>Rain effect sound</p>
 * <p>Explosion sound</p>
 * <p>Ambulance sound</p>
 * <p>PoliceCar sound</p>
 * <p>RaceCare sound after hitting pedestrian</p>
 * <p>Honk sound before vehicles change lane</p>
 * <p>Brake sound when vehicles slow down</p>
 * 
 * 
 * <h1>Checklist</h1>
 * <ul>
 *  <li>Some or all Vehicles change lanes when a slower vehicle is ahead via drive() method - <b>DONE</b></li>
 *  <li>Ambulance and Bus interactions implemented - <b>DONE</b></li>
 *  <li>An additional Vehicle class has been implemented with its own interactions - <b>DONE (RaceCar, PoliceCar)</b></li>
 *  <li>Pedestrian has been turned into an abstract superclass with at least two subclasses with unique behavior and/or traits - <b>DONE (Child, Skeleton)</b></li>
 *  <li>Added a Local, Collision- or Detection- triggered event or effect, that temporarily changes the speed (or some other attribute/behavior) of all Vehicles in the simulation through use of ArrayList and iterative loop - <b>DONE (Bomb, Rain, Skeleton, PoliceCar RaceCar, Moon, VolumeButton)</b></li>
 *  <li>Added a worldwide effect that affects all Vehicles of any given class (can be all Vehicles, all Ambulances, etc). through use of ArrayList and iterative loop - <b>DONE (Moon, Rain)</b></li>
 *  <li>Added (6) sound effects, as described - <b>DONE (ambient background music, halloween sound, rain sound, explosion sound, ambulance sound, race car acceleration sound, police car siren, honk sound, brake sound)</b></li>
 *  <li>Code uses inheritance appropriately to avoid redundancy, using abstract methods, superclass methods and/or other tools appropriately. - <b>DONE (I tried my best in my level)</b></li>
 *  <li>Basic simulation functions like spawning, object removal and movement work correctly. - <b>DONE (Haven't found any bugs yet)</b></li>
 *  <li>Code follows coding conventions: - <b>(I tried my best)</b></li>
 *  <ul>
 *      <li>commented appropriately (a level of detail to explain to someone at approximately your skill level) - <b>DONE</b></li>
 *      <li>Variables, methods and classes named correctly - <b>DONE</b></li>
 *      <li>Consistent indentation, consistent style. - <b>DONE</b></li>
 *      <li>Don't use public variables! - <b>DONE</b></li>
 *  </ul>
 *  <li>Completed the written reflection at the top of the World class as a block comment. - <b>DONE (Here it is!)</b></li>
 * </ul>
 * 
 * @author Jordan Cohen, Yixin Cai 
 * @version 2022-10-23
 */
public class VehicleWorld extends World
{
    private GreenfootImage background;
    private GreenfootImage backgroundDark;
    private GreenfootSound sound;

    // Color Constants
    public static Color PURPLE_BORDER = new Color (181, 126, 220);
    public static Color GREY_STREET = new Color (83, 89, 93);
    public static Color PINK_LINE = new Color (255, 192, 203);
    public static Color BLUE_MIDLINE = new Color (145, 193, 226);

    // Instance variables / Objects
    private boolean twoWayTraffic, splitAtCenter;
    private boolean isHalloween;
    private boolean isRaining;
    private boolean isMoon;
    private int laneHeight, laneCount, spaceBetweenLanes;
    private int[] lanePositionsY;
    private VehicleSpawner[] laneSpawners;
    private int volume;
    private Label volumeLable;

    /**
     * Constructor for objects of class VehicleWorld.
     */
    public VehicleWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1, false); 
        Greenfoot.setSpeed(50); // reset to default speed in case I played with it
        setPaintOrder(VolumeButton.class, Effect.class, Pedestrian.class, Bus.class, Car.class, Ambulance.class, VehicleWorld.class);

        // set up background
        background = new GreenfootImage("background.png");
        backgroundDark = new GreenfootImage("backgroundDark.png");
        
        setBackground(background);
        
        // Set critical variables
        laneCount = 6;
        laneHeight = 48;
        spaceBetweenLanes = 6;
        splitAtCenter = true;
        twoWayTraffic = true;
        
        //Set initial boolean for Effects
        isHalloween = false;
        isMoon = false;
        isRaining = false;

        // Init lane spawner objects 
        laneSpawners = new VehicleSpawner[laneCount];

        // Prepare lanes method - draws the lanes
        lanePositionsY = prepareLanes(this, background, laneSpawners, 222, laneHeight, laneCount, spaceBetweenLanes, twoWayTraffic, splitAtCenter);
        
        // Set initial volume
        volume = 60;
        
        // Add volume control
        volumeLable = new Label(volume + " %");
        addObject(new VolumeButton(false), 700, 50);
        addObject(new VolumeButton(true), 760, 50);
        addObject(volumeLable, 730, 15);
        
        // pre-load sound with initial volume
        sound = new GreenfootSound("trafficBackground.wav");
        sound.setVolume(volume);
    }
    
    /**
     * Act - do whatever the VehicleWorld wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act () {
        // Method to manage spawn of various Vehicles, Pedestrians, Effects
        spawn();
    }
    
    /**
     * This method is called by the Greenfoot system when the execution has started.
     */
    public void started() {
        // start all sounds
        ArrayList<iSoundMaker> sounds = (ArrayList<iSoundMaker>) getObjects(iSoundMaker.class);
        for (iSoundMaker sound : sounds){
            sound.playSound();
        }
        // play background sound in loop
        sound.playLoop();
    }
    
    /**
     * This method is called by the Greenfoot system when the execution has stopped.
     */    
    public void stopped() {
        // stop all sounds 
        ArrayList<iSoundMaker> sounds = (ArrayList<iSoundMaker>) getObjects(iSoundMaker.class);
        for (iSoundMaker sound : sounds){
            sound.stopSound();
        }
        // stop background sound
        sound.stop();
    }

    /**
     * The method to spwan vehicles, pedestrians and effects.
     */
    private void spawn () {
        // Chance to spawn a vehicle
        if (Greenfoot.getRandomNumber (60) == 0){
            int lane = Greenfoot.getRandomNumber(laneCount);
            if (!laneSpawners[lane].isTouchingVehicle()){
                // ratio of RaceCar, Car, Bus, Ambulance is 3 : 3 : 2 : 2
                int vehicleType = Greenfoot.getRandomNumber(10);
                if (vehicleType > 6) {
                    addObject(new RaceCar(laneSpawners[lane]), 0, 0);
                }
                else if (vehicleType > 3) {
                    addObject(new Car(laneSpawners[lane]), 0, 0);
                }
                else if (vehicleType > 1) {
                    addObject(new Bus(laneSpawners[lane]), 0, 0);
                }
                else {
                    addObject(new Ambulance(laneSpawners[lane]), 0, 0);
                }
            }
        }
        
        // Chance to spawn a Pedestrian
        if (Greenfoot.getRandomNumber (60) == 0){
            // random between 100 and 699, so not near edges
            int xSpawnLocation = Greenfoot.getRandomNumber(600) + 100;
            boolean spawnAtTop = Greenfoot.getRandomNumber(2) == 0 ? true : false;
            int pedestrianType = Greenfoot.getRandomNumber(4);
            // ratio of Walkman, Child, Skeleton is 1 : 1 : 2
            if (pedestrianType == 0) {
                if (spawnAtTop) {
                    addObject(new Walkman(1, isRaining), xSpawnLocation, 50);
                }
                else {
                    addObject(new Walkman(-1, isRaining), xSpawnLocation, 550);
                }
            }
            else if (pedestrianType == 1) {
                if (spawnAtTop) {
                    addObject(new Child(1, isRaining), xSpawnLocation, 50);
                }
                else {
                    addObject(new Child(-1, isRaining), xSpawnLocation, 550);
                }
            }
            else {
                if (spawnAtTop) {
                    addObject(new Skeleton(1), xSpawnLocation, 50);
                }
                else {
                    addObject(new Skeleton(-1), xSpawnLocation, 550);
                }
            }
        }
        
        // Chance to spawn a Effect
        if (Greenfoot.getRandomNumber(300) == 0 && !isInWorldEffect()) {
            // check the total number of fallen skeletons.
            ArrayList<Skeleton> skeletons = (ArrayList<Skeleton>)getObjects(Skeleton.class);
            int count = 0;
            for (Skeleton skeleton : skeletons) {
                if(!skeleton.isAwake()) {
                    count++;
                }
            }
            // spwan a Halloween if there are three or more fallen skeletons
            if (count >= 3) {
                addObject(new Halloween(600), 400, 300);
            }
            else if (Greenfoot.getRandomNumber(2) == 0) {
                addObject(new Rain(300), 400, 300);
            }
            else {
                addObject(new Moon(300), 75, 150);
            }
        }
    }

      /**
     * <p>The prepareLanes method is a static (standalone) method that takes a list of parameters about the desired roadway and then builds it.</p>
     * 
     * <p><b>Note:</b> So far, Centre-split is the only option, regardless of what values you send for that parameters.</p>
     *
     * <p>This method does three things:</p>
     * <ul>
     *  <li> Determines the Y coordinate for each lane (each lane is centered vertically around the position)</li>
     *  <li> Draws lanes onto the GreenfootImage target that is passed in at the specified / calculated positions. 
     *       (Nothing is returned, it just manipulates the object which affects the original).</li>
     *  <li> Places the VehicleSpawners (passed in via the array parameter spawners) into the World (also passed in via parameters).</li>
     * </ul>
     * 
     * <p> After this method is run, there is a visual road as well as the objects needed to spawn Vehicles. Examine the table below for an
     * in-depth description of what the roadway will look like and what each parameter/component represents.</p>
     * 
     * <pre>
     *                  <=== Start Y
     *  ||||||||||||||  <=== Top Border
     *  /------------\
     *  |            |  
     *  |      Y[0]  |  <=== Lane Position (Y) is the middle of the lane
     *  |            |
     *  \------------/
     *  [##] [##] [##| <== spacing ( where the lane lines or borders are )
     *  /------------\
     *  |            |  
     *  |      Y[1]  |
     *  |            |
     *  \------------/
     *  ||||||||||||||  <== Bottom Border
     * </pre>
     * 
     * @param world     The World that the VehicleSpawners will be added to
     * @param target    The GreenfootImage that the lanes will be drawn on, usually but not necessarily the background of the World.
     * @param spawners  An array of VehicleSpawner to be added to the World
     * @param startY    The top Y position where lanes (drawing) should start
     * @param heightPerLane The height of the desired lanes
     * @param lanes     The total number of lanes desired
     * @param spacing   The distance, in pixels, between each lane
     * @param twoWay    Should traffic flow both ways? Leave false for a one-way street (Not Yet Implemented)
     * @param centreSplit   Should the whole road be split in the middle? Or lots of parallel two-way streets? Must also be two-way street (twoWay == true) or else NO EFFECT
     * 
     */
    public static int[] prepareLanes (World world, GreenfootImage target, VehicleSpawner[] spawners, int startY, int heightPerLane, int lanes, int spacing, boolean twoWay, boolean centreSplit){
        // Declare an array to store the y values as I calculate them
        int[] lanePositions = new int[lanes];
        // Pre-calculate half of the lane height, as this will frequently be used for drawing.
        // To help make it clear, the heightOffset is the distance from the centre of the lane (it's y position)
        // to the outer edge of the lane.
        int heightOffset = heightPerLane / 2;

        // draw top border
        target.setColor (PURPLE_BORDER);
        target.fillRect (0, startY, target.getWidth(), spacing);

        // Main Loop to Calculate Positions and draw lanes
        for (int i = 0; i < lanes; i++){
            // calculate the position for the lane
            lanePositions[i] = startY + spacing + (i * (heightPerLane+spacing)) + heightOffset ;

            // draw lane
            target.setColor(GREY_STREET); 
            // the lane body
            target.fillRect (0, lanePositions[i] - heightOffset, target.getWidth(), heightPerLane);
            // the lane spacing - where the white or yellow lines will get drawn
            target.fillRect(0, lanePositions[i] + heightOffset, target.getWidth(), spacing);

            // Place spawners and draw lines depending on whether its 2 way and centre split
            if (twoWay && centreSplit){
                // first half of the lanes go rightward (no option for left-hand drive, sorry UK students .. ?)
                if ( i < lanes / 2){
                    spawners[i] = new VehicleSpawner(false, heightPerLane);
                    world.addObject(spawners[i], target.getWidth(), lanePositions[i]);
                } else { // second half of the lanes go leftward
                    spawners[i] = new VehicleSpawner(true, heightPerLane);
                    world.addObject(spawners[i], 0, lanePositions[i]);
                }

                // draw yellow lines if middle 
                if (i == lanes / 2){
                    target.setColor(BLUE_MIDLINE);
                    target.fillRect(0, lanePositions[i] - heightOffset - spacing, target.getWidth(), spacing);

                } else if (i > 0){ // draw white lines if not first lane
                    for (int j = 0; j < target.getWidth(); j += 120){
                        target.setColor (PINK_LINE);
                        target.fillRect (j, lanePositions[i] - heightOffset - spacing, 60, spacing);
                    }
                } 

            } else if (twoWay){ // not center split
                if ( i % 2 == 0){
                    spawners[i] = new VehicleSpawner(false, heightPerLane);
                    world.addObject(spawners[i], target.getWidth(), lanePositions[i]);
                } else {
                    spawners[i] = new VehicleSpawner(true, heightPerLane);
                    world.addObject(spawners[i], 0, lanePositions[i]);
                }

                // draw Grey Border if between two "Streets"
                if (i > 0){ // but not in first position
                    if (i % 2 == 0){
                        target.setColor(PURPLE_BORDER);
                        target.fillRect(0, lanePositions[i] - heightOffset - spacing, target.getWidth(), spacing);

                    } else { // draw dotted lines
                        for (int j = 0; j < target.getWidth(); j += 120){
                            target.setColor (PINK_LINE);
                            target.fillRect (j, lanePositions[i] - heightOffset - spacing, 60, spacing);
                        }
                    } 
                }
            } else { // One way traffic
                spawners[i] = new VehicleSpawner(true, heightPerLane);
                world.addObject(spawners[i], 0, lanePositions[i]);
                if (i > 0){
                    for (int j = 0; j < target.getWidth(); j += 120){
                        target.setColor (Color.WHITE);
                        target.fillRect (j, lanePositions[i] - heightOffset - spacing, 60, spacing);
                    }
                }
            }
        }
        // draws bottom border
        target.setColor (PURPLE_BORDER);
        target.fillRect (0, lanePositions[lanes-1] + heightOffset, target.getWidth(), spacing);

        return lanePositions;
    }
    
    /**
     * Start Rain effect.
     */
    public void startRaining() {
        isRaining = true;
        // stop background sound
        sound.stop();
    }
    
    /**
     * Stop Rain effect.
     */
    public void stopRaining() {
        isRaining = false;
        // start background sound
        sound.playLoop();
    }
    
    /**
     * Method to check if is in Rain effect
     * @return boolean True if is in Rain effect, False otherwise.
     */
    public boolean isRaining() {
        return isRaining;
    }
    
    /**
     * Start Halloween effect.
     */
    public void startHalloween(){
        isHalloween = true;
        sound.stop();
    }

    /**
     * Stop Halloween effect.
     */
    public void stopHalloween() {
        isHalloween = false;
        sound.playLoop();
    }
    
    /**
     * Method to check if is in Halloween effect
     * @return boolean True if is in Halloween effect, False otherwise.
     */
    public boolean isHalloween(){
        return isHalloween;
    }
    
    /**
     * Start Moon effect.
     */
    public void startMoon(){
        isMoon = true;
        setBackground(backgroundDark);
        changeLanesDirection();
        sound.stop();
    }

    /**
     * Stop Moon effect.
     */
    public void stopMoon() {
        isMoon = false;
        setBackground(background);
        sound.playLoop();
    }
    
    /**
     * Method to check if is in Moon effect
     * @return boolean True if is in Moon effect, False otherwise.
     */
    public boolean isMoon(){
        return isMoon;
    }

    /**
     * Method to check if is in any Effect
     * @return boolean True if is in any Effect, False otherwise.
     */
    private boolean isInWorldEffect() {
        return isRaining || isHalloween || isMoon;
    }
    
    /**
     * Method to change lane directions, including VehicleSpawner and existing vehicles.
     */
    public void changeLanesDirection() {
        // change direction of existing vehicle
        ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) getObjects(Vehicle.class);
        for (Vehicle v : vehicles){
            v.changeDirection();
            if (v instanceof Car) {
                Car car = (Car)v;
                car.resetIsPoliceCared();
            }
        }
        // change direction of all VehicleSpawner
        for (VehicleSpawner vs : laneSpawners) {
            vs.changeDirection();
            vs.setLocation(background.getWidth() - vs.getX(), vs.getY());
        }
    }
    
    /**
     * Method to spwan a new police car at the given VehicleSpawner
     * @param origin VehicleSpawner
     */
    public void spawnPoliceCar(VehicleSpawner origin) {
        addObject(new PoliceCar(origin), 0, 0);
    }
    
    /**
     * Get the current volume value.
     * @return int current volume
     */
    public int getVolume() {
        return volume;
    }
    
    /**
     * Update volume.
     * @param isUp True if turn volume up, False if turn volume down.
     */
    public void updateVolume(boolean isUp) {
        if (isUp) {
            volume = Math.min(volume + 20, 100);
        }
        else {
            volume = Math.max(volume - 20, 0);
        }
        
        ArrayList<iSoundMaker> sounds = (ArrayList<iSoundMaker>) getObjects(iSoundMaker.class);
        for (iSoundMaker sound : sounds) {
            sound.setVolume(volume);
        }
        // Original version, thanks Mr.Coen for helping develop the new version above. 
        /**
        ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) getObjects(Vehicle.class);
        for (Vehicle v : vehicles) {
            v.setVolume(volume);
        }
        ArrayList<Explosion> explosion = (ArrayList<Explosion>) getObjects(Explosion.class);
        for (Explosion e : explosion) {
            e.setVolume(volume);
        }
        ArrayList<Effect> effects = (ArrayList<Effect>) getObjects(Effect.class);
        for (Effect e : effects) {
            e.setVolume(volume);
        }
        */
        
        // Update the background volume and volume label.
        sound.setVolume(volume);
        volumeLable.updateLabel(volume + " %");
    }
    
    /**
     * Add a bomb to the world randomly on road.
     */
    public void setBomb() {
        addObject(new Bomb(), Greenfoot.getRandomNumber(650) + 75, Greenfoot.getRandomNumber(150) + 300);
    }
    
    /**
     * Add bombs to the world randomly on road.
     * @param num The num of bombs to be added.
     */
    public void setBomb(int num) {
        for (int i = 0; i < num; i++) {
            setBomb();
        }
    }
    
    /**
     *  Given a lane number (zero-indexed), return the y position
     *  in the centre of the lane. (doesn't factor offset, so 
     *  watch your offset, i.e. with Bus).
     *  
     *  Copied from Mr.Cohen's codes.
     *  
     *  @param lane the lane number (zero-indexed)
     *  @return int the y position of the lane's center, or -1 if invalid
     */
    public int getLaneY (int lane){
        if (lane < lanePositionsY.length){
            return lanePositionsY[lane];
        } 
        return -1;
    }
    
    /**
     * Given a y-position, return the lane number (zero-indexed).
     * Note that the y-position must be valid, and you should 
     * include the offset in your calculations before calling this method.
     * For example, if a Bus is in a lane at y=100, but is offset by -20,
     * it is actually in the lane located at y=80, so you should send
     * 80 to this method, not 100.
     * 
     * Copied from Mr.Cohen's codes.
     * 
     * @param y - the y position of the lane the Vehicle is in
     * @return int the lane number, zero-indexed
     * 
     */
    public int getLane (int y){
        for (int i = 0; i < lanePositionsY.length; i++){
            if (y == lanePositionsY[i]){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Method to check if two lanes are in the same direction.
     * @param lane1 Lane number of the first lane to be checked
     * @param lane2 Lane number of the second lane to be checked
     */
    public boolean isSameDirection(int lane1, int lane2) {
        // check if given lane numbers are valid
        if (lane1 < 0 || lane2 < 0) {
            return false;
        }
        else if (lane1 >= laneCount || lane2 >= laneCount) {
            return false;
        }
        
        // all lanes are in the same direction if single way traffic
        if (!twoWayTraffic) {
            return true;
        }
        // directions are alternative if not split at center
        else if (!splitAtCenter) {
            return false;
        }
        // two lanes are in the same direction
        else if (lane1 / (laneCount / 2) == lane2 / (laneCount / 2)) {
            return true;
        }
        // two lanes are in the opposite direction
        else {
            return false;
        }
    }
}
