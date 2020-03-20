package io.slingcms.core.diagrams.io.gliffy.model;

import io.slingcms.core.diagrams.model.mxCell;

public class GliffyLayer {
	public String guid;

	public int order;

	public String name;

	public boolean active;
	
	public boolean locked;
	
	public boolean visible;

	public int nodeIndex;

	public mxCell mxObject;// the mxCell this gliffy layer got converted into
	
	public GliffyLayer() 
	{
	}
}
