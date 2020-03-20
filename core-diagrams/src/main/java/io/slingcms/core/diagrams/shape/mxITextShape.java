/**
 * Copyright (c) 2010, Gaudenz Alder, David Benson
 */
package io.slingcms.core.diagrams.shape;

import io.slingcms.core.diagrams.canvas.mxGraphics2DCanvas;
import io.slingcms.core.diagrams.view.mxCellState;

import java.util.Map;

public interface mxITextShape
{
	/**
	 * 
	 */
	void paintShape(mxGraphics2DCanvas canvas, String text, mxCellState state, Map<String, Object> style);

}
