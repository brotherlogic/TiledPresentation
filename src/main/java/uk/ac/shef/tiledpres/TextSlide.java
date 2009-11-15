package uk.ac.shef.tiledpres;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.nodes.PText;

/**
 * A slide constructed just from text
 * 
 * @author sat
 * 
 */
public class TextSlide extends Slide
{
	/** The title of the slide */
	private final String headline;

	/** The bullet points connected to the slide */
	private final String[] bullets;

	/**
	 * Constructor
	 * 
	 * @param title
	 *            The title to display for this slide
	 * @param headlineIn
	 *            The title of the slide
	 * @param bulletsIn
	 *            The bullet points for this slide
	 */
	public TextSlide(final String title, final String headlineIn, final String[] bulletsIn)
	{
		super(title);

		this.headline = headlineIn;
		this.bullets = bulletsIn;
	}

	@Override
	public final PNode getSlide()
	{
		PText headNode = new PText(headline);
		PNode[] bNode = new PNode[bullets.length];
		for (int i = 0; i < bullets.length; i++)
			bNode[i] = new PText(bullets[i]);

		PNode thisNode = new PNode();
		double yLoc = 0;

		double maxX = 0;

		thisNode.addChild(headNode);
		yLoc += headNode.getHeight();
		maxX = Math.max(maxX, headNode.getWidth());

		for (int i = 0; i < bNode.length; i++)
		{
			bNode[i].setBounds(0, yLoc, bNode[i].getWidth(), bNode[i].getHeight());
			thisNode.addChild(bNode[i]);
			yLoc += bNode[i].getHeight();

			maxX = Math.max(maxX, bNode[i].getWidth());
		}

		thisNode.setHeight(yLoc);
		thisNode.setWidth(maxX);

		return thisNode;
	}
}
