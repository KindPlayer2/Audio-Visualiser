package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class flower extends PApplet {
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
        ap = minim.loadFile("strawberry_fields_forever.mp3", 1024);
        ap.play();
        ab = ap.mix;
        colorMode(HSB);

        y = height / 2;
        smoothedY = y;

        fft = new FFT(width, 1024);
    }

    public void sinFlower(float h, float w, float e, float c) {
    
        strokeWeight(0);
        fill(c, 255, 220);

        circle(h + 50, w + 10, 75); // works
        circle(h - 50, w - 10, 75);
        circle(h - 10, w + 50, 75);
        circle(h + 10, w - 50, 75);

        fill(50, 255, 255);
        circle(h, w, 50);

    }

    float lerpedBuffer[] = new float[1024];

    public void draw() {
        background(80, 190, 150);
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

        // used to stop too many flowers spawning
        int counter = 0;

        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for (int i = 0; i < ab.size(); i++) {

            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);

            average = sum / (float) ab.size();
            smoothedAmplitude = lerp(smoothedAmplitude, average, 0.5f);

            //float freq = fft.indexToFreq((int)(smoothedAmplitude * 100000.0f));
            float freq = (int)(smoothedAmplitude * 100000.0f);

            System.out.println(freq);

            //float c = map(smoothedAmplitude, -1, 1, 0, 255);
            
            float c = map(i, 0, freq, 0, 255);
            c = map(i, 0, freq, 0, 255);
            
            float f = lerpedBuffer[i] * halfH * 4.0f;
            float pos = halfH + random(-500,500);
            float pos2 = (width / 2) + random(-500,500);

            sinFlower(halfH, width / 2, f+100, c); // middle flower

            
            if (freq > 5000 && counter < 5)
            {
                // calls flowers in random pos.
                pos = halfH + random(-500,500);
                pos2 = (width / 2) + random(-500,500);


                sinFlower(pos, pos2, f, c);
                if (freq > 7000)
                {
                    pos = halfH + random(-500,500);
                    pos2 = (width / 2) + random(-500,500);
                    
                }

                sinFlower(pos, pos2, f, c);

                // increments flower counter
                counter++;
                
            }
            
        }
    }

}