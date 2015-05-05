package app.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the proxies database table.
 * 
 */
@Entity
@Table(name="proxies")
@NamedQuery(name="Proxy.findAll", query="SELECT p FROM Proxy p")
public class Proxy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String ip;

	@Column(name="is_ssl")
	private boolean isSsl;

	@Column(name="is_worked")
	private boolean isWorked;

	@Column(name="pinged_count")
	private int pingedCount;

	private String port;

	private int timeout;

	public Proxy() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public boolean getIsSsl() {
		return this.isSsl;
	}

	public void setIsSsl(boolean isSsl) {
		this.isSsl = isSsl;
	}

	public boolean getIsWorked() {
		return this.isWorked;
	}

	public void setIsWorked(boolean isWorked) {
		this.isWorked = isWorked;
	}

	public int getPingedCount() {
		return this.pingedCount;
	}

	public void setPingedCount(int pingedCount) {
		this.pingedCount = pingedCount;
	}

	public String getPort() {
		return this.port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public int getTimeout() {
		return this.timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

}