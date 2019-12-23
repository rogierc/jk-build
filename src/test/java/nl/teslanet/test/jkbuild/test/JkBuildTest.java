/*******************************************************************************
 * Copyright (c) 2019 (teslanet.nl) Rogier Cobben.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License - v 2.0 
 * which accompanies this distribution.
 * 
 * The Eclipse Public License is available at
 *    http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *    (teslanet.nl) Rogier Cobben - initial creation
 ******************************************************************************/

package nl.teslanet.test.jkbuild.test;


import static org.junit.Assert.assertTrue;

import org.junit.Test;

import nl.teslanet.test.jkbuild.JkBuild;


/**
 * The Class JkBuild
 *
 */
public class JkBuildTest
{
    /**
     * Default constructor
     */
    public JkBuildTest()
    {
        //nothing to be done
    }

    /**
     * Do test
     */
    @Test
    public void test1()
    {
        JkBuild myObject= new JkBuild();

        assertTrue( myObject.isOk() );
    }
}
