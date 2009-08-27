package uk.ac.shef.slides;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.nodes.PImage;
import edu.umd.cs.piccolo.nodes.PText;

public abstract class Slide
{
	String title = "";

	protected abstract PNode getSlide();
	
	public PNode getNode()
	{
		PNode slide = getSlide();
		
		//Add the title to the bottom of the slide
		PNode titlePart = new PText(title);
		titlePart.setBounds(0, slide.getHeight(), titlePart.getWidth(), titlePart.getHeight());
		slide.addChild(titlePart);
		
		return slide;
	}
	
	
	public Slide(String title)
	{
		this.title = title;
	}
	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}
}
