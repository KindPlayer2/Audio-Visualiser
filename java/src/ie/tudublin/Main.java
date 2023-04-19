package ie.tudublin;

public class Main
{


	public static void audio1()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Audio1());
    }

	public static void audio2()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Audio2());
    }

	public static void audio3()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Audio3());
    }

	public static void flower()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Flower());
    }
	
	public static void main(String[] args)
	{
		System.out.println("Hello world");
		
		flower();
	}
	
	
}