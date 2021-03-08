
/**
 * Four picture collage with filters and transformations applied
 *
 * @rpande
 * @03/07/21
 */
public class Collage
{
    public static void main(String[] args) 
    {
        Picture album = new Picture("album.jpg");
        Picture snowman = new Picture("snowman.jpg");
        Picture canvas = new Picture(500, 500);
        
        album.explore();
        canvas.explore();
        
        canvas.cropAndCopy(album, 0, 250, 0, 250, 0,0);
        
        album.mirrorHorizontalBotToTop();
        canvas.cropAndCopy(album, 0, 250, 0, 250, 0,250);
        
        album.mirrorVertical();
        canvas.cropAndCopy(album, 0, 250, 0, 250, 250,0);
        
        album.zeroBlue();
        canvas.cropAndCopy(album, 0, 250, 0, 250, 250,250);
        canvas.explore();
        
        canvas.write("RiaPande.jpg");
    }
}
