package repair_App;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import aj.org.objectweb.asm.TypeReference;
import jakarta.annotation.PreDestroy;
import jpaConnection.fix_Repair_JPAController;
import repairObject.ReportSelectViewDTO;
import repairObject.SendMail;
import repairObject.fix_Data_log;
import repairObject.fix_Repair;
import repairObject.report_Repair;

@SpringBootApplication
@ComponentScan(basePackages = { "jpaConnection", "repairObject" })
@RestController
public class repairApp_Controller<Json> {
	private ObjectMapper mapper = new ObjectMapper();
	private final fix_Repair_JPAController fix_Repair_jPAController;
	private final SendMail mail;

	@Autowired
	public repairApp_Controller(fix_Repair_JPAController fix_Repair_JPAController, SendMail mail) {
		this.fix_Repair_jPAController = fix_Repair_JPAController;
		this.mail = mail;
		mapper.registerModule(new JavaTimeModule());
		mail.mailInit("loveaoe33@gmail.com", "repairApp Service開啟", "狀態:Sucess", "loveaoe33@gmail.com");
	}

	@PreDestroy
	public void cleanUp() {
		mail.mailInit("loveaoe33@gmail.com", "repairApp Service關閉", "狀態:Sucess", "loveaoe33@gmail.com");
	}

	@GetMapping("repairApp_Controller/s")
	public String test() {
		return "123";
	}

	@GetMapping("repairApp_Controller/")
	public String test2() {
		return "123";
	}

	@CrossOrigin
	@PostMapping("repairApp_Controller/insert_ReportRepair")
	public String insert_ReportRepair(@RequestBody String content) { // 新增報修申請/結果
		try {
			report_Repair data = mapper.readValue(content, report_Repair.class);
			fix_Repair_jPAController.insert_repair_Report(data);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
			return "fail";
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			System.out.print(e);
			e.printStackTrace();
			return "fail";
		}
		return "Sucess";
	}

	@CrossOrigin
	@PostMapping("repairApp_Controller/postFixRepair")
	public String postFixRepair(@RequestBody String content) { // 完成報修
		try {
			fix_Repair repair = mapper.readValue(content, fix_Repair.class);
			return fix_Repair_jPAController.insert_fix_Repair(repair);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
	}

	@CrossOrigin
	@PostMapping("repairApp_Controller/postFixRepair_Delay")
	public String postFixRepair_Delay(@RequestBody String content) { // 逾時報修
		try {
			JsonNode nodes = mapper.readTree(content);
			if (nodes.isArray()) {
				fix_Repair repair = mapper.readValue(nodes.get(0).toString(), fix_Repair.class);
				fix_Data_log repair_Log = mapper.readValue(nodes.get(1).toString(), fix_Data_log.class);
				return fix_Repair_jPAController.insert_fix_Repair_Replay(repair, repair_Log);
			}
			return "fail";
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
	}

	@CrossOrigin
	@PostMapping("repairApp_Controller/accept_Repair")
	public String accept_Repair(@RequestBody String content) { // 接收報修
		try {
			JsonNode node = mapper.readTree(content);
			String TimeString = "yyyyMMdd HH:mm:ss";
			DateTimeFormatter Formater = DateTimeFormatter.ofPattern(TimeString);
			LocalDateTime AccetTime = LocalDateTime.parse(node.get("Report_Accept_Date").asText(), Formater);
			LocalDateTime EstiTime = LocalDateTime.parse(node.get("Report_Esti_Date").asText(), Formater);
			if (node.get("AcceptType").asText().equals("Sucess")) {
				return fix_Repair_jPAController.accept_Repair("Process", node.get("List_Number").asText(),
						node.get("Report_Accept_Emp").asText(), AccetTime, EstiTime);
			} else if (node.get("AcceptType").asText().equals("fail")) {
				return fix_Repair_jPAController.accept_Repair("Reject", node.get("List_Number").asText(),
						node.get("Report_Accept_Emp").asText(), AccetTime, EstiTime);
			}
			return "fail";
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}
	}

	@CrossOrigin
	@GetMapping("repairApp_Controller/loadRepairHistory")
	public <T> List<T> loadRepaitHistory(@RequestParam String content) throws UnsupportedEncodingException { // 帶出報修/維修紀錄
		try {
			content = URLDecoder.decode(content, "UTF-8");
			JsonNode node = mapper.readTree(content);
			DateTimeFormatter formatetr = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
			if (node.get("RadioContent").asText().equals("報修")) {
				return (List<T>) fix_Repair_jPAController.load_DateReportStatus(node.get("SearchNumber").asText(),
						LocalDateTime.parse(node.get("start").asText(), formatetr),
						LocalDateTime.parse(node.get("end").asText(), formatetr));
			} else if (node.get("RadioContent").asText().equals("維修")) {
				return (List<T>) fix_Repair_jPAController.load_DateFixStatus(node.get("SearchNumber").asText(),
						LocalDateTime.parse(node.get("start").asText(), formatetr),
						LocalDateTime.parse(node.get("end").asText(), formatetr));
			}

		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@CrossOrigin
	@GetMapping("repairApp_Controller/loadDateReportList")
	public <T> List<T> loadDateReportList(@RequestParam String content) throws UnsupportedEncodingException {
		try {
			content = URLDecoder.decode(content, "UTF-8");
			JsonNode node = mapper.readTree(content);
			DateTimeFormatter formatetr = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
			return (List<T>) fix_Repair_jPAController.load_DateReportList(
					LocalDateTime.parse(node.get("Start").asText(), formatetr),
					LocalDateTime.parse(node.get("End").asText(), formatetr));
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return null;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	@CrossOrigin
	@GetMapping("repairApp_Controller/loadRepairProcessList")
	public List<report_Repair> loadRepairProcessList(@RequestParam String content) { // 帶出報修狀態
		return fix_Repair_jPAController.load_RepairStatus();
	}

	@CrossOrigin
	@GetMapping("repairApp_Controller/loadRepairFinishList")
	public List<fix_Repair> loadRepairFinishList(@RequestParam String content) { // 帶出已完成報修明細
		return fix_Repair_jPAController.load_RepairFinish();
	}
}
