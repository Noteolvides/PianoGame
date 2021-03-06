package Server.Controller.BBDD.MultiConnection;


import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that takes care of loading all the configuration that is needed to connect
 * to the database (contains hashmap of the different users ...). Simply, it is the
 * method where we specify the different connections that may exist. We define it
 * as a bean, because in reality should be in the XML, but we do it through annotations.
 *
 * @version 1.0
 * @since 2019-05-19
 *
 * @author Gustavo Gómez
 * @author Gerard Melgares
 * @author Josep Roig
 * @author Neil Torrero
 * @author Jiahui Xie
 */
@Configuration
public class DataSourceConfiguration {

    /**
     * This is the constructor that basically deals with obtaining the JSON information related to the database and interpreting it
     * @return Returns the instance of the Datasource object, which will contain all the necessary information from the database
     */
    @Bean(name = "dataSource")
    public DataSource clientDatasource() {
        //We define a hashmap where we will save the object together with its reference (her personal DNI that is an enumeration, so we can identify them)
        Map<Object, Object> targetDataSources = new HashMap<Object,Object>();
        Gson gson = new Gson();
        JsonReader json = null;
        try {
            json = new JsonReader(new FileReader("configFiles/config.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Model.ConfigurationPackage.Configuration config = gson.fromJson(json, Model.ConfigurationPackage.Configuration.class);
        //We create the datasource (connections) that there may be (At the moment you will only have one)
        DataSource clientDatasource_2 = DataSourceBuilder.create().username(config.getMysqlUsers().get(0).getUsername()).password(config.getMysqlUsers().get(0).getPassword()).driverClassName("com.mysql.cj.jdbc.Driver").url("jdbc:mysql://" + config.getIp() + "/"+ config.getBBDDName() +"?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC").type(DriverManagerDataSource.class).build();
        DataSource clientDatasource_3 = DataSourceBuilder.create().username(config.getMysqlUsers().get(1).getUsername()).password(config.getMysqlUsers().get(1).getPassword()).driverClassName("com.mysql.cj.jdbc.Driver").url("jdbc:mysql://"+ config.getIp()+ "/" + config.getBBDDName() + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC").type(DriverManagerDataSource.class).build();
        DataSource clientDatasource = DataSourceBuilder.create().username(config.getMysqlUsers().get(2).getUsername()).password(config.getMysqlUsers().get(2).getPassword()).driverClassName("com.mysql.cj.jdbc.Driver").url("jdbc:mysql://"+ config.getIp()+"/"+ config.getBBDDName()+"?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC").type(DriverManagerDataSource.class).build();
        //We add them to the hashMap
        targetDataSources.put(AvaiableClients.adminSmartPiano, clientDatasource);
        targetDataSources.put(AvaiableClients.noUserSmartPiano, clientDatasource_2);
        targetDataSources.put(AvaiableClients.UserRegistered, clientDatasource_3);


        //We create the previous class and we add the HashMap, to know when the connection is changed, what is the new one
        AbstractRouting clientRoutingDatasource = new AbstractRouting();
        clientRoutingDatasource.setTargetDataSources(targetDataSources);

        //We define the connection with which it will start first
        clientRoutingDatasource.setDefaultTargetDataSource(clientDatasource_2);
        return clientRoutingDatasource;
    }


}
