/**
 * Thanks Mr.Cohen for helping me reduce redundancy!
 * 
 * @author Jordan Cohen, Yixin Cai
 * @version 2022-10-24
 */
public interface iSoundMaker{
    /**
     * Set volume
     * @param volume The current volume
     */
    public void setVolume(int volume);
    
    /**
     * Play sound
     */
    public void playSound();
    
    /**
     * Stop sound
     */
    public void stopSound();
}
 
