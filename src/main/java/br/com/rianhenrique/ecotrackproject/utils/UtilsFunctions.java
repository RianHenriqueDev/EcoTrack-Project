package br.com.rianhenrique.ecotrackproject.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.stream.Stream;

public class UtilsFunctions {


    public static String[] getNameProperties(Object data) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(data);

        return Stream.of(beanWrapper.getPropertyDescriptors()).map(FeatureDescriptor::getName).filter(name -> beanWrapper.getPropertyValue(name) == null).toArray(String[]::new);
    }
}
