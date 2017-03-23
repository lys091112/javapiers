package com.xianyue.mocktiotest;

/**
 * @author Xianyue
 */
public class packageinfo {
    /**
     * PowerMock有两个重要的注解：
     * –@RunWith(PowerMockRunner.class)
     * –@PrepareForTest( { YourClassWithEgStaticMethod.class })
     * 如果你的测试用例里没有使用注解@PrepareForTest，那么可以不用加注解@RunWith(PowerMockRunner.class)
     * ，反之亦然。当你需要使用PowerMock强大功能（Mock静态、final、私有方法等）的时候，就需要加注解@PrepareForTest。
     */


    /**
     * Mock  : 对于mock出来的数据，我们只能执行他方法的表面动作，无法执行到数据内部的逻辑
     * InjectMock ： 可以执行到数据内部的方法逻辑，但对象需要我们手动NEW出来。（这是很重要的一点）
     * 有个很重要的技巧就是，但我们需要手动创建一个injectMock时，如果对象的构造方法还有第三方的依赖，那么可以通过构造方法传递一个Mock对象进去
     * 这样就可以成功创建对象。伟大的构造方法呀
     *
     * 对于第三方的jar，他们的静态方法是否也可以injectMock???
     */

    /**
     * PowerMock会根据你的mock要求，去修改写在注解@PrepareForTest里的class文件（当前测试类会自动加入注解中），以满足特殊的mock需求。例如：去除final方法的final标识，在静态方法的最前面加入自己的虚拟实现等。
     * 如果需要mock的是系统类的final方法和静态方法，PowerMock不会直接修改系统类的class文件，而是修改调用系统类的class文件，以满足mock需求,所以
     * 当我们去模拟Spring类测SpringContextHolder的静态方法时，它依旧会调用它内部的方法逻辑，此时我们需要对其进行mock，并通过构造方法传递，
     * 例如:
     * public WebhookHandler( List<String> severityMessages ) {
     *      this(severityMessages, (AlertActionTrigger)SpringContextHolder.getSpringBean("actionTrigger"));
     * }
     *
     * WebhookHandler( List<String> severityMessages, AlertActionTrigger actionTrigger) {
     *      this.severityMessages = severityMessages;
     *      this.actionTrigger = actionTrigger;
     * }
     *
     * class TestClass {
     *
     * @InjectMock
     * WebhookHandler handler = new WebHookHanndler(servities, actionTrigger)
     *
     * @Mock
     * AlertActionTrigger actionTrigger;
     *
     * ....
     * }

     */
}
