package io.slingcms.core.diagrams.shape;

import io.slingcms.core.diagrams.canvas.mxGraphics2DCanvas;
import io.slingcms.core.diagrams.view.mxCellState;

public interface mxIShape
{
	/**
	 * 
	 */
	void paintShape(mxGraphics2DCanvas canvas, mxCellState state);

}
