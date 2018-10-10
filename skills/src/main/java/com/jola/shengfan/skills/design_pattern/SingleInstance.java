package com.jola.shengfan.skills.design_pattern;

/**
 * Created by lenovo on 2018/9/25.
 */

public class SingleInstance {

    private static volatile SingleInstance singleInstance;


    private SingleInstance(){};

//    懒汉-线程安全
    public static SingleInstance getSingleInstance(){
        if (null == singleInstance){
            synchronized (SingleInstance.class){
                if (null == singleInstance){
                    singleInstance  = new SingleInstance();
                }
            }
        }
        return singleInstance;
    }

//    静态内部类 ：jvm机制
    private static class InstanceHolde {
        private static SingleInstance singleInstance = new SingleInstance();
    }

    public static SingleInstance getSingleInstaceJvm(){
        return InstanceHolde.singleInstance;
    }


    public enum Singleton{

        INSTANCE;

        private String objectName ;

        public String getObjectName(){
            return objectName;
        }

        public void setObjectName(String objectName){
            this.objectName = objectName;
        }

        public static void main(String[] args){
            Singleton instanceOne = Singleton.INSTANCE;
            instanceOne.setObjectName("firstName");
            Singleton instanceTwo = Singleton.INSTANCE;
            instanceTwo.setObjectName("secondName");

            System.out.println(instanceOne.getObjectName());
            System.out.println(instanceTwo.getObjectName());
        }
    }


}
