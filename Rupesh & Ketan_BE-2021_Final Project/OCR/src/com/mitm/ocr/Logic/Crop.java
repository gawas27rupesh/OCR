/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitm.ocr.Logic;

import java.awt.image.BufferedImage;

/**
 *
 * @author account
 */
public class Crop {

    public int lockup(BufferedImage Img){

        for (int j = 0; j < Img.getHeight(); j++) {
             for (int i = 0; i < Img.getWidth(); i++) {
                    if(Img.getRGB(i, j)==-16777216){
                    if(j==0){
                      return j;
                    }
                    return j-1;
                }
            }
        }
        return 0;
    }
    
    public int lockdown(BufferedImage Img, int y1) {

        for (int j = y1 + 1; j < Img.getHeight(); j++) {
            int counterWhite = 0;
            for (int i = 0; i < Img.getWidth(); i++) {
                if (Img.getRGB(i, j) == -1) {
                    counterWhite++;
                }
            }
            if (counterWhite == Img.getWidth()) {
                if(j>(Img.getHeight()/2)){
                    return j;
                }
            }
            if (j == Img.getHeight() - 1) {
                return j + 1;
            }
        }
        return 0;
    }

    public BufferedImage crop(BufferedImage imageToCrop) {

        int y1 = lockup(imageToCrop);
        int y2 = lockdown(imageToCrop, y1);
        int x1 = 0;
        int x2 = imageToCrop.getWidth();
        return imageToCrop.getSubimage(x1, y1, x2 - x1, y2 - y1);
    }
}
