package hapyboy.tools.colls;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 泛型工具类
 * 
 * @author 赵利波 <zhaolibo@cyanclone.com>
 */
public final class GenericUtil<T>
{
	@SuppressWarnings("unchecked")
	public final Class<T> getGeneric(Object obj)
	{
//		int g =  obj.getClass().getTypeParameters().length;
//		if(g > 0){
//			throw new RuntimeException("必须确定父类泛型的具体类型，并且不允许在后继的子类中加入泛型...");
//		}
		
		Type t = obj.getClass().getGenericSuperclass();

		if (t == null)
		{
			throw new RuntimeException("无法获得类的泛型！");
		}

		if (t instanceof ParameterizedType)
		{
			Type[] args = ((ParameterizedType) t).getActualTypeArguments();
			if (args != null && args.length > 0)
			{
				Class<T> clz = (Class<T>) args[0];
				return clz;
			}

		}
		throw new RuntimeException("无法获得类的泛型！");
	}
}
