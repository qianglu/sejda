/*
 * Created on 06/jun/2010
 *
 * Copyright 2010 by Andrea Vacondio (andrea.vacondio@gmail.com).
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
package org.sejda.impl.itext.component;

import static org.sejda.model.rotation.Rotation.getRotation;

import org.sejda.model.rotation.PageRotation;
import org.sejda.model.rotation.RotationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.pdf.PdfDictionary;
import com.lowagie.text.pdf.PdfName;
import com.lowagie.text.pdf.PdfNumber;
import com.lowagie.text.pdf.PdfReader;

/**
 * Handles rotations on a given PdfReader
 * 
 * @author Andrea Vacondio
 * 
 */
public final class PdfRotator implements OngoingRotation {

    private static final Logger LOG = LoggerFactory.getLogger(PdfRotator.class);

    private PdfReader reader;
    private PageRotation rotation;

    private PdfRotator(PageRotation rotation) {
        this.rotation = rotation;
    }

    /**
     * DSL entry point to apply a rotation
     * <p>
     * <code>applyRotation(rotation).to(reader);</code>
     * </p>
     * 
     * @param rotation
     * @return the ongoing apply rotation exposing methods to set the reader you want to apply the rotation to.
     */
    public static OngoingRotation applyRotation(PageRotation rotation) {
        return new PdfRotator(rotation);
    }

    public void to(PdfReader reader) {
        this.reader = reader;
        executeRotation();
    }

    /**
     * Apply the rotation to the dictionary of the given {@link PdfReader}
     */
    private void executeRotation() {
        RotationType type = rotation.getRotationType();
        LOG.debug("Applying rotation of {} to pages {}", rotation.getRotation().getDegrees(), type);
        if (RotationType.SINGLE_PAGE.equals(type)) {
            apply(rotation.getPageNumber());
        } else {
            for (int j = 1; j <= reader.getNumberOfPages(); j++) {
                apply(j);
            }
        }
    }

    /**
     * apply the rotation to the given page if necessary
     * 
     * @param pageNmber
     */
    private void apply(int pageNmber) {
        if (rotation.accept(pageNmber)) {
            PdfDictionary dictionary = reader.getPageN(pageNmber);
            dictionary.put(PdfName.ROTATE,
                    new PdfNumber(rotation.getRotation().addRotation(getRotation(reader.getPageRotation(pageNmber)))
                            .getDegrees()));
        }
    }
}
