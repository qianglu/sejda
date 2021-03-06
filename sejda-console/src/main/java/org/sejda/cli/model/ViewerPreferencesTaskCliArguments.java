/*
 * Created on Jun 30, 2011
 * Copyright 2011 by Eduard Weissmann (edi.weissmann@gmail.com).
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
package org.sejda.cli.model;

import org.sejda.conversion.PdfDirectionAdapter;
import org.sejda.conversion.PdfDuplexAdapter;
import org.sejda.conversion.PdfNonFullScreenPageModeAdapter;
import org.sejda.conversion.PdfPageLayoutAdapter;
import org.sejda.conversion.PdfPageModeAdapter;
import org.sejda.conversion.PdfPrintScalingAdapter;

import uk.co.flamingpenguin.jewel.cli.CommandLineInterface;
import uk.co.flamingpenguin.jewel.cli.Option;

/**
 * Specifications for command line options of the ViewerPreferences task
 * 
 * @author Eduard Weissmann
 * 
 */
@CommandLineInterface(application = TaskCliArguments.EXECUTABLE_NAME + " setviewerpreferences")
public interface ViewerPreferencesTaskCliArguments extends CliArgumentsWithPdfAndDirectoryOutput,
        CliArgumentsWithPrefixableOutput {
    @Option(description = "center of the screen (optional)")
    boolean isCenterWindow();

    @Option(description = "display document title metadata as window title (optional)")
    boolean isDisplayDocTitle();

    @Option(shortName = "d", description = "direction {l2r, r2l} (optional)")
    PdfDirectionAdapter getDirection();

    boolean isDirection();

    @Option(description = "resize the window to fit the page size (optional)")
    boolean isFitWindow();

    @Option(description = "hide the menu bar (optional)")
    boolean isHideMenu();

    @Option(description = "hide the toolbar (optional)")
    boolean isHideToolbar();

    @Option(description = "hide user interface elements (optional)")
    boolean isHideWindowUI();

    @Option(shortName = "l", description = "layout for the viewer. { onecolumn, singlepage, twocolumnl, twocolumnr, twopagel, twopager}  (optional)", defaultValue = "singlepage")
    PdfPageLayoutAdapter getLayout();

    @Option(shortName = "m", description = "open mode for the viewer {attachments, fullscreen, none, ocontent, outlines, thumbs}. If omitted it uses none (optional)", defaultValue = "none")
    PdfPageModeAdapter getMode();

    @Option(shortName = "n", description = "non full screen mode for the viewer when exiting full screen mode {nfsnone, nfsocontent, nfsoutlines, nfsthumbs}."
            + " If omitted it uses 'nfsnone' (optional)", defaultValue = "nfsnone")
    PdfNonFullScreenPageModeAdapter getNfsMode();

    // pdfsam-incompatibility: is called noPrintScaling in pdfsam
    @Option(shortName = "s", description = "page scaling in print dialog {none, app_default} (optional)")
    PdfPrintScalingAdapter getPrintScaling();

    boolean isPrintScaling();

    // pdfsam-incompatibility: doesnt exist in pdfsam
    @Option(shortName = "x", description = "paper handling options to use when printing the file from the print dialog: {simplex, duplex_flip_short_edge, duplex_flip_long_edge} (optional)")
    PdfDuplexAdapter getDuplex();

    boolean isDuplex();
}
