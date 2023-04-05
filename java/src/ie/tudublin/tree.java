package ie.tudublin;
import processing.core.PApplet;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class tree extends PApplet {

	Minim minim;
    AudioInput ai;
    AudioPlayer ap;
    AudioBuffer ab;


    public void settings()
	{
		size(500, 500);
	}

	public void setup() {
		minim = new Minim(this);
		ap = minim.loadFile("Mio mao.mp3", 1024);
        ap.play();
        ab = ap.mix;
		colorMode(HSB);	
	}

	public void branch(float len)
	{
		float angle = PI/4;
		//float len = 100;
		line(0, 0, 0, - len);
		translate(0, -len);
		
		if(len > 4)
		{
			pushMatrix();
			rotate(angle);
			branch(len*0.75f);
			popMatrix();

			pushMatrix();
			rotate(-angle);
			branch(len*0.75f);
			popMatrix();
		}

	}

	float lerpedBuffer[] = new float[1024];

    public void draw() {
		background(0);
		float halfH = height / 2;
		float c = map(0, 0, ab.size(), 0, 255);
        stroke(c, 255, 255);
		translate(250, height);
		

		for(int i = 0 ; i < ab.size() ; i ++)
        {
            lerpedBuffer[i] = lerp(lerpedBuffer[i], ab.get(i), 0.1f);
			float f = lerpedBuffer[i] * halfH * 4.0f;
			branch(f);
        }
		
		
    }
}
