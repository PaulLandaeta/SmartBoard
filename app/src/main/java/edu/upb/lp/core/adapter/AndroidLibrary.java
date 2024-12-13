package edu.upb.lp.core.adapter;

import edu.upb.lp.core.adapter.TextListener;

/**
 * This interface shows all methods available for clients in the Android library
 * for graphical interaction with the user.
 * 
 * @author Alexis Marechal
 * @author Alfredo Villalba
 * @author Luis Frontanilla
 * @author Jordi Ugarte
 */
public interface AndroidLibrary extends UiLibrary, StorageLibrary, MediaLibrary, DeckLibrary {

	/**
	 * Ask the user for text. This method will show a popup window with the
	 * title parameter.
	 * 
	 * @param title
	 *            The title of the window popup to be shown
	 * @param listener
	 *            An object that will wait for the answer of the user and act
	 *            accordingly
	 * @return The text introduced by the user
	 */
	public void askUserText(String title, TextListener listener);
	
	/**
	 * Executes the runnable parameter in r milliseconds
	 * @param r The runnable to be executed
	 * @param ms the time delay
	 */
	public void executeLater(Runnable r, int ms);
}