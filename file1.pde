import peasy.*;

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

void setup()
{  
    fullScreen(P3D);
    
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
    
    m = new Move(0, 0, 0, 0);
}

void turnX(int index, int dir)
{
    for(int i = 0; i < cube.length; i++)
    {
        Box qb = cube[i];
        
        if(qb.x == index)
        {
            PMatrix2D matrix = new PMatrix2D();
            matrix.rotate(dir * HALF_PI);
            matrix.translate(qb.y, qb.z);
            
            turn = 'X';
            m = new Move(qb.x, qb.y, qb.z, dir);
            
            qb.update(qb.x, round(matrix.m02), round(matrix.m12));
            
            qb.turnFaceX(dir);
        }
    }
}

void turnY(int index, int dir)
{
    for(int i = 0; i < cube.length; i++)
    {
        Box qb = cube[i];
        
        if(qb.y == index)
        {
            PMatrix2D matrix = new PMatrix2D();
            matrix.rotate(dir * HALF_PI);
            matrix.translate(qb.x, qb.z);
            
            turn = 'Y';
            m = new Move(qb.x, qb.y, qb.z, dir);
            
            qb.update(round(matrix.m02), qb.y, round(matrix.m12));
            
            qb.turnFaceY(dir);
        }
    }
}

void turnZ(int index, int dir)
{
    for(int i = 0; i < cube.length; i++)
    {
        Box qb = cube[i];
        
        if(qb.z == index)
        {
            PMatrix2D matrix = new PMatrix2D();
            matrix.rotate(dir * HALF_PI);
            matrix.translate(qb.x, qb.y);
            
            turn = 'Z';
            m = new Move(qb.x, qb.y, qb.z, dir);
            
            qb.update(round(matrix.m02), round(matrix.m12), qb.z);
            
            qb.turnFaceZ(dir);
        }
    }
}

void applyMove(char move)
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

void solveMove(char move)
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

void suffleCube()
{
    sequence = "";
    counter = 0;
    for(int i = 0; i < 50; i++)
    {
        int id = int(random(allMoves.length));
        if(random(1) < 0.5)
        {
            sequence += allMoves[id];
        }
        else
        {
            sequence += allMoves[id].toUpperCase();
        }
    }
}

void solveCube()
{
    solveCounter = 0;
    temp = solve;
    solve = "";
}

void keyPressed()
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

void draw()
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
     
    cam.endHUD();
    
    rotateX(-0.3);
    rotateY(0.4);
    
    if(turn != '$')
      m.update();
      
    scale(50);
    
    if(frameCount%5 == 0)
    {  
      if(counter < sequence.length())
      {
          applyMove(sequence.charAt(counter));
          counter++;
      }
    }
    
    if(frameCount%5 == 0)
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
