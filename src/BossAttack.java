import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
public class BossAttack extends VectorSprite
{
    int life;
     public  BossAttack (double x, double y, int speed, int l)
   {
       
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
       life = l;
        
       xposition = x;
       yposition = y;
       thrust = speed;
       xspeed = Math.cos(angle) * thrust;
       yspeed = Math.sin(angle) * thrust;
   }
}
