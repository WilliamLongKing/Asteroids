import java.awt.*;
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.util.ArrayList;
import java.applet.AudioClip;
//

public class Asteroids extends Applet implements KeyListener, ActionListener
{
    AudioClip laser, shipHit, asteroidHit, thruster;
    Image offscreen;    
    Graphics offg;
    VectorSprite randomshape;
    Image bob;
    VectorSprite ship;
    Powerup power;
    Powerup power1;
    Powerup power2;
    Powerup power3;
    Boss boss;
    ArrayList<Bullet> bossAttackList;
    ArrayList<Bullet> bulletList;
    ArrayList<Asteroid> asteroidList;
    ArrayList<Debris> debrisList;
    Timer timer;
    int bossLife;
    int score;
    int level;
    int invincible;
    int explosionDensity;
    int numBullets;
    int bulletSpeed;
    int bulletLife;
    int bulletSize;
    int firingRate;
    int lives;
    
    double spread;
    boolean upKey, leftKey, rightKey, downKey, spaceKey, shiftKey, wKey, sKey, aKey, dKey, ctrlKey, qKey, eKey;

    public void init() {
         this.setSize(900,600);
         this.addKeyListener(this);
         offscreen =createImage(this.getWidth(),this.getHeight());
         offg = offscreen.getGraphics();
         bob = getImage(getCodeBase(),"Bob.jpg");
         ship = new VectorSprite();
         boss = new Boss (450, 300);
         power = new  Powerup(150, 100);
         power1 = new Powerup(600, 400);
         power2 = new Powerup(750, 500);
         power3 = new Powerup(300, 200);
//         shield = new Shield(ship.xposition, ship.yposition);
         randomshape = new VectorSprite();
         bulletList = new ArrayList();
         asteroidList = new ArrayList();
         debrisList = new ArrayList();
         bossAttackList = new ArrayList();
         timer = new Timer(20,this);
         timer.start();
         level = 0;
         bossLife = 1;
         explosionDensity = 30;
         numBullets = 3;
         bulletSpeed = 10;
         bulletLife = 100;
         bulletSize = 1;
         firingRate = 100;
         score = 0;
         spread = 0.1;
         lives = 3;
         startLevel();
         laser = getAudioClip(getCodeBase(), "laser80.wav");
         thruster = getAudioClip(getCodeBase(), "thruster.wav");
         shipHit = getAudioClip(getCodeBase(), "explode1.wav");
         asteroidHit = getAudioClip(getCodeBase(), "explode0.wav");
         
    }
    
     public void paint(Graphics g){
            offg.setColor(Color.black);
            offg.fillRect(0, 0, 900, 600);
            //offg.drawImage(bg.image.pic,bg.position.x,bg.position.y,bg.width,bg.height, this);
            //offg.drawImage(Bob, 500, 300, this);
            if(invincible  % 2==0)
            {
                ship.paint(offg, Color.CYAN);    
            }
            power.paint(offg, Color.PINK);
            power1.paint(offg, Color.GREEN);
            power2.paint(offg, Color.CYAN);
            power3.paint(offg, Color.YELLOW);
            boss.paint(offg, Color.orange);
            
            offg.setColor(Color.red);
            offg.drawString("Score : "+ score,830, 590);
             offg.drawString("Lives : "+ lives,800, 550);
             if(lives <= -1)
             {
                 offg.drawString("Game Over", 450, 600);
             }


//            
            randomshape.paint(offg, Color.MAGENTA);
            for(int i = 0; i < debrisList.size(); i++)
            {
                debrisList.get(i).paint(offg, Color.orange);
            }
            for(int i = 0; i < bulletList.size(); i++)
            {
                bulletList.get(i).paint(offg, Color.blue);
            }
            for(int i = 0; i < asteroidList.size(); i++)
            {
               asteroidList.get(i).paint(offg, Color.red);
            }
            
            for(int i = 0; i < bossAttackList.size(); i++)
            {
                bossAttackList.get(i).paint(offg, Color.magenta);
            }
            g.drawImage(offscreen,0,0, this);
            repaint();
             
     }
     //This is the Timer Tick of Java 
     public void actionPerformed(ActionEvent e)
    {
        checkKeys();
        
        randomshape.updatePosition();
        
        updateShip();
        updateBullets();
        updateAsteroids();
        updateDebris();
        updatePower();
        updatePower1();
        updatePower2();
        updatePower3();
        updateBoss();
        boss.updatePosition();
        updateBossBullets();
        makeBossAttack();
        checkBossCollision();
        checkBossBulletCollision();
        checkBossPlayerCollision();
        
     
    }
     
     public void updateBoss()
     {
         double rightDistance;
         double leftDistance;
         double target = boss.getAngle(ship);
         double us = boss.angle;
             
         if (target < us)
         {
             rightDistance = target-us;
         }
         else
         {
             rightDistance = us + (Math.PI*2 - target);
            
         }
         if (us < target)
         {
             leftDistance = target-us;
            
         }
         else
         {
             leftDistance = target + (Math.PI*2 - us);
         }
         
         
         
         
        
         if(!(us >  target - 0.5 && us < target + 0.5))
         {
             if (leftDistance < rightDistance)
             {
                 boss.angle += 0.01;
             }
             else
             {
                 boss.angle -= 0.01;
             }
             
         }
     }
     
     public void updatePower3()
     {
         power3.updatePosition();
         if(collision(ship, power3))
         {
             power3.alive = false;
             asteroidList.clear();
         }
     }
     
     public void updatePower2()
     {
         power2.updatePosition();
         if(collision(ship, power2))
         {
             power2.alive = false;
             invincible += 1999999999;
              if(invincible  % 2==0)
            {
                ship.paint(offg, Color.CYAN);    
            }
         }
     }
     //
     public void updatePower1()
     {
         power1.updatePosition();
         if(collision(ship, power1))
         {
             power1.alive = false;
             numBullets =250;
         }
     }
     
     public void updatePower()
     {
         power.updatePosition();
         if(collision(ship, power))
         {
             power.alive = false;
             bulletSize = 10;
         }
     }
     
    public void updateShip()
    {
        ship.updatePosition();
        
        if (invincible==0)
        {
            checkPlayerCollision();
            checkShipCollision();
        }
        else
        {
            invincible--;
        }
        
        if(ship.xspeed != 0 || ship.yspeed != 0)
       //                    ^Or  (||)
        {
            //friction();
        }
    }
    
    public void updateAsteroids()
    {
        checkAsteroidCollision();
        
        for(int i = 0; i < asteroidList.size(); i++)
            {
                    asteroidList.get(i).updatePosition();
                    if(asteroidList.get(i).alive==false)
                    {
                        asteroidList.remove(i);
                    }
//                    if(asteroidList.get(i).counter > asteroidList.get(i).life)
//                    {
//                    asteroidList.remove(i);   
//                    }
            }
     
        if(asteroidList.isEmpty())
        {
            startLevel();
        }
    }
    
    public void updateBullets()
    {
        for(int i = 0; i < bulletList.size(); i++)
            {
                    bulletList.get(i).updatePosition();
                    if(bulletList.get(i).counter > bulletList.get(i).life || bulletList.get(i).alive == false)
                    {
                    bulletList.remove(i);   
                    }
            }
        
         
    }
    public void updateBossBullets()
    {
        for(int i = 0; i < bossAttackList.size(); i++)
            {
                    bossAttackList.get(i).updatePosition();
                    if(bossAttackList.get(i).counter > bossAttackList.get(i).life || bossAttackList.get(i).alive == false)
                    {
                    bossAttackList.remove(i);   
                    }
            }
        
         
    }
    
    public void updateDebris()
    {
          for(int i = 0; i < debrisList.size(); i++)
            {
                    debrisList.get(i).updatePosition();
                    if(debrisList.get(i).counter > 50 || debrisList.get(i).alive == false)
                    {
                    debrisList.remove(i);   
                    }
            }
        
    }
     
    public void friction()
    {
        VectorSprite nextMove;
        
        nextMove = ship;
        nextMove.xposition-=ship.xspeed;
        nextMove.yposition-=ship.yspeed;
        System.out.println (""+ship.getAngle(nextMove));
        ship.xspeed += Math.cos(ship.getAngle(nextMove)) * .1;
        ship.yspeed += Math.sin(ship.getAngle(nextMove)) * .1;
    }
     
     public void start()
    {
        //timer.start();
    }
    
    
    public void stop()
    {
        //timer.stop();
    }
    
     public void update(Graphics g)
     {
        paint(g);
     }  
     
     public void keyPressed(KeyEvent e)
    {
         if (e.getKeyCode() == KeyEvent.VK_Q)
        {
            qKey = true;
        }
         if (e.getKeyCode() == KeyEvent.VK_E)
        {
            eKey = true;
        }
          if (e.getKeyCode() == KeyEvent.VK_W)
        {
            wKey = true;
        }
          
           if (e.getKeyCode() == KeyEvent.VK_S)
        {
            sKey = true;
        }
            if (e.getKeyCode() == KeyEvent.VK_A)
        {
            aKey = true;
        }
            if (e.getKeyCode() == KeyEvent.VK_D)
        {
            dKey = true;
        }
        
        if (e.getKeyCode() == KeyEvent.VK_SHIFT)
        {
            shiftKey = true;
        }
        
           if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            downKey = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            rightKey = true;
        }
        
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            leftKey = true;
        }
        
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            upKey = true;
            thruster.loop();
        }
        
         if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            downKey = true;
        }
        
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            spaceKey = true;
        }
       if (e.getKeyCode() == KeyEvent.VK_Z)
        {
            if(ship.alive == true)
            {
            ctrlKey = false;
            
            }
            else
            {
                 if( lives >= 1)
                 {
            ctrlKey = true;
            ship.xposition = 400;
            ship.yposition = 400;
            invincible = 200;
            ship.xspeed = 0;
            ship.yspeed = 0;
            lives -= 1;
            }
            }
        }
    }
    
    
    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            rightKey = false;
        }
        
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            leftKey = false;
        }
        
        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            upKey = false;
            thruster.stop();
        }
            
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            downKey = false;
        }
        
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            spaceKey = false;
        }
        
         if (e.getKeyCode() == KeyEvent.VK_SHIFT)
        {
            shiftKey = false;
        }
          if (e.getKeyCode() == KeyEvent.VK_W)
        {
            wKey = false;
        }
          
           if (e.getKeyCode() == KeyEvent.VK_S)
        {
            sKey = false;
        }
            if (e.getKeyCode() == KeyEvent.VK_A)
        {
            aKey = false;
        }
            if (e.getKeyCode() == KeyEvent.VK_D)
        {
            dKey = false;
        }
            if (e.getKeyCode()== KeyEvent.VK_Z)
            {
                     ctrlKey = false;
            }
             if (e.getKeyCode() == KeyEvent.VK_Q)
        {
            qKey = false;
        }
         if (e.getKeyCode() == KeyEvent.VK_E)
        {
            eKey = false;
        }
    }
    
    
    public void keyTyped(KeyEvent e) 
    {
        
    }
    
    public void checkKeys()
    {
        if(leftKey)
        {
            ship.turnLeft();
        }
        if(rightKey)
        {
            ship.turnRight();
        }
        if(upKey)
        {
            ship.accelerate();
            
        }
        if(downKey)
        {
            ship.decelerate();
        }
        if (spaceKey)
        {
            fireBullet();
            
        }
        if (shiftKey)
        {
           
            fireBulletBackwards();
        }
        if(wKey)
        {
            randomshape.accelerate();
        }
        if(sKey)
        {
            randomshape.decelerate();
        }
        if(aKey)
        {
            randomshape.turnLeft();
        }
        if(dKey)
        {
            randomshape.turnRight();
        }
        if(ctrlKey)
        {
            ship.alive = true;
            invincible = 200;
        }
        if(qKey)
        {
            fireBulletSecondShip();
        }
        if(eKey)
        {
            fireBulletBackwardsSecondShip();
            //
        }
    }
    public void makeBullet()
    {
        for(int i = 1;i < numBullets/2+1;i++)
        {
            bulletList.add(new Bullet(ship.xposition, ship.yposition, ship.angle+i*spread, bulletSize, bulletSpeed, bulletLife));
        }
        for(int i = 1;i < numBullets/2+1;i++)
        {
            bulletList.add(new Bullet(ship.xposition, ship.yposition, ship.angle-i*spread, bulletSize, bulletSpeed, bulletLife));
        }
        if(numBullets%2 == 1)
        {
            bulletList.add(new Bullet(ship.xposition, ship.yposition, ship.angle, bulletSize, bulletSpeed, bulletLife));
        }
     }
    // 
     public void makeBossAttack()
    {
        if(boss.alive == true)
        {
       if(boss.counter > 100)
       {
           boss.counter = 0;
           bossAttackList.add(new Bullet(boss.xposition, boss.yposition, boss.angle - 90,  10, 5, 100));
       }
        }
    }
     
        
    public void fireBullet()
    {
        if(ship.counter > firingRate)
        {
            if(lives  >= -1)
            {
        makeBullet();
        laser.play();
        ship.counter = 0;
            }
        }
        
    }
    
     public void fireBulletSecondShip()
    {
        if(randomshape.counter > firingRate)
        {
        bulletList.add(new Bullet(randomshape.xposition, randomshape.yposition, randomshape.angle, bulletSize, bulletSpeed, bulletLife));
        randomshape.counter = 0;
        }
        
    }
    
     public void fireBulletBackwards()
     {
              if(lives  >= -1)
    {
        bulletList.add(new Bullet(ship.xposition, ship.yposition, ship.angle - 135,  bulletSize, bulletSpeed, bulletLife));
        
    }
     }
     
     public void fireBulletBackwardsSecondShip()
     {
         bulletList.add(new Bullet(randomshape.xposition, randomshape.yposition, randomshape.angle  - 135,  bulletSize, bulletSpeed, bulletLife));
     }
     
     public void startLevel()
     {
         bossLife = level * 10 + 30;
         boss.alive = true;
         ship.xposition = 250;
         ship.yposition = 400;
         ship.xspeed = 0;
         ship.yspeed = 0;
         randomshape.xposition = 650;
         randomshape.yposition = 400;
         randomshape.xspeed = 0;
         randomshape.yspeed = 0;
         invincible = 200;
         numBullets = 5;
         bulletSize = 1;
         power.alive = true;
         power1.alive = true;
         power2.alive = true;
         power3.alive = true;
         level += 1;
         asteroidList.clear();
         for(int i = 0; i < level; i++)
         {
             asteroidList.add(new Asteroid(Math.random()* 900,Math.random()* 600, Math.random()* Math.PI * 2, 9, level / 3 + 1));
         }
     }
     
    public boolean collision(VectorSprite thing1, VectorSprite thing2)
    {
        int x, y;
        
        for (int i = 0; i < thing1.drawShape.npoints; i++)
        {
            x = thing1.drawShape.xpoints[i];
            y = thing1.drawShape.ypoints[i];

            if ( thing2.drawShape.contains(x, y) )
            {
                return true;
            }
        }

        for (int i = 0; i < thing2.drawShape.npoints; i++)
        {
            x = thing2.drawShape.xpoints[i];
            y = thing2.drawShape.ypoints[i];

            if ( thing1.drawShape.contains(x, y) )
            {
                return true;
            }
        }
        
        return false;
    }

     
     public void checkPlayerCollision()
             {
                 for(int i = 0; i < asteroidList.size(); i++)
                 {
                     if(collision(ship, asteroidList.get(i)) && ship.alive == true)
                     {
                         ship.alive = false;
                         shipHit.play();
                         score -= 5;
                         for(int j = 0; j < explosionDensity; j++)
                         {
                             debrisList.add(new Debris(ship.xposition, ship.yposition));
                         }
                     }
                 }
             }
     
     public void checkAsteroidCollision()
             {
                 for(int i = 0; i < asteroidList.size(); i++)
                 {
                    for(int j = 0; j < bulletList.size(); j++)
                    {
                     if(collision(bulletList.get(j), asteroidList.get(i)))
                     {
                         asteroidList.get(i).alive = false;
                         bulletList.get(j).alive = false;
                         score += 5;
                         asteroidHit.play();
                         if(asteroidList.get(i).size > 1)
                         {
                            asteroidList.add(new Asteroid(asteroidList.get(i).xposition, asteroidList.get(i).yposition, Math.random() * Math.PI * 2, asteroidList.get(i).size /2, level / 3 + 1));
                             asteroidList.add(new Asteroid(asteroidList.get(i).xposition, asteroidList.get(i).yposition, Math.random() * Math.PI * 2, asteroidList.get(i).size /2, level / 3 + 1));
                         }
                          for(int c = 0; c < explosionDensity; c++)
                         {
                             debrisList.add(new Debris(asteroidList.get(i).xposition, asteroidList.get(i).yposition));
                         }
                          asteroidList.remove(i);
                         
                          return;
                     }
                    }
                 }
             }
     
     public void checkShipCollision()
             
     {
         if(collision(ship, randomshape)&& ship.alive == true)
         {
             ship.alive = false;
             score -= 5;
             shipHit.play();
         }
     }
     
     public void checkAsteroidShipCollision()
     {
          for(int i = 0; i < asteroidList.size(); i++)
        {
         if(collision(asteroidList.get(i), ship)&& ship.alive == true)
                 {
                      asteroidList.get(i).alive = false;
                      score -= 10;
                      shipHit.play();
                 }
        }
     }
     
     public void checkBossCollision()
     {
          for(int i = 0; i < bossAttackList.size(); i++)
        {
         if(collision(bossAttackList.get(i), ship) && invincible == 0 && ship.alive == true)
                 {
                     if(boss.alive == true)
                     {
                        ship.alive = false;
                        score -= 20;
                        shipHit.play();
                     }
                 for(int j = 0; j < explosionDensity; j++)
                {
                    debrisList.add(new Debris(ship.xposition, ship.yposition));
                }
           }
        }
     }
     
     public void checkBossPlayerCollision()
     {
         if(collision(boss, ship )&& invincible == 0 && ship.alive == true)
             if(boss.alive == true)
             {
         {
             ship.alive = false;
             score -= 50;
             shipHit.play();
         }
              for(int j = 0; j < explosionDensity; j++)
                {
                    debrisList.add(new Debris(ship.xposition, ship.yposition));
                }
         }
     }
     
     public void checkBossBulletCollision()
        {
             for(int i = 0; i < bulletList.size(); i++)
             {
         if(collision(boss,bulletList.get(i)) && boss.alive == true)
            {
             bossLife -= 1;
             if(bossLife <= 0)
             {
                 boss.alive = false;
                 asteroidHit.play();
                 score += 100;
              }
             }
             
         }
       }
     }
   
     

   