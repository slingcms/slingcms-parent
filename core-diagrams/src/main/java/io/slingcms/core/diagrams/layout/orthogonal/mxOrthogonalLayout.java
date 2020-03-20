/**
 * Copyright (c) 2008-2009, JGraph Ltd
 */
package io.slingcms.core.diagrams.layout.orthogonal;

import io.slingcms.core.diagrams.layout.mxGraphLayout;
import io.slingcms.core.diagrams.layout.orthogonal.model.mxOrthogonalModel;
import io.slingcms.core.diagrams.view.mxGraph;

/**
 *
 */

/**
*
*/
public class mxOrthogonalLayout extends mxGraphLayout
{

  /**
   * 
   */
  protected mxOrthogonalModel orthModel;

  /**
   * Whether or not to route the edges along grid lines only, if the grid
   * is enabled. Default is false
   */
  protected boolean routeToGrid = false;
  
  /**
   * 
   */
  public mxOrthogonalLayout(mxGraph graph)
  {
     super(graph);
     orthModel = new mxOrthogonalModel(graph);
  }

  /**
   * 
   */
  public void execute(Object parent)
  {
     // Create the rectangulation
     
  }

}
