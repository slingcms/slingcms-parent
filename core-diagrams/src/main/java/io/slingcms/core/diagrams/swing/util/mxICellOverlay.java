package io.slingcms.core.diagrams.swing.util;

import io.slingcms.core.diagrams.util.mxRectangle;
import io.slingcms.core.diagrams.view.mxCellState;

public interface mxICellOverlay
{

	/**
	 * 
	 */
	mxRectangle getBounds(mxCellState state);

}
