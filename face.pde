class face
{
   PVector normal;
   color c;
   
   face(PVector normal, color c)
   {
      this.normal = normal;
      this.c = c;
   }
   
   void turnZ(float angle)
   {
       PVector v = new PVector();
       
       v.x = round(normal.x*cos(angle) - normal.y*sin(angle));
       v.y = round(normal.x*sin(angle) + normal.y*cos(angle));
       v.z = round(normal.z);
       
       normal = v;
   }
   
   void turnY(float angle)
   {
       PVector v = new PVector();
       
       v.x = round(normal.x*cos(angle) - normal.z*sin(angle));
       v.z = round(normal.x*sin(angle) + normal.z*cos(angle));
       v.y = round(normal.y);
       
       normal = v;
   }
   
   void turnX(float angle)
   {
       PVector v = new PVector();
       
       v.y = round(normal.y*cos(angle) - normal.z*sin(angle));
       v.z = round(normal.y*sin(angle) + normal.z*cos(angle));
       v.x = round(normal.x);
       
       normal = v;
   }
   
   void show()
   {
     pushMatrix();
     
     fill(c);
     
     stroke(1);
     strokeWeight(0.01);
     
     rectMode(CENTER);
     
     translate(0.5*normal.x, 0.5*normal.y, 0.5*normal.z);
     
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
