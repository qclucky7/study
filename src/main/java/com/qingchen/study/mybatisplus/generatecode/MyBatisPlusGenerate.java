package com.qingchen.study.mybatisplus.generatecode;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.*;

/**
 * @ClassName MyBatisPlusGenerate
 * @description: 代码生成器
 * @author: WangChen
 * @create: 2020-05-21 10:22
 **/
public class MyBatisPlusGenerate {

    /**
     * 数据源
     */
    private static final String URL = "jdbc:mysql://rm-2ze6dvrqezp7j30g6zo.mysql.rds.aliyuncs.com:3306/qingchen?useUnicode=true&characterEncoding=utf8";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "wangchen";
    private static final String PASSWORD = "Kvalue_wangchen";

    /**
     * 包路径配置
     */
    private static final String PARENT = "kj.op.sys";
    /**
     * controller url
     **/
    private static final String CONTROLLER_URL = "controller";
    /**
     * entity url
     **/
    private static final String ENTITY_URL = "domain";
    /**
     * mapper url
     **/
    private static final String MAPPER_URL = "bll.core.repositories";
    /**
     * service 接口 url
     **/
    private static final String SERVICE_INTERFACE_URL = "bll.core.services";
    /**
     * service 实现类 url
     **/
    private static final String SERVICE_IMPL_URL = "bll.services";
    /**
     * mapper.xml映射文件 url
     **/
    private static final String MAPPER_XML_URL = "/src/main/resources/mapping/";


    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {


        //填充名字
        String entity = scanner("输入生成实体类名");

        //支持AR模式
        String projectPath = System.getProperty("user.dir");
        // 1.全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setActiveRecord(true)
                .setOutputDir(projectPath + "/src/main/java")
                //文件覆盖
                .setFileOverride(true)
                //主键自增
                .setIdType(IdType.AUTO)
                .setAuthor(scanner("输入生成作者"))
                //开启swagger配置
                .setSwagger2(true)
                //xml映射
                .setBaseResultMap(true)
                //对应数据库Date字段 不写这个生成实体类LocalDate
                .setDateType(DateType.ONLY_DATE)
                //sql片段
                .setBaseColumnList(true)
                //生成的文件名字
                .setMapperName("I" + entity + "Mapper")
                .setXmlName(entity + "Mapper")
                .setServiceName("I" + entity + "Service")
                .setServiceImplName(entity + "ServiceImpl")
                .setControllerName(entity + "Controller")
                .setEntityName(entity + "Entity");


        // 2.数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(URL)
                .setDbType(DbType.MYSQL)
                .setDriverName(DRIVER)
                .setUsername(USERNAME)
                .setPassword(PASSWORD);

        // 3.包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(PARENT)
                .setMapper(MAPPER_URL)
                .setService(SERVICE_INTERFACE_URL)
                .setServiceImpl(SERVICE_IMPL_URL)
                .setController(CONTROLLER_URL)
                .setEntity(ENTITY_URL);

        // 4.策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        //全局大小写命名
        strategyConfig.setCapitalMode(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setRestControllerStyle(true)
                // 公共父类
                //.setSuperEntityClass(BaseEntity.class)
                //这里配置的是数据库父类的字段 不是实体类的
                //.setSuperEntityColumns("guid","created_time","updated_time")
                //strategyConfig.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
                //使用lombok
                .setEntityLombokModel(false)
                //生成使用的表
                .setInclude(scanner("生成的表名"))
                .setControllerMappingHyphenStyle(true);
        //strategyConfig.setTablePrefix(pc.getModuleName() + "_");


        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                //自定义配置，在模版中cfg.superColums 获取
                // TODO 这里解决子类会生成父类属性的问题，在模版里会用到该配置
                map.put("superColums", this.getConfig().getStrategyConfig().getSuperEntityColumns());
                this.setMap(map);
            }
        };
        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + MAPPER_XML_URL + entity
                        + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);

        // 代码生成器全局配置
        AutoGenerator mpg = new AutoGenerator();

        mpg.setPackageInfo(pc);
        mpg.setGlobalConfig(gc);
        mpg.setDataSource(dsc);
        mpg.setStrategy(strategyConfig);
        mpg.setCfg(cfg);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 关闭默认 xml 生成，调整生成 至 根目录
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);
        mpg.setTemplate(tc);


        // 5.执行
        mpg.execute();
    }
}
