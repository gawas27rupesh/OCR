/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitm.ocr.Logic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author account
 */
public class LoadImage {

    public BufferedImage loadImage(File f) throws IOException {
        BufferedImage image = ImageIO.read(f);
        
        Cleaner c = new Cleaner();
        return c.blackAndLightGrayCleaning(image);

    }
}
