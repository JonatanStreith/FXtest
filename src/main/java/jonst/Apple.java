package jonst;

public class Apple extends Sprite {

    public void checkWallCollision(double screenWidth, double screenHeight){
        if(positionX+getBoundary().getWidth() >= screenWidth){       //Hits right border
            velocityX = velocityX*-1;
            positionX = screenWidth-getBoundary().getWidth()-1;
        }
        if(positionX <= 0){                                          //Hits left border
            velocityX = velocityX*-1;
            positionX = 1;
        }
        if(positionY+getBoundary().getHeight() >= screenHeight){     //Hits lower border
            velocityY = velocityY*-1;
            positionY = screenHeight-getBoundary().getHeight()-1;
        }
        if(positionY <= 0){                                          //Hits upper border
            velocityY = velocityY*-1;
            positionY = 1;
        }
    }

}
