package Model.ConfigurationPackage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationCreation {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Configuration configuration = new Configuration();
        configuration.setBBDDName("SmartPiano");
        configuration.setClientPort(5000);
        configuration.setIp("localhost");
        configuration.setPortConnexioBBDD(3306);

        List<MysqlUser> mysqlUsers = new ArrayList<>();
        MysqlUser mysqlUser = new MysqlUser();
        mysqlUser.setUsername("noAlias");
        mysqlUser.setPassword("password1999");

        MysqlUser mysqlUser2 = new MysqlUser();
        mysqlUser2.setUsername("normalUser");
        mysqlUser2.setPassword("normalUserPassword1999");

        MysqlUser mysqlUser3 = new MysqlUser();
        mysqlUser3.setUsername("admin");
        mysqlUser3.setPassword("adminPassword1999");

        mysqlUsers.add(mysqlUser);
        mysqlUsers.add(mysqlUser2);
        mysqlUsers.add(mysqlUser3);

        configuration.setMysqlUsers(mysqlUsers);


        try {
            FileWriter fw = new FileWriter("configFiles/config.json");
            gson.toJson(configuration,fw);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
