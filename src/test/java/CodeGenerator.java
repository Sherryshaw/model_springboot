import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
final class MyConfig{
    //表名
    public static final String[] TABLES={
    };
    public static final boolean MAKE_CONTROLLER=true;
    public static final boolean MAKE_SERVICE=true;
    public static final boolean MAKE_MAPPER=true;
    public static final boolean MAKE_ENTITY=true;
    public static final boolean FILEOVERRIDE=true;
    public static String author;
    public static String username;
    public static String password;
    public static String driver;
    public static String url;

    @Value("${app-info.author}")
    public void setAuthor(String author) {
        MyConfig.author = author;
    }
    @Value("${spring.datasource.username}")
    public static void setUsername(String username) {
        MyConfig.username = username;
    }

    @Value("${spring.datasource.password}")
    public static void setPassword(String password) {
        MyConfig.password = password;
    }

    @Value("${spring.datasource.driver}")
    public static void setDriver(String driver) {
        MyConfig.driver = driver;
    }

    @Value("${spring.datasource.url}")
    public static void setUrl(String url) {
        MyConfig.url = url;
    }

    public static final String dbUrl=url+"?useUnicode=true&useSSL=false&characterEncoding=utf8";
}

// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(MyConfig.author);
        gc.setOpen(false);
        gc.setFileOverride(MyConfig.FILEOVERRIDE);
        gc.setBaseResultMap(true);
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(MyConfig.dbUrl);
        // dsc.setSchemaName("public");
        dsc.setDriverName(MyConfig.driver);
        dsc.setUsername(MyConfig.username);
        dsc.setPassword(MyConfig.password);
        mpg.setDataSource(dsc);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("project");
        pc.setParent("com");
        pc.setController("tmp");
        pc.setXml("mappper.xml");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
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
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
        strategy.setSuperControllerClass("com.project.common.BaseController");
        // 写于父类中的公共字段
        //strategy.setSuperEntityColumns("id");
        strategy.setInclude(MyConfig.TABLES);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}