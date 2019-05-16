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

        KeyConfiguration keyConfiguration [] = new KeyConfiguration[31];

        keyConfiguration[0] = new KeyConfiguration("PrimeraOctava",'1');
        keyConfiguration[1] = new KeyConfiguration("SegundaOctava",'2');
        keyConfiguration[2] = new KeyConfiguration("TerceraOctava",'3');
        keyConfiguration[3] = new KeyConfiguration("CuartaOctava",'4');
        keyConfiguration[4] = new KeyConfiguration("QuintaOctava",'5');
        keyConfiguration[5] = new KeyConfiguration("SextaOctava",'6');

        keyConfiguration[6] = new KeyConfiguration("C(i)",'A');
        keyConfiguration[7] = new KeyConfiguration("D(i)",'S');
        keyConfiguration[8] = new KeyConfiguration("E(i)",'D');
        keyConfiguration[9] = new KeyConfiguration("F(i)",'F');
        keyConfiguration[10] = new KeyConfiguration("G(i)",'G');
        keyConfiguration[11] = new KeyConfiguration("A(i)",'H');
        keyConfiguration[12] = new KeyConfiguration("B(i)",'J');

        keyConfiguration[13] = new KeyConfiguration("C#(i)",'Q');
        keyConfiguration[14] = new KeyConfiguration("D#(i)",'W');
        keyConfiguration[16] = new KeyConfiguration("F#(i)",'E');
        keyConfiguration[17] = new KeyConfiguration("G#(i)",'R');
        keyConfiguration[18] = new KeyConfiguration("A#(i)",'T');

        keyConfiguration[19] = new KeyConfiguration("C(i+1)",'Z');
        keyConfiguration[20] = new KeyConfiguration("D(i+1)",'X');
        keyConfiguration[21] = new KeyConfiguration("E(i+1)",'C');
        keyConfiguration[22] = new KeyConfiguration("F(i+1)",'V');
        keyConfiguration[23] = new KeyConfiguration("G(i+1)",'B');
        keyConfiguration[24] = new KeyConfiguration("A(i+1)",'N');
        keyConfiguration[25] = new KeyConfiguration("B(i+1)",'M');

        keyConfiguration[26] = new KeyConfiguration("C#(i+1)",'Y');
        keyConfiguration[27] = new KeyConfiguration("D#(i+1)",'U');
        keyConfiguration[28] = new KeyConfiguration("F#(i+1)",'I');
        keyConfiguration[29] = new KeyConfiguration("G#(i+1)",'O');
        keyConfiguration[30] = new KeyConfiguration("A#(i+1)",'P');

        configuration.setKeys(keyConfiguration);

        try {
            FileWriter fw = new FileWriter("configFiles/config.json");
            gson.toJson(configuration,fw);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
