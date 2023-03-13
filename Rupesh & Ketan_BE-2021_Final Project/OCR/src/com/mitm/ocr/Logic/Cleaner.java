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
public class Cleaner {

    public BufferedImage blackAndWhiteCleaning(BufferedImage Image) {
        BufferedImage bwImage = null;
        for (int j = 0; j < Image.getHeight(); j++) {
            for (int i = 0; i < Image.getWidth(); i++) {
                if (Image.getRGB(i, j) != -16777216) {
                    Image.setRGB(i, j, -1);
                }
            }
        }
        return bwImage = Image;
    }

    public BufferedImage blackAndGrayCleaning(BufferedImage Image) {
        BufferedImage bwImage = null;
        for (int j = 0; j < Image.getHeight(); j++) {
            for (int i = 0; i < Image.getWidth(); i++) {
                if (Image.getRGB(i, j) > -4473925) {
                    Image.setRGB(i, j, -1);
                } else {
                    Image.setRGB(i, j, -16777216);
                }
            }
        }
        return bwImage = Image;
    }
    
    public BufferedImage blackAndLightGrayCleaning(BufferedImage Image) {
        BufferedImage bwImage = null;
        for (int j = 0; j < Image.getHeight(); j++) {
            for (int i = 0; i < Image.getWidth(); i++) {
                if (Image.getRGB(i, j) > -3092272) {
                    Image.setRGB(i, j, -1);
                } else {
                    Image.setRGB(i, j, -16777216);
                }
            }
        }
        return bwImage = Image;
    }

    public BufferedImage ColorCleaning(BufferedImage Image, int RGBcolor) {
        BufferedImage bwImage = null;
        for (int j = 0; j < Image.getHeight(); j++) {
            for (int i = 0; i < Image.getWidth(); i++) {
                if (Image.getRGB(i, j) == RGBcolor) {
                    Image.setRGB(i, j, -16777216);
                } else {
                    Image.setRGB(i, j, -1);
                }
            }
        }
        return bwImage = Image;
    }
}
