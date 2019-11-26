package jonst;

public class Player extends Sprite {


    public void checkWallCollision(double screenWidth, double screenHeight){
        if(positionX+getBoundary().getWidth() >= screenWidth){       //Hits right border
            velocityX = 0;
            positionX = screenWidth-getBoundary().getWidth()-1;
        }
        if(positionX <= 0){                                          //Hits left border
            velocityX = 0;
            positionX = 1;
        }
        if(positionY+getBoundary().getHeight() >= screenHeight){     //Hits lower border
            velocityY = 0;
            positionY = screenHeight-getBoundary().getHeight()-1;
        }
        if(positionY <= 0){                                          //Hits upper border
            velocityY = 0;
            positionY = 1;
        }
    }
}
