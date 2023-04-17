package ie.tudublin;

import processing.core.PApplet;

public class AudioVisualiser extends PApplet {

    Cube[][] cubes;
    boolean isCubeVisible = false;

    public void keyPressed() {
        if (key == '1') {
            createCube();
        } else if (key == '0') {
            removeCube();
        }
    }

    public void settings() {
        size(1000, 1000, P3D);
    }

    public void setup() {
        colorMode(HSB);
    }

    public void createCube() {
        if (!isCubeVisible) {
            cubes = new Cube[3][3];
            float posit = 1000;
            for (int i = 0; i < cubes.length; i++) {
                for (int j = 0; j < cubes.length; j++) {
                    cubes[i][j] = new Cube();
                    cubes[i][j].x = width / 2 + (i - 1) * 150;
                    cubes[i][j].y = posit / 2;
                    cubes[i][j].size = 100;
                }
                posit = posit + 200;
            }
            isCubeVisible = true;
        }
    }

    public void removeCube() {
        isCubeVisible = false;
    }

    public void draw() {
        background(0);
        lights();

        if (isCubeVisible) {
            for (int i = 0; i < cubes.length; i++) {
                for (int j = 0; j < cubes.length; j++) {
                    cubes[i][j].rot = map(mouseX, 0, width, 0, 255);
                    cubes[i][j].rot += 0.01f;
                    cubes[i][j].render(this);
                }
            }
        }
    }
}
