package server.socket;

/**
 * 
 * @author wltjd
 *
 *
 * @param ip   : Server ip address / default : 127.0.0.1
 * @param port : Server port / default : 5000
 */

public class ServerConfig {
	private String ip;
	private int port;

	public ServerConfig() {
		this.ip = "127.0.0.1";
		this.port = 6000;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "ServerConfig [ip=" + ip + ", port=" + port + "]";
	}

}
