package uk.ac.shef.slides;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolo.nodes.PImage;

public class ImageSlide extends Slide
{
	URL imageURL;
	InputStream is;

	PNode node;

	public ImageSlide(String title, InputStream streamToImage)
	{
		super(title);
		is = streamToImage;
	}

	public ImageSlide(String title, URL imageURL)
	{
		super(title);
		this.imageURL = imageURL;
	}

	public PNode getSlide()
	{
		if (node == null)
		{
			node = new PNode();

			// Load the image from a file or from a stream
			try
			{
				PNode imagePart;
				if (imageURL != null)
				{
					imagePart = new PImage(imageURL);
				}
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
			}
			catch (IOException e)
			{
				System.err.println("Unable to load image - ignoring");
				e.printStackTrace();
			}

		}

		return node;
	}
}
