/*
 * Created on 23/ago/2011
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
package org.sejda.model.validation.validator;

import java.io.File;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.sejda.model.validation.constraint.IsFile;

/**
 * Constraint validating the an instance of {@link File} is actually a file and not a directory.
 * 
 * @author Andrea Vacondio
 * 
 */
public class FileValidator implements ConstraintValidator<IsFile, File> {

    public void initialize(IsFile constraintAnnotation) {
        // on purpose
    }

    public boolean isValid(File value, ConstraintValidatorContext context) {
        if (value != null && value.exists()) {
            return value.isFile();
        }
        return true;
    }

}
