package com.qa.tdl_project.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TDLBeanUtils {

	public static void merge(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

	private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSourceObject = new BeanWrapperImpl(source);

        Set<String> propertyNames = new HashSet<>();
        for (PropertyDescriptor propertyDescriptors : wrappedSourceObject.getPropertyDescriptors()) {
            if (wrappedSourceObject.getPropertyValue(propertyDescriptors.getName()) == null)
                propertyNames.add(propertyDescriptors.getName());
        }
        return propertyNames.toArray(new String[propertyNames.size()]);
    }
}

