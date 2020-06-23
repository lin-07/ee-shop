package cn.itcast.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class MyBeanUtils {

    public static void populate(Object bean, Map<String,String[]> properties){
        try{
            // 注册时间转换器
            ConvertUtils.register(new Converter() {
                @Override
                public Object convert(Class aClass, Object o) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try{
                        return simpleDateFormat.parse(o.toString());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return null;
                }
            }, Date.class);
            // 封装数据
            BeanUtils.populate(bean,properties);
        }catch (Exception e){
            // e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static <T> T populate(Class<T> beanClass, Map<String,String[]> properties){
        try{
            T bean = beanClass.newInstance();
            populate(bean,properties);
            return bean;
        }catch (Exception e){
            // e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
