/**
 * 类加载的几个阶段
 * 加载 --> 验证（Verification) --> 准备 (Preparation) --> 解析 (Resolution)
 * --> 初始化 (Initiallization) --> 使用 (Use） --> 卸载 (Unloading)
 *
 * 解析阶段有时候会在初始化后再执行
 *
 * 加载： 分3部，
 *
 * <p>
 * 类的初始化:有且只有的5种对类初始化的情况
 * 1. 遇到 new, putstatic, getstatic, invokestatic 这四个指令时，需要先触发类的初始化。 关键字的常用场景： 使用new初始化实例对象，获取静态
 * 变量，为静态变量赋值时（被final修饰的除外，已经被放到常量池中），  掉哟过一个类静态方法时
 * 2. 使用 java.lang.reflect 对类进行反射调用的时候，如果类没有初始化，则触发初始化
 * 3. 当初始化一个类的时候，如果父类没有初始化，会先初始化父类
 * 4. 虚拟机启动时，需要用户指定一个主类（包含main方法的类）， 虚拟机会先初始化这个主类
 * 5. 使用ＪＤＫ1.7动态语言支持时， 如果一个java.lang.invoke.MethodHandle 实例最后的解析结果是REF_static, REF_putstatic, REF_invokestatic
 * 的方法句柄，并且这个方法没有被初始化，则需要先进行初始化
 */
package com.xianyue.basictype.jvm.classinnitial;
