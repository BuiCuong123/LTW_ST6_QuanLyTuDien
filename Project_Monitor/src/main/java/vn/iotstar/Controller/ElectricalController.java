package vn.iotstar.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import com.hivemq.client.mqtt.datatypes.MqttQos;
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client;
import com.hivemq.client.mqtt.mqtt5.message.connect.connack.Mqtt5ConnAck;

import com.hivemq.client.mqtt.mqtt5.message.subscribe.Mqtt5RetainHandling;
import com.hivemq.client.mqtt.mqtt5.message.subscribe.suback.Mqtt5SubAck;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vn.iotstar.Entity.Electrical;
import vn.iotstar.Service.ElectricalServiceImpl;
import vn.iotstar.Service.IElectricalService;


@WebServlet("/admin-electrical")
public class ElectricalController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IElectricalService electricalService = new ElectricalServiceImpl();
	public static String payloadString;
	
	
    public ElectricalController() {
        super();
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI().toString();
		if (url.contains("admin-electrical")) {
			request.getRequestDispatcher("login.jsp").forward(request, response);}
        final Mqtt5AsyncClient client = Mqtt5Client.builder()
                .serverHost("broker.hivemq.com")
                .automaticReconnectWithDefaultConfig()
                .buildAsync();

        final Mqtt5ConnAck connAck = client.toBlocking().connectWith()
                .cleanStart(false)          // resume a previous session
                .sessionExpiryInterval(30)  // keep session state for 30s
                .restrictions()
                    .receiveMaximum(10)             // receive max. 10 concurrent messages
                    .sendMaximum(10)                // send max. 10 concurrent messages
                    .maximumPacketSize(10_240)      // receive messages with max size of 10KB
                    .sendMaximumPacketSize(10_240)  // send messages with max size of 10KB
                    .topicAliasMaximum(0)           // the server should not use topic aliases
                    .sendTopicAliasMaximum(8)       // use up to 8 aliases for the most used topics (automatically traced)
                    .applyRestrictions()
                .willPublish()
                    .topic("demo/topic/will")
                    .qos(MqttQos.EXACTLY_ONCE)
                    .payload("rip".getBytes())
                    .contentType("text/plain")  // our payload is text
                    .messageExpiryInterval(120) // not so important, expire message after 2min if can not be delivered
                    .delayInterval(30)          // delay sending out the will message so we can try to reconnect immediately
                    .userProperties()           // add some user properties to the will message
                        .add("sender", "demo-seder-1")
                        .add("receiver", "you")
                        .applyUserProperties()
                    .applyWillPublish()
                .send();

        System.out.println("connected " + connAck);


        final Mqtt5SubAck subAck = client.subscribeWith()
                .topicFilter("ESP32_send_to_Server")
                .noLocal(true)
                .retainHandling(Mqtt5RetainHandling.DO_NOT_SEND)
                .retainAsPublished(true)
                .callback(publish -> {
                    payloadString = new String(publish.getPayloadAsBytes());
                   
                    // Phân tích chuỗi payloadString
                    String[] number_string_split = payloadString.split(" ");

                    // Kiểm tra nếu có đủ giá trị để gán vào biến
                    if (number_string_split.length >= 6) {
                        // Chuyển đổi và gán giá trị cho các biến
                        int voltage = Integer.parseInt(number_string_split[0].trim());
                        double current = Double.parseDouble(number_string_split[1].trim());
                        int power = Integer.parseInt(number_string_split[2].trim());
                        double pF = Double.parseDouble(number_string_split[3].trim());
                        double temperature = Double.parseDouble(number_string_split[4].trim());
                        double humidity = Double.parseDouble(number_string_split[5].trim());
                        
                        
                		Electrical electrical_value = new Electrical();
                		electrical_value.setVoltage(voltage);
                		electrical_value.setCurrent((float) current);
                		electrical_value.setPower(power);
                		electrical_value.setpF((float) pF);
                		electrical_value.setTemperature((float) temperature);
                		electrical_value.setHumidity((float) humidity);
                		
                		electricalService.add_new_value(electrical_value);


                        // Sử dụng các giá trị đã gán
                        System.out.println("Voltage: " + voltage);
                        System.out.println("Current: " + current);
                        System.out.println("Power: " + power);
                        System.out.println("Power Factor: " + pF);
                        System.out.println("Temperature: " + temperature);
                        System.out.println("Humidity: " + humidity);
                    } else {
                        System.err.println("Invalid payload format ");
                    }
               
                    //System.out.println("received message: " + publish + ", payload: " + payloadString);
                  
                })
                .send().join();

		

		findAll(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI().toString();
		if (url.contains("admin-electrical")) {
			req.getRequestDispatcher("login.jsp").forward(req, resp);}
		findAll(req, resp);
		doGet(req, resp);
		req.getRequestDispatcher("login.jsp").forward(req, resp);
		
		
	}


	protected void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			List<Electrical> listElec = electricalService.findAll();
			req.setAttribute("list", listElec);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Eror: " + e.getMessage());
		}
	}
	
	
	protected void get_timestamp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Timestamp timestamp_result = electricalService.get_timestamp();
			req.setAttribute("timestamp", timestamp_result);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Eror: " + e.getMessage());
		}
	}
	 
	



}
