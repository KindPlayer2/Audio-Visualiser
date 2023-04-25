package ie.tudublin;

import java.util.concurrent.TimeUnit;

public class Main
{
	public static void strawberry()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch(a, new StrawberryBush());
    }

	public static void spiral()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch(a, new SpiralVisualizer());
    }

	public static void Tree()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new tree());
    }


	public static void Flower()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new flower());
    }

	public static void vines()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new vines());
    }

	public static void notes()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new notes());
    }

	public static void main(String[] args) throws InterruptedException
	{
		System.out.println("Hello world");
		int time = 1;
		
		switch (time) 
		{
			case 1:
			{
				strawberry();
				TimeUnit.SECONDS.sleep(3);
				System.out.println(time);
				time++;
				break;
			}
			case 2:
			{
				Flower();
				TimeUnit.SECONDS.sleep(10);
				
				time++;
				break;
			}	
			case 3:
			{
				Tree();
				TimeUnit.SECONDS.sleep(10);
				time++;
				break;
			}	
			case 4:
			{
				vines();
				TimeUnit.SECONDS.sleep(10);
				time++;
				break;
			}
			case 5:
			{
				notes();
				TimeUnit.SECONDS.sleep(10);
				time++;
				break;
			}
			case 6:
			{
				spiral();
				TimeUnit.SECONDS.sleep(10);
				time++;
				break;
			}
			default:
				break;
		}		
	}	
}