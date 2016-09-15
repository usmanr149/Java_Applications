package com.Usman.framework;

import com.Usman.framework.Graphics.ImageFormat;

/**
 * Created by usman on 23/11/14.
 */
public interface Image {

    public int getWidth();
    public int getHeight();
    public ImageFormat getFormat();
    public void dispose();

}