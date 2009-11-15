package uk.ac.shef.tiledpres.gui;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;

import uk.ac.shef.tiledpres.Slide;

/**
 * Manages the layout of the slides
 * 
 * @author sat
 * 
 */
public interface LayoutManager
{
	/**
	 * Lays out the slides
	 * 
	 * @param slides
	 *            The slides to layout
	 * @return A Map from the name of the slide to the location that the slide
	 *         should be displayed at
	 */
	Map<String, Point2D.Double> layoutSlides(final List<Slide> slides);
}
