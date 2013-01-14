import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

interface IVBuilder {
    void draw(Graphics g);
}

class PictureBuilder implements IVBuilder {
    private BufferedImage bi1;
    public PictureBuilder() throws IOException{
        this.bi1 = javax.imageio.ImageIO.read(new File("../img/p1.jpg"));
    } 

    public void draw(Graphics g){
        try{
            g.drawImage(bi1, 0, 0, null);
        }
        finally{
        }
    }
    
} 
