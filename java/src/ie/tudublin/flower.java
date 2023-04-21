package ie.tudublin;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class flower extends PApplet {
    Minim minim;
    AudioPlayer ap;
    AudioInput ai;
    AudioBuffer ab;

    int mode = 0;

    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    public void keyPressed() {
        if (key >= '0' && key <= '9') {
            mode = key - '0';
        }
        if (keyCode == ' ') {
            if (ap.isPlaying()) {
                ap.pause();
            } else {
                ap.rewind();
                ap.play();
            }
        }
    }

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
    }

    public void sinFlower(float h, float w, float e, float c) {

        smoothedY = lerp(smoothedY, y, 0.1f);
        fill(c, 255, 200);

        circle(h + 50, w + 10, 75); // works
        circle(h - 50, w - 10, 75);
        circle(h - 10, w + 50, 75);
        circle(h + 10, w - 50, 75);

        stroke(50, 255, 255);
        fill(50, 255, 255);
        circle(h, w, 50);

        y += random(-10, 10);
        smoothedY = lerp(smoothedY, y, 0.1f);
        // circle(200, smoothedY, 50);

    }

    float off = 0;
    float lerpedBuffer[] = new float[1024];

    public void draw() {
        // background(0);
        float halfH = height / 2;
        float average = 0;
        float sum = 0;
        off += 1;

        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for (int i = 0; i < ab.size(); i++) {
            sum += abs(ab.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
        }
        average = sum / (float) ab.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);

        background(0);
        for (int i = 0; i < ab.size(); i++) {
            /*
             * float c = map(i, 0, ab.size(), mouseX /2, mouseY/ 2);
             * stroke(c, 255, 255);
             * float f = lerpedBuffer[i] * halfH * 4.0f;
             * line(0, i, f, i);
             * line(width, i, width - f, i);
             * line(i, 0, i, f);
             * line(i, height, i, height - f);
             */
            float c = map(ab.get(i), -1, 1, 0, 255);
            float f = lerpedBuffer[i] * halfH * 4.0f;

            sinFlower(halfH, width / 2, f, c);
            sinFlower(halfH + 200, (width / 2) + 200, f, c);
            sinFlower(halfH - 250, (width / 2) - 250, f, c);

        }
    }

}
