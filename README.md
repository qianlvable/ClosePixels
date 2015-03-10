# ClosePixels
An android library design to converts an image into a pixelated version.Inspired by https://github.com/desandro/close-pixelate .
##Showcase：
######The origin:  
![image](https://github.com/qianlvable/ClosePixels/blob/master/demoImage/orign.jpg)

######Use Square pixel with size and solution 30：  
![image](https://github.com/qianlvable/ClosePixels/blob/master/demoImage/square.jpg)

######Use Circle pixel：   
![image](https://github.com/qianlvable/ClosePixels/blob/master/demoImage/Circle.jpg)

######Use diamond：   
![image](https://github.com/qianlvable/ClosePixels/blob/master/demoImage/diamond.jpg)

##Usage
Android studio:  
Import "pixelart" library module.  
PixelArt.renderPixels(Bitmap bitmap,int resolution,int size,SHAPE option); // Will return a rendered bitmap

Ps: PixelArt.SHAPE includes PixelArt.SHAPE.square,PixelArt.SHAPE.circle,PixelArt.SHAPE.diamond
