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
    
    void start()
    {
        animate = true;
        finished = false;
    }
    
    void update()
    {
        if(animate)
        {  
            angle += (-dir * 0.2);
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
