package repair_App;

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
import jpaConnection.fix_Repair_JPAController;
import repairObject.fix_Data_log;
import repairObject.fix_Repair;
import repairObject.report_Repair;

@SpringBootApplication
@ComponentScan(basePackages = { "jpaConnection" })
@RestController
public class repairApp_Controller<Json> {
	private ObjectMapper mapper = new ObjectMapper();
	private final fix_Repair_JPAController fix_Repair_jPAController;

	@Autowired
	public repairApp_Controller(fix_Repair_JPAController fix_Repair_JPAController) {

		this.fix_Repair_jPAController = fix_Repair_JPAController;
		mapper.registerModule(new JavaTimeModule());
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
		System.out.println("123123" + content);
		try {
			report_Repair data = mapper.readValue(content, report_Repair.class);
			System.out.print(data.Report_Date);

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
	public String postFixRepair(@RequestBody String content) { //完成報修
           
		try {
			System.out.print(content);

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
	public String postFixRepair_Delay(@RequestBody String content) { //逾時報修
           
		try {
			JsonNode nodes=mapper.readTree(content);
			if(nodes.isArray()) {
				System.out.print(nodes.get(0));
				System.out.print(nodes.get(1));

				
				fix_Repair repair = mapper.readValue(nodes.get(0).toString(), fix_Repair.class);
				fix_Data_log repair_Log = mapper.readValue(nodes.get(1).toString(), fix_Data_log.class);
				return fix_Repair_jPAController.insert_fix_Repair_Replay(repair,repair_Log);
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
	public String accept_Repair(@RequestBody String content) { //接收報修
		try {
			System.out.print(content);

			JsonNode node = mapper.readTree(content);
	          String TimeString="yyyyMMdd HH:mm:ss";
	          DateTimeFormatter Formater=DateTimeFormatter.ofPattern(TimeString);
	          LocalDateTime  AccetTime=LocalDateTime.parse(node.get("Report_Accept_Date").asText(),Formater);
	          LocalDateTime EstiTime=LocalDateTime.parse(node.get("Report_Esti_Date").asText(),Formater);
		  
			  return fix_Repair_jPAController.accept_Repair(node.get("List_Number").asText(), node.get("Report_Accept_Emp").asText(), AccetTime, EstiTime);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "fail";
		}

	}

	@GetMapping("repairApp_Controller/loadRepairProcessList")
	public List<report_Repair> loadRepairProcessList(@RequestParam String content) { // 帶出報修狀態
		return fix_Repair_jPAController.load_RepairStatus();

	}

	@GetMapping("repairApp_Controller/loadRepairFinishList")
	public List<fix_Repair> loadRepairFinishList(@RequestParam String content) { // 帶出已完成報修明細
		return fix_Repair_jPAController.load_RepairFinish();

	}
	

}
