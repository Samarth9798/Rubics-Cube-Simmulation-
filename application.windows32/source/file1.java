import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import peasy.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class file1 extends PApplet {



PeasyCam cam;

int dim = 3;
Box[] cube = new Box[dim*dim*dim];

String allMoves[] = {"u", "d", "l", "r", "f", "b", "x", "y", "z"};
String sequence = "";
int counter = 0;
String solve = "";
int solveCounter = 0;
String temp = "";
char KeyPress = '\0';

Move m;
char turn = '$';

public void setup()
{  
    
    
    cam = new PeasyCam(this, 400);
    
    cursor(HAND);
    
    int index = 0;
    
    for(int x = -1; x <= 1; x++)
    {
        for(int y = -1; y <= 1; y++)
        {
            for(int z = -1; z <= 1; z++)
            {
                PMatrix3D matrix = new PMatrix3D();
                
                matrix.translate(x,y,z);
                
                cube[index++] = new Box(matrix, x, y, z);
            }
        }
    }
    
    m = new Move(0, 0, 0, 0, 0);
}

public void turnXX(int index, int dir)
{
    for(int i = 0; i < cube.length; i++)
    {
        Box qb = cube[i];
        
        if(qb.x == index)
        {
            PMatrix2D matrix = new PMatrix2D();
            matrix.rotate(dir * HALF_PI);
            matrix.translate(qb.y, qb.z);

            qb.update(qb.x, round(matrix.m02), round(matrix.m12));
            
            qb.turnFaceX(dir);
        }
    }
}

public void turnX(int index, int dir)
{
    for(int i = 0; i < cube.length; i++)
    {
        Box qb = cube[i];
        
        if(qb.x == index)
        {
            turn = 'X';
            m = new Move(qb.x, qb.y, qb.z, -dir, index);
            m.start();
        }
    }
}

public void turnYY(int index, int dir)
{
    for(int i = 0; i < cube.length; i++)
    {
        Box qb = cube[i];
        
        if(qb.y == index)
        {
            PMatrix2D matrix = new PMatrix2D();
            matrix.rotate(dir * HALF_PI);
            matrix.translate(qb.x, qb.z);
            
            qb.update(round(matrix.m02), qb.y, round(matrix.m12));
            
            qb.turnFaceY(dir);
        }
    }
}

public void turnY(int index, int dir)
{
    for(int i = 0; i < cube.length; i++)
    {
        Box qb = cube[i];
        
        if(qb.y == index)
        {
            turn = 'Y';
            m = new Move(qb.x, qb.y, qb.z, dir, index);
            m.start();
        }
    }
}

public void turnZZ(int index, int dir)
{
    for(int i = 0; i < cube.length; i++)
    {
        Box qb = cube[i];
        
        if(qb.z == index)
        {
            PMatrix2D matrix = new PMatrix2D();
            matrix.rotate(dir * HALF_PI);
            matrix.translate(qb.x, qb.y);
            
            qb.update(round(matrix.m02), round(matrix.m12), qb.z);
            
            qb.turnFaceZ(dir);
        }
    }
}

public void turnZ(int index, int dir)
{
    for(int i = 0; i < cube.length; i++)
    {
        Box qb = cube[i];
        
        if(qb.z == index)
        {
            turn = 'Z';
            m = new Move(qb.x, qb.y, qb.z, -dir, index);
            m.start();
        }
    }
}

public void applyMove(char move)
{
   KeyPress = move;
   
   switch(move)
   {
       case 'f': turnZ(1, 1); solve = "F" + solve; solveCounter++; break;
       case 'F': turnZ(1, -1); solve = "f" + solve; solveCounter++; break;
       case 'b': turnZ(-1, 1); solve = "B" + solve; solveCounter++; break;
       case 'B': turnZ(-1, -1); solve = "b" + solve; solveCounter++; break;
       
       case 'u': turnY(1, 1); solve = "U" + solve; solveCounter++; break;
       case 'U': turnY(1, -1); solve = "u" + solve; solveCounter++; break;
       case 'd': turnY(-1, 1); solve = "D" + solve; solveCounter++; break;
       case 'D': turnY(-1, -1); solve = "d" + solve; solveCounter++; break;
       
       case 'r': turnX(1, 1); solve = "R" + solve; solveCounter++; break;
       case 'R': turnX(1, -1); solve = "r" + solve; solveCounter++; break;
       case 'l': turnX(-1, 1); solve = "L" + solve; solveCounter++; break;
       case 'L': turnX(-1, -1); solve = "l" + solve; solveCounter++; break;
       
       case 'x': turnX(0, 1); solve = "X" + solve; solveCounter++; break;
       case 'X': turnX(0, -1); solve = "x" + solve; solveCounter++; break;
       
       case 'y': turnY(0, 1); solve = "Y" + solve; solveCounter++; break;
       case 'Y': turnY(0, -1); solve = "y" + solve; solveCounter++; break;
       
       case 'z': turnZ(0, 1); solve = "Z" + solve; solveCounter++; break;
       case 'Z': turnZ(0, -1); solve = "z" + solve; solveCounter++; break;
   }
}

public void solveMove(char move)
{
     KeyPress = move;
     
     switch(move)
     {
         case 'f': turnZ(1, 1); break;
         case 'F': turnZ(1, -1); break;
         case 'b': turnZ(-1, 1); break;
         case 'B': turnZ(-1, -1); break;
         
         case 'u': turnY(1, 1); break;
         case 'U': turnY(1, -1); break;
         case 'd': turnY(-1, 1); break;
         case 'D': turnY(-1, -1); break;
         
         case 'r': turnX(1, 1); break;
         case 'R': turnX(1, -1); break;
         case 'l': turnX(-1, 1); break;
         case 'L': turnX(-1, -1); break;
         
         case 'x': turnX(0, 1); break;
         case 'X': turnX(0, -1); break;
         
         case 'y': turnY(0, 1); break;
         case 'Y': turnY(0, -1); break;
         
         case 'z': turnZ(0, 1); break;
         case 'Z': turnZ(0, -1); break;
     }
}

public void suffleCube()
{
    sequence = "";
    counter = 0;
    for(int i = 0; i < 50; i++)
    {
        int id = PApplet.parseInt(random(allMoves.length));
        if(random(1) < 0.5f)
        {
            sequence += allMoves[id];
        }
        else
        {
            sequence += allMoves[id].toUpperCase();
        }
    }
}

public void solveCube()
{
    solveCounter = 0;
    temp = solve;
    solve = "";
}

public void keyPressed()
{
  if(m.finished)
  {
    if(key == 's')
    {
        suffleCube();
    }
    else if(key == 'S')
    {
        solveCube();
    }
    else
    {
        applyMove(key);
    }
  }
}

public void draw()
{  
    background(51);
     
    cam.beginHUD();
   
     fill(237, 69, 69);
     textSize(20);
     text("Key Pressed: ", 100, 100);
     textSize(30);
     fill(255);
     text(KeyPress, 250, 100);
     rectMode(CORNER);
     fill(0, 255, 0, 50);
     rect(width-350, 50, 300, 300, 10);
     
     fill(240, 229, 77);
     textSize(30);
     text("Controls:", width-300, 100);
     
     fill(68, 176, 235);
     textSize(20);
     text("Suffle Cube: ", width-300, 140);
     fill(242, 85, 221);
     text("s", width-150, 140);
     
     fill(68, 176, 235);
     textSize(20);
     text("Solve Cube: ", width-300, 170);
     fill(242, 85, 221);
     text("S", width-150, 170);
     
     fill(68, 176, 235);
     textSize(20);
     text("Rotation: ", width-300, 220);
     fill(242, 85, 221);
     text("F, f, B, b, U, u, D, d", width-300, 260);
     text("R, r, L, l, X, x, Y, y", width-300, 290);
     text("Z, z", width-300, 320);
     
    cam.endHUD();
    
    rotateX(-0.3f);
    rotateY(0.4f);
    
    m.update();
      
    scale(50);
    
    if(frameCount%15 == 0)
    {  
      if(counter < sequence.length())
      {
          applyMove(sequence.charAt(counter));
          counter++;
      }
    }
    
    if(frameCount%15 == 0)
    {
        if(solveCounter < temp.length())
        {
            solveMove(temp.charAt(solveCounter));
            
            solveCounter++;
        }
    }
    
    for(int i = 0; i < cube.length; i++)
    {
       push();
       
        if(cube[i].x == m.x && turn == 'X')
        {
            rotateX(m.angle);
        }
        else if(cube[i].y == m.y && turn == 'Y')
        {
            rotateY(m.angle);
        }
        else if(cube[i].z == m.z && turn == 'Z')
        {
            rotateZ(m.angle);
        }
        
       cube[i].show();
       
       pop();
    }   
}
class Move
{
    float angle = 0;
    int x = 0, y = 0, z = 0, dir = 0, index = 0;
    boolean animate = false, finished = true;
    
    Move(int x, int y, int z, int dir, int index)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.dir = dir;
        this.index = index; 
    }
    
    public void start()
    {
        animate = true;
        finished = false;
    }
    
    public void update()
    {
        if(animate)
        {  
            angle += (-dir * 0.2f);
            if(abs(angle) > HALF_PI)
            {
                animate = false;
                angle = 0;
                
                if(turn == 'X')
                   turnXX(index, -dir);
                else if(turn == 'Y')
                   turnYY(index, dir);
                else if(turn == 'Z')
                    turnZZ(index, -dir);
                
                finished = true;
                turn = '$';
            }
        }
    }
}
class Box{
 
  PMatrix3D matrix;
  int x, y, z;
  
  face faces[] = new face[6];
  
  Box(PMatrix3D m, int x, int y, int z)
  {
      matrix = m;
      this.x = x;
      this.y = y;
      this.z = z;
      
      faces[0] = new face(new PVector(0, 0, -1), color(43, 43, 217));
      faces[1] = new face(new PVector(0, 0, 1), color(37, 184, 69));
      faces[2] = new face(new PVector(0, 1, 0), color(213, 227, 216));
      faces[3] = new face(new PVector(0, -1, 0), color(217, 217, 43));
      faces[4] = new face(new PVector(1, 0, 0), color(232, 149, 60));
      faces[5] = new face(new PVector(-1, 0, 0), color(217, 43, 43));
  }
  
  public void update(int x, int y, int z)
  {
      matrix.reset();
      matrix.translate(x, y, z);
      this.x = x;
      this.y = y;
      this.z = z;
  }
  
  public void turnFaceX(int dir)
  {
      for(face f : faces)
        f.turnX(dir * HALF_PI);
  }
  
  public void turnFaceY(int dir)
  {
      for(face f : faces)
        f.turnY(dir * HALF_PI);
  }
  
  public void turnFaceZ(int dir)
  {
      for(face f : faces)
        f.turnZ(dir * HALF_PI);
  }
  
  public void show()
  {
     noFill();
     
     stroke(0);
     
     strokeWeight(0.02f);
     
     pushMatrix();
      
     applyMatrix(matrix);
    
     box(1);
     
     for(face f: faces)
     {
        PVector temp = f.normal;
        if(abs(temp.x) == 1 && temp.x == x)
            f.show();
        else if(abs(temp.y) == 1 && temp.y == y)
            f.show();
        else if(abs(temp.z) == 1 && temp.z == z)
            f.show();
        else
        {
            f.c = color(51);
            f.show();
        }
     }
     
     popMatrix();
  }
}
class face
{
   PVector normal;
   int c;
   
   face(PVector normal, int c)
   {
      this.normal = normal;
      this.c = c;
   }
   
   public void turnZ(float angle)
   {
       PVector v = new PVector();
       
       v.x = round(normal.x*cos(angle) - normal.y*sin(angle));
       v.y = round(normal.x*sin(angle) + normal.y*cos(angle));
       v.z = round(normal.z);
       
       normal = v;
   }
   
   public void turnY(float angle)
   {
       PVector v = new PVector();
       
       v.x = round(normal.x*cos(angle) - normal.z*sin(angle));
       v.z = round(normal.x*sin(angle) + normal.z*cos(angle));
       v.y = round(normal.y);
       
       normal = v;
   }
   
   public void turnX(float angle)
   {
       PVector v = new PVector();
       
       v.y = round(normal.y*cos(angle) - normal.z*sin(angle));
       v.z = round(normal.y*sin(angle) + normal.z*cos(angle));
       v.x = round(normal.x);
       
       normal = v;
   }
   
   public void show()
   {
     pushMatrix();
     
     fill(c);
     
     stroke(1);
     strokeWeight(0.01f);
     
     rectMode(CENTER);
     
     translate(0.5f*normal.x, 0.5f*normal.y, 0.5f*normal.z);
     
     if(abs(normal.x) > 0)
     {
         rotateY(HALF_PI);
     }
     else if(abs(normal.y) > 0)
     {
         rotateX(HALF_PI);
     }
     else if(abs(normal.z) > 0)
     {
         rotateZ(HALF_PI);
     }
     
     square(0, 0, 1);
     
     popMatrix();
   }
}
  public void settings() {  fullScreen(P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#FFF7F7", "--hide-stop", "file1" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
