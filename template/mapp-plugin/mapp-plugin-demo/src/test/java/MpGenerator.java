
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import org.jangod.iweb.util.generator.Config;
import org.jangod.iweb.util.generator.MybatisPlusGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MpGenerator {
    public static final String dbUserName="root";
    public static final String dbPassword="!QAZ2wsx2020";
    public static final String dbUrl = "jdbc:mysql://203.175.139.40:3306/dv_db_demo?useUnicode=true&characterEncoding=utf-8";
    public static final DbType dbType = DbType.MYSQL;
    public static final String dbDriver = "com.mysql.jdbc.Driver";
    public static final String author="mark";
    public static final String packageName = "org.jangod.mapp";
    public static final String moduleName="demo";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("请输入表名,多表逗号隔开:");
        String tables = br.readLine();
        String[] tableArray = tables.split(",");
        for(String tableName : tableArray){
            //tableName = tableName.toUpperCase();
            String projectPath = System.getProperty("user.dir")+"/mapp-plugin/mapp-plugin-demo/";
            GlobalConfig gc = new GlobalConfig();
            Config config = new Config();
            config.setAuthor(author);
            config.setDbDriver(dbDriver);
            config.setDbPassword(dbPassword);
            config.setDbUserName(dbUserName);
            config.setDbType(dbType);
            config.setDbUrl(dbUrl);
            config.setPackageName(packageName);
            config.setModuleName(moduleName);
            MybatisPlusGenerator.execute(config,projectPath,new String[]{tableName});
        }

    }
}