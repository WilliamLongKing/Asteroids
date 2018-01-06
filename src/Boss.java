import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
public class Boss extends VectorSprite
{
    public Boss (double x, double y)
    {
        size = 3;
        shape = new Polygon();
        shape.addPoint(0 * size, 10 * size);
        shape.addPoint(8 * size, 7 * size);
        shape.addPoint(16 * size, -4 * size);
        shape.addPoint(8 * size, -4 * size);
        shape.addPoint(0 * size, -10 * size);
        shape.addPoint(-8 * size, -4 * size);
        shape.addPoint(-16 * size, -4 * size);
        shape.addPoint(-8 * size, 7 * size);
        
        
        
        
       
        
        drawShape = new Polygon();
        drawShape.addPoint(0 * size, 10 * size);
        drawShape.addPoint(8 * size, 7 * size);
        drawShape.addPoint(16 * size, -4 * size);
        drawShape.addPoint(8 * size, -4 * size);
        drawShape.addPoint(0 * size, -10 * size);
        drawShape.addPoint(-8 * size, -4 * size);
        drawShape.addPoint(-16 * size, -4 * size);
        drawShape.addPoint(-8 * size, 7 * size);
        
        xposition = x;
        yposition = y;
        
        
                
        
    }
}
