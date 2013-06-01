// Copyright (c) 2003-2012, Jodd Team (jodd.org). All Rights Reserved.

package jodd.examples.beanutil;

import jodd.bean.BeanUtilBean;
import jodd.typeconverter.TypeConverter;
import jodd.typeconverter.TypeConverterManagerBean;

/**
 * Custom TypeConverterManagerBean with use of BeanUtilBean.
 */
public class CustomTypeManager {
	
	static class Bean {
		public Integer value;
	}

	public static void main(String[] args) {
		TypeConverterManagerBean typeConverterManagerBean = new TypeConverterManagerBean();

		typeConverterManagerBean.register(Integer.class, new TypeConverter<Integer>() {
			public Integer convert(Object value) {
				return Integer.valueOf(73);
			}
		});

		BeanUtilBean beanUtilBean = new BeanUtilBean();
		beanUtilBean.setTypeConverterManager(typeConverterManagerBean);

		// usage
		
		Bean bean = new Bean();
		beanUtilBean.setProperty(bean, "value", "-12");

		System.out.println(bean.value);		// 73
	}
}
