package ie.tudublin;


import ddf.minim.AudioBuffer;
// import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class Flower extends PApplet {
    public float x, y;
    public float size;
    public float c;
    public float rot;
    
    Minim m;
    AudioInput ai;
    AudioPlayer ap;
    AudioBuffer ab;

    FFT fft;

    public void settings()
    {
        size(256,512);
    }

    public void setup()
    {
        m = new Minim(this);
        ai = m.getLineIn(Minim.MONO, width, 44100, 16);
        ab = ai.mix;
        fft = new FFT(width, 44100);
    }

    public void draw()
    {
        background(0);
        colorMode(HSB);
        stroke(255);

        //draw green stem
        strokeWeight(10);
        stroke(0, 128, 0);
        line(100, 100, 100, 300);
        
        //leafs
        ellipse(105, 200, 10, 10);
        ellipse(95, 225, 10, 10);
        
        //change stroke back to thin black line
        strokeWeight(1);
        stroke(0);
        
        //draw petals
        fill(255, 100, 0);
        ellipse(50, 50, 100, 100);
        ellipse(150, 50, 100, 100);
        ellipse(50, 150, 100, 100);
        ellipse(150, 150, 100, 100);
        
        //draw middle part
        fill(255, 128, 0);
        ellipse(100, 100, 100, 100);
        
    }

}
