import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
public class Powerup extends VectorSprite
{
    public Powerup(double x, double y)
    {
          size = 4;
          shape = new Polygon();
          shape.addPoint(2* size, 2* size);
          shape.addPoint(2* size, -2* size);
          shape.addPoint(-2* size, -2* size);
          shape.addPoint(-2* size, 2* size);
          
          drawShape = new Polygon();
          drawShape.addPoint(2* size, 2* size);
          drawShape.addPoint(2* size, -2* size);
          drawShape.addPoint(-2* size, -2* size);
          drawShape.addPoint(-2* size, 2* size);
          
          xposition = x;
          yposition = y;
          
    }
}
