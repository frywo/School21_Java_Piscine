package javas.edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Logic {
    char white;
    char black;
    String path;
    public Logic(char white, char black, String path){
        this.black =black;
        this.path = path;
        this.white =white;
    }

    public void print(){
        try {
            BufferedImage image = ImageIO.read(new File(path));
            for(int x = 0; x<image.getWidth();++x){
                for (int y=0; y<image.getHeight();++y){
                  int color = image.getRGB(y,x);
                  if(color == Color.BLACK.getRGB()){
                      System.out.print(black);
                  } else if (color == Color.WHITE.getRGB()) {
                      System.out.print(white);
                  }
                }
                System.out.println();
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }
}
