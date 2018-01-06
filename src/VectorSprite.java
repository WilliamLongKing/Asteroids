import java.awt.*;


public class VectorSprite {
    double xposition;
    double yposition;
    double xspeed;
    double yspeed;
    double angle;
    Polygon shape,drawShape, randomshape, randomDrawShape;
    double thrust;
    boolean alive;
    int counter;
    int size;
    
    public VectorSprite()
    {
//        randomshape = new Polygon();
//        randomshape.addPoint(25, 0);
//        randomshape.addPoint(-10, 15);
//        randomshape.addPoint(-10, -15);
//        randomshape.addPoint(-25, 0);
//        
//        randomDrawShape = new Polygon();
//        randomDrawShape.addPoint(25, 10);
//        randomDrawShape.addPoint(-10, 15);
//        randomDrawShape.addPoint(-10, -15);
//        randomDrawShape.addPoint(-25, 10);
      
        
        shape = new Polygon();
        shape.addPoint(20 , 0);
        shape.addPoint(-12, 10);
        shape.addPoint(0, 0);
        shape.addPoint(-12, -10);
        
        drawShape = new Polygon();
        drawShape.addPoint(20 , 0);
        drawShape.addPoint(-12, 10);
        drawShape.addPoint(0, 0);
        drawShape.addPoint(-12, -10);
        xposition = 450;
        yposition = 300;
        //ROTATION = 0.15;
        thrust = 0.1;   
        alive = true;
    } 
    
    public void turnRight() 
    { 
        angle += 0.1;
    }
    
    public void turnLeft()
    {
        angle -= 0.1;
    }
    
    public void accelerate()
    {
        xspeed += Math.cos(angle) * thrust;
        yspeed += Math.sin(angle) * thrust;
    }
    
    public void decelerate()
    {
        xspeed -= Math.cos(angle) * thrust / 2;
        yspeed -= Math.sin(angle) * thrust / 2;
    }
      
    public void paint(Graphics g, Color c)
    {
        if(alive)
        {
        g.setColor(c);
        g.fillPolygon(drawShape);
        g.setColor(Color.BLACK);
        g.drawPolygon(drawShape);
        }
        //g.fillPolygon(randomDrawShape);
        //g.setColor(Color.GREEN);
        //g.drawPolygon(randomDrawShape);
       
    }

    public void updatePosition()
    {
          if(this.angle < 0)
         {
             this.angle = Math.PI * 2 + this.angle;
         }
         
         if(this.angle > Math.PI * 2)
         {
             this.angle = this.angle - Math.PI * 2;
         }
         
        xposition += xspeed;
        yposition += yspeed;
        wrap();
        
        //wraparound();
                
        int x,y;
        
        for (int i = 0; i < shape.npoints; i++)
        {
            x = (int)Math.round(shape.xpoints[i]*Math.cos(angle) - shape.ypoints[i]*Math.sin(angle));
            y = (int)Math.round(shape.xpoints[i]*Math.sin(angle) + shape.ypoints[i]*Math.cos(angle));
            drawShape.xpoints[i] = x;
            drawShape.ypoints[i] = y;
            //randomDrawShape.xpoints[i] = x;
            //randomDrawShape.ypoints[i] = y;
        }
        
        drawShape.invalidate();
        drawShape.translate((int)Math.round(xposition), (int)Math.round(yposition));
        
        //randomDrawShape.translate((int)Math.round(xposition), (int)Math.round(yposition));
        counter ++;
    }
    
    
    
    public void wrap()
    {
        if(xposition > 900)
        {
            xposition = 0;
        }
        
        if (xposition < 0)
        {
            xposition = 900;
        }
        
        if (yposition < 0)
        {
            yposition = 600;
        }
        
        if (yposition > 600)
        {
            yposition = 0;
        }
    }
    public double getAngle(VectorSprite target)
    {
        double x, y, a;
        x = target.xposition - this.xposition;
        y = target.yposition - this.yposition;
        if(x != 0)
        {
            a = Math.atan(y/x);
        }
        else
        {
            a = 0;
        }
        
        if(x == 0 && y > 0)
        {
            return Math.PI / 2;
        }
        
         if(x == 0 && y < 0)
        {
            return Math.PI * 3/2;
        }
         
          if(x >0 && y == 0)
        {
            return 0;
        }
          
           if(x < 0 && y == 0)
        {
            return Math.PI;
        }
           
            if(x == 0 && y == 0)
        {
            return 0;
        }
        
        if(x > 0 && y > 0)
        {
            return a;
        }
        
    
            
        
        if(x< 0 && y > 0)
        {
            return Math.PI - a;
        }
        
        if(x< 0 && y < 0)
        {
            return Math.PI + a;
        }
        
        if(x > 0 && y < 0)
        {
            return Math.PI*2 - a;
        }
        
        return 0;
    }

    }
