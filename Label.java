import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Class Label represents a text label on the world. 
 * 
 * Modifed from my own grade 11 final project.
 * 
 * @author Yixin Cai
 * @version 2022-01-28
 */
public class Label extends Actor
{
    // Instance variables
    private String labelText;
    private int labelSize;
    private Color labelColor = new Color(10, 186,181);
    
    /**
     * Constructors for objects of class Label.
     * @param labelText The text that show on the world
     */
    // Constructor taking label text 
    public Label(String labelText) {
        this(labelText, 30);
    }
    
    // Constructor taking label text and label size
    /**
     * Constructors for objects of class Label.
     * @param labelText The text that show on the world
     * @param labelSize The size of the label
     */
    public Label(String labelText, int labelSize) {
        this.labelText = labelText;
        // Set the size of the letters.
        this.labelSize = labelSize;
        // Putt image.
        putLabelImage();
    }
    
    // Constructor taking label text, label size and label color
    /**
     * Constructors for objects of class Label.
     * @param labelText The text that show on the world
     * @param labelSize The size of the label
     * @param labelColor the color of the label
     */
    public Label(String labelText, int labelSize, Color labelColor) {
        this.labelText = labelText;
        // Set the size of the letters.
        this.labelSize = labelSize;
        // Set the clours of the letters.
        this.labelColor = labelColor;
        // Putt image.
        putLabelImage();
    }
    
    /**
     * Method to set the text to image
     */
    private void putLabelImage() {
        setImage( new GreenfootImage( this.labelText, this.labelSize, this.labelColor, new Color(0,0,0,0), new Color(0,0,0,0)));
    }
    
    /**
     * Method to update the label text
     * @param labelText The text that show on the world
     */
    public void updateLabel( String labelText) {
        this.labelText = labelText;
        putLabelImage();
    }
}
