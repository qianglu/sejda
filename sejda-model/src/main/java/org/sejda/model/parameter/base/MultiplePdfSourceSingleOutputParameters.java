/*
 * Created on 14/set/2011
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
package org.sejda.model.parameter.base;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.sejda.model.output.SingleTaskOutput;
import org.sejda.model.validation.constraint.ValidSingleOutput;

/**
 * Provides a skeletal implementation for parameter classes having multiple pdf source as input and producing a {@link SingleTaskOutput}.
 * 
 * @author Andrea Vacondio
 * 
 */
@ValidSingleOutput
public abstract class MultiplePdfSourceSingleOutputParameters extends MultiplePdfSourceParameters implements
        SingleOutputTaskParameters {

    private String outputName;
    @Valid
    @NotNull
    private SingleTaskOutput<?> output;

    public String getOutputName() {
        return outputName;
    }

    public SingleTaskOutput<?> getOutput() {
        return output;
    }

    public void setOutput(SingleTaskOutput<?> output) {
        this.output = output;
    }

    /**
     * @param outputName
     *            the outputName to be used when the output is not a file destination
     */
    public void setOutputName(String outputName) {
        this.outputName = outputName;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().appendSuper(super.hashCode()).append(outputName).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MultiplePdfSourceSingleOutputParameters)) {
            return false;
        }
        MultiplePdfSourceSingleOutputParameters parameter = (MultiplePdfSourceSingleOutputParameters) other;
        return new EqualsBuilder().appendSuper(super.equals(other)).append(outputName, parameter.getOutputName())
                .isEquals();
    }
}
