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
        strokeWeight(20);
        stroke(80, 190, 150);

        float x1, x2,x3;
        float y1,y2,y3;

        x1 = 100;
        x2 = 200;
        x3 = 300;

        y1 = 100;
        y2 = 300;
        y3 = 100;


        noFill();
        beginShape();
        curveVertex(0, 200); // the first control point
        curveVertex(0, 200); // is also the start point of curve

        curveVertex(x1+x, y1+y);
        curveVertex(x2+x, y2+y);
        curveVertex(x1+x+100, y1+y);
        curveVertex(x2+x-100, y2-y);
        endShape();

        //curveVertex(400+x, 200+y);

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

