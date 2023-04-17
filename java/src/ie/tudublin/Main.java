package ie.tudublin;

public class Main
{
	public static void audioVisualiser()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch(a, new AudioVisualiser());
    }

	public static void Tree()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new tree());
    }

	
	public static void main(String[] args)
	{
		System.out.println("Hello world");
		Tree();
	}
	
	
}