/*
 * Created on 24/ago/2011
 * Copyright 2011 by Andrea Vacondio (andrea.vacondio@gmail.com).
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
package org.sejda.core.support.prefix.processor;

import static org.junit.Assert.assertEquals;
import static org.sejda.core.support.prefix.model.NameGenerationRequest.nameRequest;

import org.junit.Test;
import org.sejda.model.SejdaFileExtensions;

/**
 * @author Andrea Vacondio
 * 
 */
public class AppendExtensionPrefixProcessorTest {

    private AppendExtensionPrefixProcessor victim = new AppendExtensionPrefixProcessor();

    @Test
    public void testProcess() {
        String prefix = "blabla";
        String expected = "blabla.pdf";
        assertEquals(expected, victim.process(prefix, nameRequest()));
    }

    @Test
    public void testProcessNonDefaultExtension() {
        String prefix = "blabla";
        String expected = "blabla.txt";
        assertEquals(expected, victim.process(prefix, nameRequest(SejdaFileExtensions.TXT_EXTENSION)));
    }

    @Test
    public void testProcessNullRequest() {
        String prefix = "blabla";
        assertEquals(prefix, victim.process(prefix, null));
    }

}
