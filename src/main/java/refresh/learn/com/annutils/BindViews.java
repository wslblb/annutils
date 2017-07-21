package refresh.learn.com.annutils;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Created by Administrator on 2017/7/20 0020.
 */

public class BindViews {
    public static void bind(final Activity activity){
        Class activityClass =  activity.getClass();  //获取Activity的Class对象
        Field[] fields = activityClass.getDeclaredFields();    //返回所有字段，getField只返回可访问的字段
        for(int i=0 ; i<fields.length;i++){              //获取所有属性
            Field field = fields[i];                  //循环取得属性
            field.setAccessible(true);               //设置私有属性可访问；
            BindField annotation = field.getAnnotation(BindField.class);  //取得属性上的注解
            if(annotation == null){
                continue;
            }
            int id = annotation.value();             //获取注解上的值
            View fieldView = null;
            try {
                fieldView = (View) activityClass.getMethod("findViewById",int.class).invoke(activity,id);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            try {

                Log.i("=bind==",fieldView+"");
                Log.i("=bind==",id+"");

                field.set(activity,fieldView);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        Method[] methods = activityClass.getDeclaredMethods();    //返回所有方法，包括私有的
        for(int i=0;i<methods.length;i++){
            final Method method = methods[i];
            method.setAccessible(true);                         //暴力反射，设置可访问
            BindClick bindClick = method.getAnnotation(BindClick.class);  //获取方法上的注解
            if(bindClick == null){
                continue;
            }
            int clickId = bindClick.value();
        }
    }
}
