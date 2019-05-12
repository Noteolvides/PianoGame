package Model;

public class Configuration {
    private int portConnexioBBDD;
    private String ip;
    private String BBDDName;
    private String user;
    private String password;
    private int clientPort;

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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getClientPort() {
        return clientPort;
    }

    public void setClientPort(int clientPort) {
        this.clientPort = clientPort;
    }
}
