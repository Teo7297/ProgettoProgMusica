/*
 * ReadFilenameFilter.java 0.1.0.3 24th January 2001
 *
 * Copyright (C) 2000 Adam Kirby
 *
 * <This Java Class is part of the jMusic API version 1.5, March 2004.>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 *
 * See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package jm.util;

import java.io.File;
import java.io.FilenameFilter;

/**
 * FilenameFilter used by the AWT read buttons to display only jm and MIDI
 * files.  Apparently this only works in the SunOS implementation of the JVM.
 *
 * @author Adam Kirby
 * @version 1.0,Sun Feb 25 18:43:58  2001
 *
 * @see ReadFileButton
 * @see ReadFolderButton
 */
class ReadFilenameFilter implements FilenameFilter {

    /**
     * Tests whether the specified file has a jm or MIDI file extension
     *
     * @param dir   File describing the directory where the file is stored
     * @param name  String of the filename
     * @return      true if specified file ends with ".mid", ".midi" or ".jm"
     *              and does not begin with "."
     */
    public boolean accept(final File dir, final String name) {
        return !name.startsWith(".") && (name.endsWith(".mid")
                || name.endsWith(".midi")
                || name.endsWith(".jm"));
    }
}
