import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
public class Bullet extends VectorSprite 
{
    int life;
   public  Bullet (double x, double y, double a, int s, int speed, int l)
   {
       size = s;
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
       angle = a;
       thrust = speed;
       xspeed = Math.cos(angle) * thrust;
       yspeed = Math.sin(angle) * thrust;
   }
   
   public  Bullet (double x, double y)
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
       life = 200;
        
       xposition = x;
       yposition = y;
       angle = Math.random()*Math.PI*2;
       thrust = 10;
       xspeed = Math.cos(angle) * thrust;
       yspeed = Math.sin(angle) * thrust;
   }
}
