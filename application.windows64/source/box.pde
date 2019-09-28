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
  
  void update(int x, int y, int z)
  {
      matrix.reset();
      matrix.translate(x, y, z);
      this.x = x;
      this.y = y;
      this.z = z;
  }
  
  void turnFaceX(int dir)
  {
      for(face f : faces)
        f.turnX(dir * HALF_PI);
  }
  
  void turnFaceY(int dir)
  {
      for(face f : faces)
        f.turnY(dir * HALF_PI);
  }
  
  void turnFaceZ(int dir)
  {
      for(face f : faces)
        f.turnZ(dir * HALF_PI);
  }
  
  void show()
  {
     noFill();
     
     stroke(0);
     
     strokeWeight(0.02);
     
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
