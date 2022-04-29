package com.example.shelterapp.utils;

import com.example.shelterapp.animal.model.enums.ESpecies;
import uk.co.jemos.podam.common.AttributeStrategy;

import java.lang.annotation.Annotation;
import java.util.List;

public class StringifySpecies implements AttributeStrategy<String> {

    @Override
    public String getValue(Class<?> attrType, List<Annotation> attrAnnotations) {
        return ESpecies.DOG.getName();
    }
}
