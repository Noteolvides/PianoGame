package Model.ConfigurationPackage;

import java.util.List;

public class Configuration {
    private int portConnexioBBDD;
    private String ip;
    private String BBDDName;
    private List<MysqlUser> mysqlUsers;
    private int clientPort;
    private KeyConfiguration[] keys;

    public int getPortConnexioBBDD() {
        return portConnexioBBDD;
    }

    public void setPortConnexioBBDD(int portConnexioBBDD) {
        this.portConnexioBBDD = portConnexioBBDD;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getBBDDName() {
        return BBDDName;
    }

    public void setBBDDName(String BBDDName) {
        this.BBDDName = BBDDName;
    }

    public List<MysqlUser> getMysqlUsers() {
        return mysqlUsers;
    }

    public void setMysqlUsers(List<MysqlUser> mysqlUsers) {
        this.mysqlUsers = mysqlUsers;
    }

    public int getClientPort() {
        return clientPort;
    }

    public void setClientPort(int clientPort) {
        this.clientPort = clientPort;
    }

    public KeyConfiguration[] getKeys() {
        return keys;
    }

    public void setKeys(KeyConfiguration[] keys) {
        this.keys = keys;
    }
}
