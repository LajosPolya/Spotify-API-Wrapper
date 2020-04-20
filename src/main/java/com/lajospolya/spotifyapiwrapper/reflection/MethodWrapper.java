package com.lajospolya.spotifyapiwrapper.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Lajos Polya
 *
 * This wraps java.lang.reflect.Method to allow for easier unit testing since
 * java.lang.reflect.Method is a final class it cannot be mocked
 */
public class MethodWrapper
{
    private final Method method;

    public MethodWrapper(Method method)
    {
        this.method = method;
    }

    public void setAccessible(Boolean accessible)
    {
        this.method.setAccessible(accessible);
    }

    public Object invoke(Object invokeOn, Object... arg) throws InvocationTargetException, IllegalAccessException
    {
        return method.invoke(invokeOn, arg);
    }
}
