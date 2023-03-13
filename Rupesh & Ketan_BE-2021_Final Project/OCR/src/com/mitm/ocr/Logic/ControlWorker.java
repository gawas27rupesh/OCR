/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mitm.ocr.Logic;

import javax.swing.SwingWorker;

/**
 *
 * @author account
 */
public class ControlWorker extends SwingWorker {

    ImageRender ir;

    public ControlWorker(ImageRender ir) {
        this.ir = ir;
    }

    @Override
    protected Void doInBackground() throws Exception {
        ir.rendering();
        return null;
    }
}
