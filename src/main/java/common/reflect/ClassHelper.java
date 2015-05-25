package common.reflect;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangxi
 * @created 13-1-17
 */
public class ClassHelper {
    private static final Logger LOG = LoggerFactory.getLogger(ClassHelper.class);


    public static String simpleSignature(Method method) {
        StringBuilder sb = new StringBuilder(method.getName() + "(");
        for (Type type : method.getGenericParameterTypes()) {
            sb.append(simpleSignature(type));
        }
        sb.append(")");
        // System.out.println(method + ":" + sb.toString());
        return sb.toString();
    }

    private static String simpleSignature(Type type) {
        if (type instanceof GenericArrayType) {
            GenericArrayType real = (GenericArrayType) type;
            return "[" + simpleSignature(real.getGenericComponentType());
        } else if (type instanceof ParameterizedType) {
            ParameterizedType real = (ParameterizedType) type;
            if (real.getRawType() instanceof Class<?>) {
                Class<?> clazz = (Class<?>) real.getRawType();
                if (Collection.class.isAssignableFrom(clazz)) {
                    return "[" + simpleSignature(real.getActualTypeArguments()[0]);
                } else if (Map.class.isAssignableFrom(clazz)) {
                    assert (real.getActualTypeArguments().length == 2);
                    return "<" + simpleSignature(real.getActualTypeArguments()[0]) + "," + simpleSignature(real.getActualTypeArguments()[1]) + ">";
                }
            }
            StringBuilder sb = new StringBuilder(simpleSignature(real.getRawType()));
            for (Type t : real.getActualTypeArguments()) {
                sb.append(simpleSignature(t));
            }
            return "<" + sb.toString() + ">";
        } else if (type instanceof Class<?>) {
            Class<?> real = (Class<?>) type;
            if (real == boolean.class || real == Boolean.class) {
                return "Z";
            } else if (real == char.class || real == Character.class) {
                return "C";
            } else if (real == byte.class || real == Byte.class) {
                return "B";
            } else if (real == short.class || real == Short.class) {
                return "S";
            } else if (real == int.class || real == Integer.class) {
                return "I";
            } else if (real == long.class || real == Long.class) {
                return "J";
            } else if (real == float.class || real == Float.class) {
                return "F";
            } else if (real == double.class || real == Double.class) {
                return "D";
            } else if (String.class.isAssignableFrom(real)) {
                return "s";
            } else if (real.isArray()) {
                return "[" + simpleSignature(real.getComponentType());
            } else if (Object.class.isAssignableFrom(real)) {
                return "O";
            }
        } else if (type instanceof TypeVariable) {
            TypeVariable real = (TypeVariable) type;
            Type bound = real.getBounds()[0];
            if (bound instanceof Class<?> && Object.class.isAssignableFrom((Class<?>) bound)) {
                return "O";
            }
        } else if (type instanceof WildcardType) {
            WildcardType real = (WildcardType) type;
            Type[] lower = real.getLowerBounds();
            Type[] upper = real.getUpperBounds();
            Type bound = (lower.length != 0 ? lower[0] : (upper.length != 0 ? upper[0] : null));
            if (bound instanceof Class<?> && Object.class.isAssignableFrom((Class<?>) bound)) {
                return "O";
            }
        }
        return "U";
    }
}
