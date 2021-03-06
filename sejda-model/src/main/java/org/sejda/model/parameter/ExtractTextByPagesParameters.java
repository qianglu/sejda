/*
 * Copyright 2013 by Edi Weissmann (edi.weissmann@gmail.com).
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
package org.sejda.model.parameter;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import javax.validation.Valid;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.sejda.common.collection.NullSafeSet;
import org.sejda.model.parameter.base.SinglePdfSourceMultipleOutputParameters;
import org.sejda.model.pdf.page.PageRange;
import org.sejda.model.pdf.page.PageRangeSelection;
import org.sejda.model.pdf.page.PagesSelection;
import org.sejda.model.validation.constraint.NoIntersections;

/**
 * Parameters for extract text by pages manipulation. Accepts a set of page numbers, where the split occurs. Works similar to SplitByPages, with the difference that the output will
 * be extracted text files.
 * 
 * @author Edi Weissmann
 */
@NoIntersections
public class ExtractTextByPagesParameters extends SinglePdfSourceMultipleOutputParameters implements
        PageRangeSelection, PagesSelection {

    @Valid
    private final Set<PageRange> pageSelection = new NullSafeSet<PageRange>();
    private String textEncoding;

    public String getTextEncoding() {
        return textEncoding;
    }

    public void setTextEncoding(String textEncoding) {
        this.textEncoding = textEncoding;
    }

    /**
     * @return an unmodifiable view of the pageSelection
     */
    public Set<PageRange> getPageSelection() {
        return Collections.unmodifiableSet(pageSelection);
    }

    public void addPageRange(PageRange range) {
        pageSelection.add(range);
    }

    public void addAllPageRanges(Collection<PageRange> ranges) {
        pageSelection.addAll(ranges);
    }

    /**
     * @return true if page selection for this input contains all the pages of the input source.
     */
    public boolean isAllPages() {
        return pageSelection.isEmpty();
    }

    /**
     * @param totalNumberOfPage
     *            the number of pages of the document (upper limit).
     * @return the selected set of pages. Iteration ordering is predictable, it is the order in which elements were inserted into the {@link PageRange} set.
     * @see PagesSelection#getPages(int)
     */
    public Set<Integer> getPages(int totalNumberOfPage) {
        if (pageSelection.isEmpty()) {
            return new PageRange(1).getPages(totalNumberOfPage);
        }
        Set<Integer> retSet = new NullSafeSet<Integer>();
        for (PageRange range : getPageSelection()) {
            retSet.addAll(range.getPages(totalNumberOfPage));
        }
        return retSet;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().appendSuper(super.hashCode()).append(pageSelection).append(textEncoding)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ExtractTextByPagesParameters)) {
            return false;
        }
        ExtractTextByPagesParameters parameter = (ExtractTextByPagesParameters) other;
        return new EqualsBuilder().appendSuper(super.equals(other)).append(pageSelection, parameter.pageSelection)
                .append(textEncoding, parameter.getTextEncoding()).isEquals();
    }

}
