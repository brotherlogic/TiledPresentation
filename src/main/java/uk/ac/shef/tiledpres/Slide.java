package uk.ac.shef.tiledpres;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.nodes.PText;

/**
 * A slide to show in the GUI
 * 
 * @author sat
 * 
 */
public abstract class Slide
{
	/** The title of the slide */
	private String title = "";

	/**
	 * Returns a PNode that represents the slide
	 * 
	 * @return {@link PNode} of the slide
	 */
	protected abstract PNode getSlide();

	/**
	 * Gets the PNode that this slide represents
	 * 
	 * @return A valid PNode showing the slide data
	 */
	public final PNode getNode()
	{
		PNode slide = getSlide();

		// Add the title to the bottom of the slide
		PNode titlePart = new PText(title);
		titlePart.setBounds(0, slide.getHeight(), titlePart.getWidth(), titlePart.getHeight());
		slide.addChild(titlePart);

		return slide;
	}

	/**
	 * Slide constructor
	 * 
	 * @param titleIn
	 *            The title of the slide
	 */
	public Slide(final String titleIn)
	{
		this.title = titleIn;
	}

	/**
	 * Gets the title of the slide
	 * 
	 * @return The slide title
	 */
	public final String getTitle()
	{
		return title;
	}

	/**
	 * Sets the title of the slide
	 * 
	 * @param titleIn
	 *            The new title of the slide
	 */
	public final void setTitle(final String titleIn)
	{
		this.title = titleIn;
	}
}
