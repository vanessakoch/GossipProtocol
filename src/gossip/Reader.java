package gossip;

import java.io.FileReader;
import java.util.Properties;

public class Reader {

	private FileReader file;
	private Properties prop;
	
	private int id;
	private String node;
	private String ip;
	private int port;

	public Reader(String fileUrl) {
		super();
		this.prop = new Properties();

		try {
			this.file = new FileReader(fileUrl);
			prop.load(file);

			setId(Integer.valueOf(prop.getProperty("id")));
			setNode(prop.getProperty("node"));
			setIp(prop.getProperty("ip"));
			setPort(Integer.valueOf(prop.getProperty("port")));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getId() {
		return id;
	}

	public String getNode() {
		return node;
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}
	
}
