package io.swagger.api;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.MysqldConfig;

//import com.wix.mysql.EmbeddedMysql;
//import com.wix.mysql.config.MysqldConfig;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.ScriptResolver.classPathScript;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v5_7_latest;

public class Utils {


        public static EmbeddedMysql startMemoryMySQL(final String userName, final String password) {

            System.out.println("Starting Embedded MySQL server...");

            final MysqldConfig config = aMysqldConfig(v5_7_latest)
                    .withPort(3310)
                    .withUser(userName, password)
                    .build();

            return anEmbeddedMysql(config)
                    .addSchema("zms_server", classPathScript("schema/zms_server.sql"))
                    .start();
        }


    public static void stopMemoryMySQL(EmbeddedMysql mysqld) {
            System.out.println("Stopping Embedded MySQL server...");
            mysqld.stop();
        }

}
