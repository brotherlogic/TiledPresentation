package uk.ac.shef.slides.gui;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;

import uk.ac.shef.slides.Slide;

public interface LayoutManager
{
	public Map<String, Point2D.Double> layoutSlides(List<Slide> slides);
}
