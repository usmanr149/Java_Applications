package com.company;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 * Created by usman on 04/05/15.
 */
public class Sound {

    public static final AudioClip BALL = Applet.newAudioClip(Sound.class.getResource("ball.wav"));
    public static final AudioClip GAMEOVER = Applet.newAudioClip(Sound.class.getResource("gameover.wav"));
    public static final AudioClip BACK = Applet.newAudioClip(Sound.class.getResource("back.wav"));

}
