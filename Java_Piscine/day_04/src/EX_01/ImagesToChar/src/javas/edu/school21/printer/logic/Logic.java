package javas.edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Logic {
    char white;
    char black;
    String path;
    public Logic(char white, char black){
        this.black =black;
        this.white =white;
    }

    public void print(){
        try {
            URL url = Logic.class.getResource("/resources/it.bmp");
            if(url==null){
                System.out.println("no such file!");
                System.exit(-1);
            }
            BufferedImage image = ImageIO.read(url);
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
