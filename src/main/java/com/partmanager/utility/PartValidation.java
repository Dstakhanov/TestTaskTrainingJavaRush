package com.partmanager.utility;

import com.partmanager.model.Part;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PartValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Part.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Part part = (Part)target;
        String partName = part.getPartName();
        int partAmount  = part.getPartAmount();

        if (partName == null){
            errors.rejectValue("partName", "name.required", "Error. Name is missing");
        }
        if (partName.length() < 2){
            errors.rejectValue("partName", "name.anorm", "Error. Name less than 2 symbols");
        }
        if (partName.length() > 150){
            errors.rejectValue("partName", "name.anorm2", "Error. Name more than 150 symbols");
        }
        if (partAmount < 1){
            errors.rejectValue("partAmount", "amount.required", "Error. Amount less than 1");
        }

    }
}
