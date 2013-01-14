import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.*;

public class IVDirector {
    private ArrayList<IVBuilder> builderList;
    private BufferedImage canvas;
    

    public IVDirector(int width, int height){
        builderList = new ArrayList<IVBuilder>();
        canvas      = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    public void addBuilder(IVBuilder builder){
        this.builderList.add(builder);
    }

    public BufferedImage build(){
        Graphics g = this.canvas.getGraphics();
        for (IVBuilder build : this.builderList) {
            build.draw(g);
        }
        g.dispose();
        return this.canvas;
    }

}
