package com.tyh.marketresearch_shichangju.util;

import android.content.Context;

public class MResource {
    /**
     * 根据名字获取布局文件的id
     *
     * @param context
     * @param name
     *            布局文件的名字
     * @return
     */
    public static int getLayoutIdByName(Context context, String name) {
        return getIdByName(context, "layout", name);
    }

    /**
     * 根据名字获取图片的id
     *
     * @param context
     * @param name
     *            图片的名字
     * @return
     */
    public static int getDrawableIdByName(Context context, String name) {
        return getIdByName(context, "drawable", name);
    }

    /**
     * 根据名字获取string.xml文件中字符串的id
     *
     * @param context
     * @param name
     *            字符串的名字
     * @return
     */
    public static int getStringIdByName(Context context, String name) {
        return getIdByName(context, "string", name);
    }

    /**
     * 根据名字获取布局文件中组件的id
     *
     * @param context
     * @param name
     *            组件id属性的名字
     * @return
     */
    public static int getViewIdByName(Context context, String name) {
        return getIdByName(context, "id", name);
    }

    /**
     * 根据名字获取颜色的id
     *
     * @param context
     * @param name
     *            颜色的名字
     * @return
     */
    public static int getColorIdByName(Context context, String name) {
        return getIdByName(context, "color", name);
    }

    /**
     * 根据名字获取尺寸（dimen）的id
     *
     * @param context
     * @param name
     *            名字
     * @return
     */
    public static int getDimenIdByName(Context context, String name) {
        return getIdByName(context, "dimen", name);
    }

    /**
     * 根据资源类型和名字获取资源id
     *
     * @param context
     * @param className
     *            资源类型，如"drawable"/"layout"/"id"/"string"等
     * @param name
     *            资源的名字
     * @return
     */
    private static int getIdByName(Context context, String className,
                                   String name) {
        String packageName = context.getPackageName();
        Class r = null;
        int id = 0;
        try {
            r = Class.forName(packageName + ".R");

            Class[] classes = r.getClasses();
            Class desireClass = null;

            for (int i = 0; i < classes.length; ++i) {
                if (classes[i].getName().split("\\$")[1].equals(className)) {
                    desireClass = classes[i];
                    break;
                }
            }

            if (desireClass != null)
                id = desireClass.getField(name).getInt(desireClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return id;
    }
}
