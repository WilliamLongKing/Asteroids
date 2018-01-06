import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
public class Debris extends VectorSprite
{
     public  Debris (double x, double y)
   {
       size = 1;
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
       angle = Math.random()*Math.PI*2;
       thrust = 5;
       xspeed = Math.cos(angle) * thrust;
       yspeed = Math.sin(angle) * thrust;
   }


}
