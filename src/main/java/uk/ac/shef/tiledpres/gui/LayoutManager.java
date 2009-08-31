package uk.ac.shef.tiledpres.gui;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;

import uk.ac.shef.tiledpres.Slide;

public interface LayoutManager
{
	public Map<String, Point2D.Double> layoutSlides(List<Slide> slides);
}
