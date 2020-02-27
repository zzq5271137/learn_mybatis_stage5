package com.mycomp.mybatis.test;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MybatiseGeneratorTest {
    public static void main(String[] args) {
        try {
            generate();
        } catch (IOException | XMLParserException | InvalidConfigurationException
                | SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void generate()
            throws IOException, XMLParserException, InvalidConfigurationException,
            SQLException, InterruptedException {
        // 编写Mybatise Generator生成代码
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        File configFile = new File("./resource/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
