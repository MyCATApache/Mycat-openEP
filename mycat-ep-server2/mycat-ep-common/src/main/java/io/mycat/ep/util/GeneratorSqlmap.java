package io.mycat.ep.util;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
/**
 * mybatis 代码生成工具
 * @author 吴玉
 *
 */
public class GeneratorSqlmap {

	public void generator() throws Exception {

		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		// 指定 逆向工程配置文件
		String resource = Thread.currentThread().getContextClassLoader()
				.getResource("generatorConfig.xml").toString().substring(6);
		System.out.println(resource);
		File configFile = new File(resource);
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,callback, warnings);
		myBatisGenerator.generate(null);

	}

	public static void main(String[] args) throws Exception {
		GeneratorSqlmap generatorSqlmap = new GeneratorSqlmap();
		generatorSqlmap.generator();
	}

	public static Connection getConnection(String url, String user,
			String password) throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

}
