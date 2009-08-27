package uk.ac.shef.slides.controller;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import uk.ac.shef.DataResolver.DataResolver;
import uk.ac.shef.slides.ImageSlide;
import uk.ac.shef.slides.Slide;
import uk.ac.shef.slides.gui.SlideGUI;

public class SlideController
{
	public List<Slide> loadImageSlides()
	{
		List<String> fNames = new LinkedList<String>();

		try
		{
			Collection<String> keys = DataResolver.getKeys("slides");
			for (String f : keys)
			{
				if (f.endsWith(".png"))
					fNames.add(f);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.err.println("Cannot locate slide directory");
		}

		if (fNames.size() == 0)
		{
			System.err.println("Found no slides, exiting");
			System.exit(1);
		}

		Collections.sort(fNames);

		List<Slide> slides = new LinkedList<Slide>();
		for (int i = 0; i < fNames.size(); i++)
			try
			{
				//slides.add(new ImageSlide(fNames.get(i), fNames.get(i)));
				slides.add(new ImageSlide(fNames.get(i), DataResolver.getURL(fNames.get(i))));
			}
			catch (Exception e)
			{
				System.err.println("Unable to read: " + fNames.get(i));
				e.printStackTrace();
			}
		return slides;
	}

	public static void main(String[] args) throws Exception
	{
		SlideController cont = new SlideController();

		List<Slide> slides = cont.loadImageSlides();
		SlideGUI gui = new SlideGUI(slides);
		gui.setVisible(true);
	}
}
