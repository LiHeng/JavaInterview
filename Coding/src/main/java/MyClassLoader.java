import java.io.*;
import java.util.Map;

import test.mi.LinkedList;

public class MyClassLoader extends ClassLoader {

    private String path;

    public MyClassLoader(ClassLoader parent, String path){
        super(parent);
        this.path = path;
    }

    public MyClassLoader(String path){
        this.path = path;
    }

    @Override
    public String toString() {
        return "MyClassLoader";
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> clazz = null;
        ClassLoader system = getSystemClassLoader();
        try {
            clazz = system.loadClass(name);
        } catch (Exception e) {
            // ignore
        }
        if (clazz != null)
            return clazz;
        clazz = findClass(name);
        return clazz;
    }

    public byte[] loadClassData(String name){
        try {
            name = name.replace(".", File.separator);
            FileInputStream is = new FileInputStream(new File(path+name+".class"));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int b;
            while((b = is.read())!=-1){
                bos.write(b);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte data[] = loadClassData(name);
        return defineClass(name, data, 0, data.length);
    }

    public static void main(String[] args) throws Exception{
        ClassLoader parentClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(parentClassLoader);
        MyClassLoader classLoader = new MyClassLoader(parentClassLoader,"/Users/hangli/IdeaProjects/Study/Coding/target/classes/");
        Class<?> clz =  classLoader.loadClass("test.mi.LinkedList");
        System.out.println(clz.toString());
        System.out.println(clz.getClassLoader());

        //new ClassLoaderThread(classLoader).start();

    }

//    static class ClassLoaderThread extends Thread{
//
//        public ClassLoaderThread(ClassLoader classLoader){
//            setContextClassLoader(classLoader);
//        }
//
//        @Override
//        public void run() {
//            System.out.println("context: "+getContextClassLoader());
//            LinkedList list = new LinkedList();
//            System.out.println(list.getClass().getClassLoader());
//        }
//    }
}

