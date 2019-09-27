class Move
{
    float angle = 0;
    int x = 0, y = 0, z = 0, dir = 0;
    boolean animate = false;
    
    Move(int x, int y, int z, int dir)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        this.dir = dir;
        angle = 0;
        animate = true;
    }
    
    void update()
    {
        if(animate)
        {  
            angle += (dir * 0.1);
            if(abs(angle) > HALF_PI)
            {
                animate = false;
                angle = 0;
            }
        }
    }
}
