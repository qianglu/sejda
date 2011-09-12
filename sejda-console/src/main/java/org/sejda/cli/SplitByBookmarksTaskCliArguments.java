/*
 * Created on Sep 3, 2011
 * Copyright 2010 by Eduard Weissmann (edi.weissmann@gmail.com).
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */
package org.sejda.cli;

import uk.co.flamingpenguin.jewel.cli.CommandLineInterface;
import uk.co.flamingpenguin.jewel.cli.Option;

/**
 * Specifications for command line options of the SplitByGotoLevel task
 * 
 * @author Eduard Weissmann
 * 
 */
@CommandLineInterface(application = SejdaConsole.EXECUTABLE_NAME + " splitbybookmarks")
public interface SplitByBookmarksTaskCliArguments extends CliArgumentsWithDirectoryOutput {

    // pdf-same incompat this is part of a larger task: split
    @Option(shortName = "l", description = "bookmarks depth to split at (required)")
    Integer getBookmarkLevel();

    @Option(shortName = "e", description = "matching regular expression (optional)")
    String getMatchingRegEx();

    boolean isMatchingRegEx();
}