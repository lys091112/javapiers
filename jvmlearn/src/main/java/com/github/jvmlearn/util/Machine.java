package com.github.jvmlearn.util;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import java.util.List;

//  使用power mock 保证程序不报错
public class Machine {

    public static void main(String[] args) {
        System.out.println("Main ");
        machineInfo();
    }

    public static List<VirtualMachineDescriptor> machineInfo() {
        List<VirtualMachineDescriptor> list = VirtualMachine.list();

        for (VirtualMachineDescriptor descriptor : list) {
            System.out.println("pid: " + descriptor.id() + ":" + descriptor.displayName());
        }
        return list;

    }


}