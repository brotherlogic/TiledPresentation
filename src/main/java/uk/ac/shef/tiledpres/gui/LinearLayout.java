package uk.ac.shef.tiledpres.gui;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import uk.ac.shef.tiledpres.Slide;

/**
 * Describes the slides layed out purely in a linear style
 * 
 * @author sat
 * 
 */
public class LinearLayout implements LayoutManager
{
	@Override
	public final Map<String, Point2D.Double> layoutSlides(final List<Slide> slides)
	{
		double y = 0;
		double x = 0;

		Map<String, Point2D.Double> layout = new TreeMap<String, Double>();

		for (int i = 0; i < slides.size(); i++)
		{
			layout.put(slides.get(i).getTitle(), new Point2D.Double(x, y));
			x += slides.get(i).getNode().getWidth();
		}

		return layout;
	}

}
