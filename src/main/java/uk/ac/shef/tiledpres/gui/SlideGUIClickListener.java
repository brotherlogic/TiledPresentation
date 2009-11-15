package uk.ac.shef.tiledpres.gui;

import java.awt.event.KeyEvent;

import edu.umd.cs.piccolo.event.PBasicInputEventHandler;
import edu.umd.cs.piccolo.event.PInputEvent;

/**
 * Listens for clicks
 * 
 * @author sat
 * 
 */
public class SlideGUIClickListener extends PBasicInputEventHandler
{
	/** The associated GUI dispaly */
	private final SlideGUI gui;

	/**
	 * Constructor
	 * 
	 * @param guiIn
	 *            The gui to attach to
	 */
	public SlideGUIClickListener(final SlideGUI guiIn)
	{
		this.gui = guiIn;
	}

	@Override
	public final void mouseClicked(final PInputEvent event)
	{
		gui.selectSlide(event.getPosition().getX(), event.getPosition().getY());
	}

	@Override
	public final void keyPressed(final PInputEvent event)
	{
		System.err.println("KEY = " + event.getKeyCode());
		if (event.getKeyCode() == KeyEvent.VK_LEFT)
			gui.prev();
		else if (event.getKeyCode() != KeyEvent.VK_ALT && event.getKeyCode() != KeyEvent.VK_TAB)
			gui.next();
	}

}
