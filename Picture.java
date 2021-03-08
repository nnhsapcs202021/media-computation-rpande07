import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments 
     */
    public Picture ()
    {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor 
         */
        super(); 

    }
    /**
     * Constructor that takes a file name and creates the picture 
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName)
    {
        // let the parent class handle this fileName
        super(fileName);
    }

    /**
     * Constructor that takes the width and height
     * @param height the height of the desired picture
     * @param width the width of the desired picture
     */
    public Picture(int height, int width)
    {
        // let the parent class handle this width and height
        super(width,height);
    }

    /**
     * Constructor that takes a picture and creates a 
     * copy of that picture
     * @param copyPicture the picture to copy
     */
    public Picture(Picture copyPicture)
    {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image)
    {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////

    /**
     * Method to return a string with information about this picture.
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString()
    {
        String output = "Picture, filename " + getFileName() + 
            " height " + getHeight() 
            + " width " + getWidth();
        return output;

    }

    /** Method to set the blue to 0 */
    public void zeroBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setBlue(0);
            }
        }
    }

    /** Method that mirrors the picture around a 
     * vertical mirror in the center of the picture
     * from left to right */
    public void mirrorVertical()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        } 
    }

    /** Method that mirrors the picture around a 
     * vertical mirror in the center of the picture
     * from right to left */
    public void mirrorVerticalRightToLeft()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                leftPixel.setColor(rightPixel.getColor());
            }
        } 
    }

    /** Method that mirrors the picture around a 
     * horizontal mirror in the center of the picture
     * from top to bottom */
    public void mirrorHorizontal()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel botPixel = null;
        int width = pixels[0].length;
        for (int col = 0; col < width; col++)
        {
            for (int row = 0; row < pixels.length/2; row++)
            {
                topPixel = pixels[row][col];
                botPixel = pixels[pixels.length - 1 - row][col];
                botPixel.setColor(topPixel.getColor());
            }
        } 
    }

    /** Method that mirrors the picture around a 
     * horizontal mirror in the center of the picture
     * from bottom to top */
    public void mirrorHorizontalBotToTop()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel botPixel = null;
        int width = pixels[0].length;
        for (int col = 0; col < width; col++)
        {
            for (int row = 0; row < pixels.length/2; row++)
            {
                topPixel = pixels[row][col];
                botPixel = pixels[pixels.length - 1 - row][col];
                topPixel.setColor(botPixel.getColor());
            }
        } 
    }

    /** Mirror just part of a picture of a temple */
    public void mirrorTemple()
    {
        int mirrorPoint = 276;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();

        // loop through the rows
        for (int row = 27; row < 97; row++)
        {
            // loop from 13 to just before the mirror point
            for (int col = 13; col < mirrorPoint; col++)
            {

                leftPixel = pixels[row][col];      
                rightPixel = pixels[row]                       
                [mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
                count++;
            }
        }
        System.out.println(count);
    }

    /** Method to show large changes in color 
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetection(int edgeDist)
    {
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor = null;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; 
            col < pixels[0].length-1; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][col+1];
                rightColor = rightPixel.getColor();
                if (leftPixel.colorDistance(rightColor) > 
                edgeDist)
                    leftPixel.setColor(Color.BLACK);
                else
                    leftPixel.setColor(Color.WHITE);
            }
        }
    }
    
    /** Method to mirror snowman's arms for snowman picture
     */
    public void mirrorArms()
    {
        int mirrorPoint = 195;
        Pixel topPixel = null;
        Pixel botPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();

        // loop through the rows
        for (int col = 100; col < 300; col++)
        {
            // loop from 13 to just before the mirror point
            for (int row = 165; row < 200; row++)
            {

                topPixel = pixels[row][col];      
                botPixel = pixels[mirrorPoint - row + mirrorPoint][col];
                botPixel.setColor(topPixel.getColor());
            }
        }

    }
    
    /** Method to copy a picture onto a new location 
     * @param fromPic picture being copied
     * @param startRow start of the row that will be pasted onto image
     * @param startCol start of the column that will be pasted onto image
     */
    public void copy(Picture fromPic,
    int startRow, int startCol)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow;
        fromRow < fromPixels.length &&
        toRow < toPixels.length;
        fromRow++, toRow++)
        {
            for (int fromCol = 0, toCol = startCol;
            fromCol < fromPixels[0].length &&
            toCol < toPixels[0].length;
            fromCol++, toCol++)
            {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }
    }
    
    // copies the region defined by rows 10-100 and columns 20-200 of picture1 into picture2
// such that the upper-left corner of the copied picture starts at row 30 and column 40
//(and, therefore, extends to row 120 and column 220).
// picture2.cropAndCopy( picture1, 10, 100, 20, 200, 30, 40 );
    
    /** Method to show large changes in color 
     * @param sourcePicture start picture
     * @param startSourceRow row to start copying from
     * @param endSourceRow row to end copying from
     * @param startSourceCol column to start copying from
     * @param endSourceCol column to end copying from
     * @param startDestRow row to start pasting onto 
     * @param startDestCol column to start copying onto
     */
    public void cropAndCopy( Picture sourcePicture, int startSourceRow, int endSourceRow,
    int startSourceCol, int endSourceCol, int startDestRow, int startDestCol ){
        Pixel[][] toPixels = this.getPixels2D(); // Destination
        Pixel[][] fromPixels = sourcePicture.getPixels2D(); // Source
        Pixel fromPixel = null;
        Pixel toPixel = null;
        for (int col = startSourceCol; col < endSourceCol; col++)
        {
            for (int row = startSourceRow; row < endSourceRow; row++)
            {
                fromPixel = fromPixels[row][col];
                toPixel = toPixels[startDestRow + (row-startSourceRow)][startDestCol+(col-startSourceCol)];
                
                toPixel.setColor(fromPixel.getColor());
               
            }
        }
        
    }
    
    /* Main method for testing - each class in Java can have a main 
     * method 
     */
    public static void main(String[] args) 
    {
        Picture cat = new Picture("cat.jpg");
        Picture snowman = new Picture("snowman.jpg");
        Picture canvas = new Picture(500, 500);
        cat.explore();
        canvas.explore();
        //cat.copy(snowman, 0, 500);
        //cat.explore();
        //cat.zeroBlue();
        //cat.explore();
        
        //cropAndCopy( Picture sourcePicture, int startSourceRow, int endSourceRow, 
        // int startSourceCol, int endSourceCol, int startDestRow, int startDestCol )
        canvas.cropAndCopy(cat, 150, 400, 300, 550, 0,0);
        canvas.cropAndCopy(cat, 150, 400, 300, 550, 0,250);
        canvas.cropAndCopy(cat, 150, 400, 300, 550, 250,0);
        canvas.cropAndCopy(cat, 150, 400, 300, 550, 250,250);
        canvas.explore();
    }

} // this } is the end of class Picture, put all new methods before this
