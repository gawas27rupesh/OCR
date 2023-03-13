/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitm.ocr.Logic;

import com.mortennobel.imagescaling.ResampleOp;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import org.neuroph.contrib.imgrec.ColorMode;
import org.neuroph.contrib.imgrec.ImageRecognitionPlugin;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.Neuron;

/**
 *
 * @author account
 */
public class RecognizeImage {

    private NeuralNetwork nnet;

    public RecognizeImage(){
        nnet = NeuralNetwork.load(this.getClass().getResourceAsStream("/com/mitm/ocr/Resources/NeuralNetwork/nreza.nnet"));
        
    }

    public String recognize(BufferedImage image) {
        BufferedImage b = image;
        ResampleOp resamOp = new ResampleOp(40,40);
        BufferedImage a =resamOp.filter(image, null);

        ImageRecognitionPlugin imageRecognition = (ImageRecognitionPlugin) nnet.getPlugin(ImageRecognitionPlugin.IMG_REC_PLUGIN_NAME);
        
        ColorMode m = ColorMode.FULL_COLOR;
        
        HashMap<String, Double> output = imageRecognition.recognizeImage(a);

        System.out.println(output.toString());
        HashMap<String, Neuron> n = imageRecognition.getMaxOutput();

        String ch = n.toString().substring(1, 2);
        System.out.println(ch);
        return ch;
    }
}

