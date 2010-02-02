package uk.ac.shef.tiledpres.controller;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import uk.ac.shef.tiledpres.ImageSlide;
import uk.ac.shef.tiledpres.Slide;
import uk.ac.shef.tiledpres.gui.SlideGUI;

/**
 * Controller for the slide display
 * 
 * @author sat
 * 
 */
public class SlideController
{
	/**
	 * Loads the images for the slideshow from a specific directory
	 * 
	 * @param dir
	 *            The directory to load the files from
	 * @return An ordered list of slides
	 */
	public final List<Slide> loadImageSlides(final File dir)
	{
		List<File> fNames = new LinkedList<File>();

		if (dir.exists())
			for (File f : dir.listFiles())
				if (f.getName().toLowerCase().endsWith(".png"))
					fNames.add(f);

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
				// slides.add(new ImageSlide(fNames.get(i), fNames.get(i)));
				slides.add(new ImageSlide(fNames.get(i).getName(), fNames.get(i).toURI().toURL()));
			}
			catch (Exception e)
			{
				System.err.println("Unable to read: " + fNames.get(i));
				e.printStackTrace();
			}
		return slides;
	}

	/**
	 * Main method
	 * 
	 * @param args
	 *            Command lines are ignored
	 * @throws Exception
	 *             if something goes wrong
	 */
	public static void main(final String[] args) throws Exception
	{
		SlideController cont = new SlideController();

		List<Slide> slides = cont.loadImageSlides(new File(args[0]));
		SlideGUI gui = new SlideGUI(slides);
		gui.setVisible(true);
	}
}
