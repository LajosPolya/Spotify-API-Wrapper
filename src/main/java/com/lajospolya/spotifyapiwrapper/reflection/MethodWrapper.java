package com.lajospolya.spotifyapiwrapper.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodWrapper
{
    private Method method;

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
