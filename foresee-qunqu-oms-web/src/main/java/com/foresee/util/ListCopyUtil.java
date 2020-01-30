package com.foresee.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

public class ListCopyUtil {
     
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List CopyList(List sourcelist,Object obj){
		Class target=obj.getClass();
        List<Object> result = new ArrayList();
        if (sourcelist != null) {
            for (Object o : sourcelist) {
                try {
                    Object d = target.newInstance();
                    BeanUtils.copyProperties(o, d); 
                    result.add(d);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
		return result;
	}
	
	
	
}
