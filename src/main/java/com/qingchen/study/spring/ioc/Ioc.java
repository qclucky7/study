package com.qingchen.study.spring.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Ioc
 * @description:
 * @author: WangChen
 * @create: 2020-04-06 15:16
 **/
public class Ioc {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        Set<Object> set = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    /**
     * 都通过这个方法拿到相应的实现
     * String[] listenerBeanNames = this.getBeanNamesForType(xxxx.class, true, false);
     *
     * @see spring refresh()
     *  @1.prepareRefresh() 刷新前的预处理
     *      this.initPropertySources(); 初始化一些设置, 子类自定义个性化属性设置的方法
     *      this.getEnvironment().validateRequiredProperties(); 校验属性的合法性
     *      this.earlyApplicationEvents = new LinkedHashSet(); 保存容器一些早期的事件
     * @2.this.obtainFreshBeanFactory() 获取beanFactory
     *      refreshBeanFactory()设置序列化id 创建一个DefaultListableBeanFactory beanFactory = this.createBeanFactory();
     *      getBeanFactory() 返回刚才创建的factory
     * @3.prepareBeanFactory beanFactory准备工作, 添加一些设置
     *      1. 设置类加载器, 表达式解析器, 处理器等等
     * @4.this.postProcessBeanFactory(beanFactory); beanFactory准备工作完成后一些后置处理
     * ===================以上是beanFactory的创建和预准备工作=======================
     * @5.this.invokeBeanFactoryPostProcessors(beanFactory); 执行BeanFactoryPostProcessors
     * BeanFactoryPostProcessors在beanFactory标准初始化之后执行
     * 两个接口 BeanDefinitionRegistryPostProcessor  BeanFactoryPostProcessor
     * 先执行BeanDefinitionRegistryPostProcessor 排序相关的一些操作
     * BeanFactoryPostProcessor和上面相同
     * @6.this.registerBeanPostProcessors(beanFactory); 注册bean的后置处理器 拦截bean创建的过程 也就是在bean初始化的时候
     *      1. 获取所有的BeanPostProcessors
     *      2. 按照priorityOrder和Order排序注解到beanFactory
     * @7.this.initMessageSource(); 消息绑定和消息解析,国际化
     *      1.先获得getBeanFactory
     *      2.查看容器id中有没有是messageSource的, 类型是messageSource的组件, 没有创建一个
     * @8.this.initApplicationEventMulticaster();  初始化事件监听器
     *
     * @9.this.onRefresh(); 留给子容器(子类)
     *      1.子类重写这个方法, 可以自定义逻辑
     *
     * @10.this.registerListeners(); 给容器中将所有项目中的applicationListener注册进来
     *      1. 从容器中拿到所有的applicationListener组件
     * @11.this.finishBeanFactoryInitialization(beanFactory); 完成beanFactory的初始化 初始化所有的单实例bean
     *      1.preInstantiateSingletons() 初始化剩下的单实例bean
     *          1.获取所有的bean
     *          2.获得bean的定义信息RootBeanDefinition
     *            1)if (!bd.isAbstract() && bd.isSingleton() && !bd.isLazyInit()) {...}
     *            2)判断是否是beanFactory, 是否是实现了beanFactory的bean
     *            3)如果不是beanFactory的bean
     *                  1)就调用getBean()方法创建对象 ioc.getBean创建 调用doGetBean()
     *                  2)先获取缓存中的保存的单实例bean。如果能获取到说明这个实例创建过(所有创建的单实例bean都会被缓存起来)
     *                  //Cache of singleton objects: bean name to bean instance.
     *                  private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);
     *                  3)缓存中拿不到,开始bean的创建过程
     *                  4)标记bean已经被创建
     *                  if (!typeCheckOnly) {
     * 				           markBeanAsCreated(beanName);
     *                  }
     *                  5)获得当前bean所依赖的bean 如果有还是通过getBean方法创建
     *                  6)启动单实例bean的创建流程
     *                    1)getSingleton(String beanName, ObjectFactory<?> singletonFactory)的createBean方法
     *                    2)
     *                    // Give BeanPostProcessors a chance to return a proxy instead of the target bean instance.
     * 			          Object bean = resolveBeforeInstantiation(beanName, mbdToUse);
     * 			          3)如果上一步bean为null
     * 			             1)调用Object beanInstance = doCreateBean(beanName, mbdToUse, args);
     * 			             2)创建bean
     * 			               instanceWrapper = createBeanInstance(beanName, mbd, args);
     * 			               利用工厂方法或者构造器创建出一个对象
     * 			               然后又调用后置处理器可以更改bean实例的属性
     * 			               applyMergedBeanDefinitionPostProcessors(mbd, beanType, beanName);
     * 			             3)populateBean(beanName, mbd, instanceWrapper);为bean属性赋值
     * 			             4)bean的初始化
     * 			             exposedObject = initializeBean(beanName, exposedObject, mbd);
     * 			             5)bean销毁注册
     * 			             registerDisposableBeanIfNecessary(beanName, bean, mbd);
     * 			          4)将bean放到singletonObjects缓存中
     * @12.this.finishRefresh(); 完成ioc容器的创建
     *
     *
     *
     *
     */
}
