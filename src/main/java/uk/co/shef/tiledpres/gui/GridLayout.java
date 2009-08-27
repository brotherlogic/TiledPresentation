package uk.ac.shef.slides.gui;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import uk.ac.shef.slides.Slide;

public class GridLayout implements LayoutManager
{
	public Map<String, Double> layoutSlides(List<Slide> slides)
	{
		int gridSize = (int) Math.ceil(Math.sqrt(slides.size()));

		// Layout the thingies left to right then down to up
		double currX = 0;
		double currY = 0;
		double maxY = 0;

		Map<String, Point2D.Double> layout = new TreeMap<String, Double>();
		for (int i = 0; i < slides.size(); i++)
		{
			layout.put(slides.get(i).getTitle(), new Point2D.Double(currX, currY));
			maxY = Math.max(maxY, currY + slides.get(i).getNode().getHeight());

			if ((i + 1) % gridSize == 0)
			{
				currX = 0;
				currY = maxY;
			}
			else
			{
				currX += slides.get(i).getNode().getWidth();
			}
		}

		return layout;

	}

}
