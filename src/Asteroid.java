import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
public class Asteroid extends VectorSprite 
{
    
    int life;
   public  Asteroid (double x, double y, double a, int s, int t)
   {
       size = s;
       
       shape = new Polygon();
       shape.addPoint(-2 * size, 5 * size);
       shape.addPoint(3* size, 4* size);
       shape.addPoint(4* size, -2* size);
       shape.addPoint(-1* size, -4* size);
       shape.addPoint(-6* size, -2* size);
       shape.addPoint(-4* size, 2* size);
       shape.addPoint(-5* size, 3* size);
       
       drawShape = new Polygon();
       drawShape.addPoint(-2* size, 5* size);
       drawShape.addPoint(3* size, 4* size);
       drawShape.addPoint(4* size, -2* size);
       drawShape.addPoint(-1* size, -4* size);
       drawShape.addPoint(-6* size, -2* size);
       drawShape.addPoint(-4* size, 2* size);
       drawShape.addPoint(-5* size, 3* size);
       
       life = 200;
        
       xposition = x;
       yposition = y;
       angle = a;
       thrust = t;
       xspeed = Math.cos(angle) * thrust;
       yspeed = Math.sin(angle) * thrust;
   }
}
