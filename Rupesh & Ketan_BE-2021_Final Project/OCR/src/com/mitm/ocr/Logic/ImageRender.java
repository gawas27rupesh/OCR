/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitm.ocr.Logic;

import java.awt.image.BufferedImage;
import com.mitm.ocr.Gui;

/**
 *
 * @author account
 */
public class ImageRender {

    private int y1 = 0;
    private int y2 = 0;
    private int x1 = 0;
    private int x2 = 0;
    public static BufferedImage Img = null;
    private boolean end;
    private boolean endRow;
    private final Gui gui;
    RecognizeImage ri;

    public ImageRender(Gui gui) {

        ri = new RecognizeImage();
        this.gui = gui;
    }

    public boolean lockingUp() {

        for (int j = y2; j < Img.getHeight(); j++) {
            for (int i = x1; i < Img.getWidth(); i++) {
                if (Img.getRGB(i, j) == -16777216) {
                    if (j == 0) {
                        y1 = j;
                        return true;
                    }
                    y1 = j - 1;
                    return true;
                }
            }
        }
        end = true;
        return false;
    }

    public boolean lockingDown() {

        for (int j = y1 + 1; j < Img.getHeight(); j++) {
            int counterWhite = 0;
            for (int i = x1; i < Img.getWidth(); i++) {
                if (Img.getRGB(i, j) == -1) {
                    counterWhite++;
                }
            }
            if (counterWhite == Img.getWidth()) {
                y2 = j;
                return true;
            }
            if (j == Img.getHeight() - 1) {
                y2 = j;
                end = true;
                return true;
            }
        }
        return false;
    }

    public boolean lockingLeft() {
        int spaceCounter = 0;
        for (int i = x2; i < Img.getWidth(); i++) {
            for (int j = y1 + 1; j <= y2; j++) {
                if (Img.getRGB(i, j) == -16777216) {
                    if(i==0){
                       x1 = i;
                    return true; 
                    }
                    x1 = i - 1;
                    return true;
                }
            }
            spaceCounter++;
            if (spaceCounter == 3) {
                gui.setjTextArea1(" ");
                spaceCounter = 0;
            }
        }
        endRow = true;
        gui.setjTextArea1("\n");
        return false;
    }

    public boolean lockingRight() {
        for (int i = x1 + 1; i < Img.getWidth(); i++) {
            int counteWhite = 0;
            for (int j = y1 + 1; j <= y2; j++) {
                if (Img.getRGB(i, j) == -1) {
                    counteWhite++;
                }
            }
            int row = y2 - y1;
            if (counteWhite == row) {
                x2 = i;
                return true;
            }
            if (i == Img.getWidth() - 1) {
                x2 = i;
                endRow = true;
                gui.setjTextArea1("\n");
                return true;
            }
        }
        return false;
    }

    public void rendering() {
        
        Crop c = new Crop();
        while (end == false) {
            endRow = false;
            boolean up = lockingUp();
            boolean down = false;
            if (up == true) {
                down = lockingDown();
                if (down == true) {
                    while (endRow == false) {
                        boolean left = false;
                        boolean right = false;
                        left = lockingLeft();
                        if (left == true) {
                            right = lockingRight();
                            if (right == true) {
                                String letter = ri.recognize(c.crop(cutting()));
                                gui.setjTextArea1(letter);
                            }
                        }
                    }
                    x1 = 0;
                    x2 = 0;
                }
            }
        }
        y1 = 0;
        y2 = 0;
        end = false;
        gui.getjButton2().setEnabled(true);
        gui.getjLabel2().setText("Recognizing done!");
        System.out.println("Recognizing done!");
    }

    public BufferedImage cutting() {
        return Img.getSubimage(x1, y1, x2 - x1, y2 - y1);
    }
}
