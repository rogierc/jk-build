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

package nl.teslanet.test.jkbuild;


/**
 * The Class JkBuild
 *
 */
public class JkBuild
{
    
    /**
     * the ok value
     */
    private boolean ok;
    
    /**
     * Default constructor
     */
    public JkBuild()
    {
        ok= false;
    }

    /**
     * Is it OK?
     * @return true
     */
    public boolean isOk()
    {
        return ok;
    }
    
    /**
     * Set ok
     * @param value the new value
     */
    public void setOk( boolean value )
    {
        ok= value;
    }
}
