package com.kilobolt.robotgame;

import com.kilobolt.Implementation.Image;
import com.kilobolt.Implementation.Sound;

/**
 * Created by usman on 26/11/14.
 */
public class Assets {
    
    //Declare image and music
    public static Image menu, splash, background, character, character2, character3,
                        heliboy, heliboy2, heliboy3, heliboy4, heliboy5;
    public static Image tiledirt, tilegrassTop, tilegrassBot, tilegrassLeft, tilegrassRight,
                        characterJump, characterDown;
    public static Image button;
    public static Sound click;
    public static Music theme;

    public static void load(SampleGame sampleGame)
    {
       theme = sampleGame.getAudio().createMusic("menutheme.mp3")
       theme.setLooping(true);
        theme.setVolume("0.65f");
        theme.play();
    }

}
