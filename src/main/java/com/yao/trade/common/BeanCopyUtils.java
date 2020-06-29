package com.yao.trade.common;

import org.springframework.beans.ExtendedBeanInfoFactory;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;
import org.springframework.util.CollectionUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: 周杨
 * @Date: 2018年7月23日
 * @Description: 对象拷贝工具
 */
public class BeanCopyUtils {


    /**
     * 对象拷贝
     *
     * @param source 对象源
     * @param targer 目标对象
     */
    public static void copy(Object source, Object targer) {
        BeanCopier beanCopier = BeanCopier.create(source.getClass(), targer.getClass(), false);
        beanCopier.copy(source, targer, null);
    }

    /**
     * 对象拷贝
     *
     * @param source    对象源
     * @param targer    目标对象
     * @param converter 转换器用于转换不同数据类型的字段
     */
    public static void copy(Object source, Object targer, Converter converter) {
        BeanCopier beanCopier = BeanCopier.create(source.getClass(), targer.getClass(), true);
        beanCopier.copy(source, targer, converter);
    }

    /**
     * 对象拷贝并创建target对象
     *
     * @param source 对象源
     * @param targer 目标对象
     */
    public static <T> T copy(Object source, Class<T> targer) {
        if (source != null) {
            T instance = null;
            try {
                instance = targer.newInstance();
                copy(source, instance);
                return instance;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    /**
     * 对象拷贝并创建target对象
     *
     * @param source    对象源
     * @param targer    目标对象
     * @param converter 转换器用于转换不同数据类型的字段
     */
    public static <T> T copy(Object source, Class<T> targer, Converter converter) {
        if (source != null) {
            T instance = null;
            try {
                instance = targer.newInstance();
                copy(source, instance, converter);
                return instance;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    /**
     * 集合对象拷贝并创建target对象
     *
     * @param sources 对象源
     * @param targer  目标对象
     */
    public static <T> List copyList(Collection sources, Class<T> targer) {
        List<T> list = new ArrayList<T>();
        if (!CollectionUtils.isEmpty(sources)) {
            for (Object source : sources) {
                T t = copy(source, targer);
                list.add(t);
            }
        }
        return list;
    }

    /**
     * 集合对象拷贝并创建target对象
     *
     * @param sources   对象源
     * @param targer    目标对象
     * @param converter 转换器用于转换不同数据类型的字段
     */
    public static <T> List copyList(Collection sources, Class<T> targer, Converter converter) {
        List<T> list = new ArrayList<T>();
        if (!CollectionUtils.isEmpty(sources)) {
            for (Object source : sources) {
                T t = copy(source, targer, converter);
                list.add(t);
            }
        }
        return list;
    }
}
