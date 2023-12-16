package vn.iotstar.Entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "electrical_monitoring")
@NamedQuery(name = "Electrical.findAll", query = "SELECT c FROM Electrical c")



public class Electrical implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "voltage")
	private int voltage;
	@Column(name = "current")
	private float current;
	@Column(name = "power")
	private int power;
	@Column(name = "power_factor")
	private float pF; // Hệ số công suất
	@Column(name = "temperature")
	private float temperature;
	@Column(name = "humidity")
	private float humidity;
	@Column(name = "timestamp")
    private Timestamp timestamp;

    
    @PrePersist
    protected void onCreate() {
        timestamp = new Timestamp(System.currentTimeMillis());
    }


	public Electrical() {
		super();
	}

	public Electrical(int id, int voltage, float current, int power, 
			float pF, float temperature, float humidity, Timestamp timestamp) {
		super();
		this.id = id;
		this.voltage = voltage;
		this.current = current;
		this.power = power;
		this.pF = pF;
		this.temperature = temperature;
		this.humidity = humidity;
		this.timestamp = timestamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVoltage() {
		return voltage;
	}

	public void setVoltage(int voltage) {
		this.voltage = voltage;
	}

	public float getCurrent() {
		return current;
	}

	public void setCurrent(float current) {
		this.current = current;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public float getpF() {
		return pF;
	}

	public void setpF(float pF) {
		this.pF = pF;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getHumidity() {
		return humidity;
	}

	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}
	public Timestamp getTimeStamp() {
        return timestamp;
    }

    public void setTimeStamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }


}
