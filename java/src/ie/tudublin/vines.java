package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class vines extends PApplet
{
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;
    FFT fft;

    int mode = 0;

    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    public void settings() {
        size(1024, 1000, P3D);
    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile("strawberry_fields_forever.mp3", 1024); //CHANGE
        ap.play();
        ab = ap.mix;
        colorMode(HSB);

        y = height / 2;
        smoothedY = y;

        fft = new FFT(width, 1024);
    }

    public void vine(float h, float w, float e, float c) 
    {


    }

    float off = 0;
    float lerpedBuffer[] = new float[1024];
    float totalX = 400;
    float totalY = 500;

    public void draw()
    {
        float halfH = height / 2;
        float average = 0;
        float sum = 0;
        int highestIndex = 0;

        for(int i = 0 ;i < fft.specSize() / 2 ; i ++)
        {
            line(i * 2.0f, height, i * 2.0f, height - fft.getBand(i) * 5.0f);

            if (fft.getBand(i) > fft.getBand(highestIndex))
            {
                highestIndex = i;
            }
        }

        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for(int i = 0 ; i < ab.size() ; i ++)
        {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);

        }
        average= sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);
        float freq = fft.indexToFreq((int)(smoothedAmplitude * 100000.0f));
        System.out.println(freq);
        
        strokeWeight(100);
        background(0,0,50);
        freq = freq / 100;
        System.out.println(freq);

        if (freq > 110)
        {
            totalX++;
            totalY++;
        }

        if (freq < 60 && totalX>400 && totalY>500)
        {
            totalX--;
            totalY--;
        }




            

        line(200,300,totalX,totalY); 
        
        }
       


    }        

