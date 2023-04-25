package ie.tudublin;

import processing.core.PApplet;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

public class SpiralVisualizer extends PApplet {

    Minim minim;
    AudioPlayer ap;
    AudioBuffer ab;
    float[] spectrum;

    public void settings() {
        size(500, 500);
    }

    public void setup() {
        minim = new Minim(this);
        ap = minim.loadFile("The-Beatles-Strawberry-Fields-Forever.mp3", 1024);
        ap.play();
        ab = ap.mix;
        spectrum = new float[ab.size()];
        colorMode(HSB);
    }

    public void draw() {
        background(0);
        translate(width/2, height/2);

        float angleStep = TWO_PI / spectrum.length;
        float radius = 1000;

        for (int i = 0; i < spectrum.length; i++) {
            float angle = i * angleStep;
            float magnitude = ab.get(i);
            float x = radius * cos(angle) * magnitude;
            float y = radius * sin(angle) * magnitude;
            float hue = map(i, 0, spectrum.length, 0, 255);
            stroke(hue, 255, 255);
            line(0, 0, x, y);
        }
    }
}
