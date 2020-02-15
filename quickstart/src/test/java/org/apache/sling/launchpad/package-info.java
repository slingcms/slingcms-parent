/**
 * <h1>Smoke tests for the Sling Launchpad</h1>
 * 
 * <p>This package contains a minimal set of tests for the Sling launchpad. The
 * tests validate that the launchpad is correctly assembled and that there are
 * no obvious mistakes such as bundles that can't be wired. More extensive
 * tests must be placed in specific test projects.</p>
 * 
 * <p>The launchpad tests don't depend on other Sling bundles,to prevent circular
 * dependencies. As such, there is some duplication of code ( at least intent, if 
 * not implementation ) with some of the testing projects. This is another 
 * argument for keeping the tests minimal.</p>
 */
package org.apache.sling.launchpad;