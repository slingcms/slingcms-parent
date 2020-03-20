package io.slingcms.core.diagrams.shape;

import io.slingcms.core.diagrams.canvas.mxGraphics2DCanvas;
import io.slingcms.core.diagrams.util.mxPoint;
import io.slingcms.core.diagrams.view.mxCellState;

public interface mxIMarker
{
	/**
	 * 
	 */
	mxPoint paintMarker(mxGraphics2DCanvas canvas, mxCellState state, String type, mxPoint pe, double nx, double ny, double size, boolean source);

}
