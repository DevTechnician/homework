package com.uw.homework252eichmj;

import com.squareup.otto.Bus;

public class BusProvider {
	
	private static final Bus BUS = new Bus();

	  public static Bus getInstance() {
	    return BUS;
	  }

	  private BusProvider() {
	    // No instances.
	  }

}
