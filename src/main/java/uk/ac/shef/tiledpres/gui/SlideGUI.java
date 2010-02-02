package uk.ac.shef.tiledpres.gui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.SwingUtilities;

import uk.ac.shef.tiledpres.Slide;
import edu.umd.cs.piccolo.PNode;
import edu.umd.cs.piccolox.PFrame;

/**
 * The main GUI for the slide display
 * 
 * @author sat
 * 
 */
public class SlideGUI extends PFrame
{
	/** The serial version number */
	private static final long serialVersionUID = -325705316111220691L;

	/** Transition time between slides */
	private static final long WAIT_TIME = 2000;

	/** The slides being displayed */
	private final List<Slide> slides;

	/** The layout manager used in the slide GUI */
	private final LayoutManager layout = new GridLayout();

	/** The maximum X location */
	private double maxX = 0;

	/** The maximum Y location */
	private double maxY = 0;

	/** Mpas the slide bounds to the slide number */
	private final Map<Rectangle2D, Integer> titleToBounds = new HashMap<Rectangle2D, Integer>();

	/** The current slide being displayed */
	private int currSlide = -1;

	/**
	 * Constructor for the slide GUI
	 * 
	 * @param slidesIn
	 *            The list of slides to display
	 */
	public SlideGUI(final List<Slide> slidesIn)
	{
		this.slides = slidesIn;

		setFullScreenMode(true);
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				setVisible(true);
			}
		});

		setupDisplay();
		showAllSlides();

		this.addComponentListener(new ComponentAdapter()
		{
			@Override
			public void componentResized(final ComponentEvent e)
			{
				showAllSlides();
			}
		});

		SlideGUIClickListener clickListener = new SlideGUIClickListener(this);
		this.getCanvas().addInputEventListener(clickListener);
		this.getCanvas().addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(final KeyEvent e)
			{
				System.err.println("KEY = " + e.getKeyCode() + " where ENTER = "
						+ KeyEvent.VK_ENTER);
				System.err.println("MOD = " + e.getModifiers() + " where ALT = "
						+ KeyEvent.ALT_MASK);
				if (e.getKeyCode() == KeyEvent.VK_SPACE)
					showAllSlides();
				else if (e.getKeyCode() == KeyEvent.VK_LEFT)
					prev();
				else if (e.getModifiers() == KeyEvent.ALT_MASK
						&& e.getKeyCode() == KeyEvent.VK_ENTER)
					fullscreen();
				else if (e.getModifiers() != KeyEvent.ALT_MASK
						&& e.getModifiers() != KeyEvent.META_MASK)
					next();
			}
		});
	}

	/**
	 * Sets the GUI to display in full screen mode
	 */
	public final void fullscreen()
	{
		this.setFullScreenMode(!this.isFullScreenMode());
	}

	/**
	 * Transitions to the next slide
	 */
	public final void next()
	{
		currSlide++;
		currSlide %= slides.size();

		for (Rectangle2D bounds : titleToBounds.keySet())
			if (titleToBounds.get(bounds) == currSlide)
				this.getCanvas().getCamera().animateViewToCenterBounds(bounds, true, WAIT_TIME);
	}

	/**
	 * Transitions to the previous slide
	 */
	public final void prev()
	{
		currSlide--;
		for (Rectangle2D bounds : titleToBounds.keySet())
			if (titleToBounds.get(bounds) == currSlide)
				this.getCanvas().getCamera().animateViewToCenterBounds(bounds, true, WAIT_TIME);
	}

	/**
	 * Selects a slide at the given coordinates
	 * 
	 * @param x
	 *            The x coordinate of the selection point
	 * @param y
	 *            The y coordinate of the selection point
	 */
	public final void selectSlide(final double x, final double y)
	{
		for (Rectangle2D bounds : titleToBounds.keySet())
			if (bounds.contains(x, y))
			{
				this.getCanvas().getCamera().animateViewToCenterBounds(bounds, true, WAIT_TIME);
				currSlide = titleToBounds.get(bounds);
			}
	}

	/**
	 * Prepares the display
	 */
	public final void setupDisplay()
	{
		Map<String, Point2D.Double> locMap = layout.layoutSlides(slides);
		maxX = 0;
		maxY = 0;

		for (int i = 0; i < slides.size(); i++)
		{
			PNode node = slides.get(i).getNode();

			this.getCanvas().getLayer().addChild(node);
			Point2D.Double location = locMap.get(slides.get(i).getTitle());
			node.animateToPositionScaleRotation(location.getX(), location.getY(), 1.0, 0.0,
					WAIT_TIME);

			titleToBounds.put(new Rectangle2D.Double(location.getX(), location.getY(), node
					.getWidth(), node.getHeight()), i);

			maxX = Math.max(location.getX() + node.getWidth(), maxX);
			maxY = Math.max(maxY, location.getY() + node.getHeight());
		}

	}

	/**
	 * Displays all the slides
	 */
	public final void showAllSlides()
	{
		this.getCanvas().getCamera().animateViewToCenterBounds(
				new Rectangle2D.Double(0, 0, maxX, maxY), true, WAIT_TIME);
	}
}
