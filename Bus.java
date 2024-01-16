import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Bus can pick maximum 4 pedestrians, it would stop for one second to connect 
 * with pedestrian, is not allowed to change lane.  
 * 
 * @author Yixin Cai
 * @version 2022-10-24
 */
public class Bus extends Vehicle
{
    // use static to avoid pre-loading lots of gif images during Bus object initialization
    // otherwise, there would be noticeable delay while spawning a new bus
    private static GifImage[][] BUS_GIF_IMAGES = {
        new GifImage[] {
            new GifImage("bus00Left.gif"),
            new GifImage("bus00Right.gif")
        },
        new GifImage[] {
            new GifImage("bus01Left.gif"),
            new GifImage("bus01Right.gif")
        },
        new GifImage[] {
            new GifImage("bus02Left.gif"),
            new GifImage("bus02Right.gif")
        },
        new GifImage[] {
            new GifImage("bus03Left.gif"),
            new GifImage("bus03Right.gif")
        },
        new GifImage[] {
            new GifImage("bus04Left.gif"),
            new GifImage("bus04Right.gif")
        }
    };
    
    public static int BUS_DELAY = 60;
    public static int MAX_PEDS = 4;

    // ArrayList to store picked pedestrians
    private ArrayList<Pedestrian> pedList;
    
    /**
     * The constructor of Bus
     * @param origin A VehicleSpawner
     */
    public Bus(VehicleSpawner origin){
        super (origin); // call the superclass' constructor first
        
        //Set up values for Bus
        maxSpeed = 1.5 + ((Math.random() * 10)/5);
        speed = maxSpeed;
        // because the Bus graphic is tall, offset it a up (this may result in some collision check issues)
        yOffset = 8;
        delay = 0;
        pedList = new ArrayList<Pedestrian>();
        updateGifImage();
    }

    /**
     * Act - do whatever the Bus wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        super.act();
    }

    /**
     * Method to check if it hits pedestrian. Picks up the pedestrian and stop for 1 second, 
     * if the pedestrian is at any point on the front and right side of the bus
     * @return boolean True if it hits pedestrian, False otherwise
     */
    public boolean checkHitPedestrian() {
        boolean picked = false;
        
        int height = getImage().getHeight();
        int width = getImage().getWidth();
        
        int dy = (height/2 + 1) * direction;
        int dx = (width/2 + 1) * direction;
        // check right side
        for (int x = -(width / 2); x < width / 2; x+=5) {
            picked = checkHitPedestrianAtOffset(x, dy) || picked;
        }
        
        // check front
        for (int y = -(height / 2); y < height / 2; y+=5) {
            picked = checkHitPedestrianAtOffset(dx, y) || picked;
        }
        
        if (picked) {
            delay = BUS_DELAY;
        }
        
        return false;
    }
    
    /**
     * Method to check if it hits pedestrian at off set, pick the pedestrian if the bus is not full
     * @return True if it hits pedestrian at off set, False otherwise
     */
    private boolean checkHitPedestrianAtOffset(int x, int y) {
        boolean picked = false;
        ArrayList<Pedestrian> peds = (ArrayList<Pedestrian>)getObjectsAtOffset(x, y, Pedestrian.class);
        for (Pedestrian ped : peds) {
            // skip pedestrians that are kncked down or skeletons
            if (!ped.isAwake() || (ped instanceof Skeleton)) {
                continue;
            }
            // pick if bus is not full
            if (pedList.size() < MAX_PEDS) {
                pedList.add(ped);
                updateGifImage();
                getWorld().removeObject(ped);
                picked = true;
            }
            else {
                return picked;
            }
        }
        return picked;
    }
    
    /**
     * Update the gif image based on number of picked pedestrians
     */
    private void updateGifImage() {
        int size = pedList.size();
        gifImages = BUS_GIF_IMAGES[size];
        gifImage = gifImages[direction == 1 ? 1 : 0];
    }
}
