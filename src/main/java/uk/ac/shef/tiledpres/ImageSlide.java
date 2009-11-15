package uk.ac.shef.tiledpres;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.nodes.PImage;

/**
 * A slide represented by a single image
 * 
 * @author sat
 * 
 */
public class ImageSlide extends Slide
{
	/** The URL pointing to the image */
	private URL imageURL;

	/** An input stream to read the slide from */
	private InputStream is;

	/** The underlying PNode that is represented by this slide */
	private PNode node;

	/**
	 * Constructor for the Image Slide
	 * 
	 * @param title
	 *            The title of this slide
	 * @param streamToImage
	 *            An @link{InputStream} for the slide image data
	 */
	public ImageSlide(final String title, final InputStream streamToImage)
	{
		super(title);
		is = streamToImage;
	}

	/**
	 * Constructor for the image slide
	 * 
	 * @param title
	 *            The title of the slide
	 * @param imageURLIn
	 *            The URL that points to the slide image data
	 */
	public ImageSlide(final String title, final URL imageURLIn)
	{
		super(title);
		this.imageURL = imageURLIn;
	}

	@Override
	public final PNode getSlide()
	{
		if (node == null)
		{
			node = new PNode();

			// Load the image from a file or from a stream
			try
			{
				PNode imagePart;
				if (imageURL != null)
					imagePart = new PImage(imageURL);
				else
				{
					BufferedImage img = ImageIO.read(is);
					imagePart = new PImage(img);
					is.close();
				}

				// Place the text under the image
				node.addChild(imagePart);

				node.setWidth(imagePart.getWidth());
				node.setHeight(imagePart.getHeight());
			} catch (IOException e)
			{
				System.err.println("Unable to load image - ignoring");
				e.printStackTrace();
			}

		}

		return node;
	}
}
