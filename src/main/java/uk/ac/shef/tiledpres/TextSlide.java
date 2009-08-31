package uk.ac.shef.tiledpres;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.nodes.PText;

public class TextSlide extends Slide
{
	String headline;
	String[] bullets;

	public TextSlide(String title, String headline, String[] bullets)
	{
		super(title);

		this.headline = headline;
		this.bullets = bullets;
	}

	public PNode getSlide()
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
			bNode[i].setBounds(0, yLoc, bNode[i].getWidth(), bNode[i]
					.getHeight());
			thisNode.addChild(bNode[i]);
			yLoc += bNode[i].getHeight();

			maxX = Math.max(maxX, bNode[i].getWidth());
		}

		thisNode.setHeight(yLoc);
		thisNode.setWidth(maxX);

		return thisNode;
	}
}
