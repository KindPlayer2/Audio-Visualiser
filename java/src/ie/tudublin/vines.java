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
    float halfH = height / 2;
    float halfW = width/2;

    public void settings() {
        size(1024, 1000);
    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile("strawberry_fields_forever.mp3", 1024); //CHANGE
        ap.play();
        ab = ap.mix;
        colorMode(HSB);
        

        y = height / 2;

        fft = new FFT(width, 1024);
    }

    public void vine(float x, float y) 
    {
        float x1;
        float y1;

        float x2;
        float y2;

        x1 = 100;
        y1 = 700;

        x2 = 100;
        y2 = 500;

        //stroke(80, 190, 150); // green

        stroke(20, 100, 120);
        strokeWeight(40);
        line(x1, y1, x2, y2-y);


                
        // leaves
        strokeWeight(20);
        stroke(80, 190, 150);
        triangle(x1, y1, x1+30, y1,x1-1, y1-30);

    }

    float lerpedBuffer[] = new float[1024];
    float totalX = 0;
    float totalY = 0;

    public void draw()
    {
        float average = 0;
        float sum = 0;
        int highestIndex = 0;

        background(0,0,50);

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
        

        freq = freq / 100;
        System.out.println(freq);


        if (freq > 50)
        {
            totalX++;
            totalY++;
        }

        if (freq < 20 && totalX>400 && totalY>500)
        {
            totalX--;
            totalY--;
        }

        vine(totalX,totalY); 
        //line(totalX, totalY, totalX, totalY);
        
        }
    }        

