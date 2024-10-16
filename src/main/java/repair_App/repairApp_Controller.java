package repair_App;

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

import jpaConnection.fix_Repair_JPAController;
import repairObject.report_Repair;


@SpringBootApplication
@ComponentScan(basePackages = {"jpaConnection"})
@RestController
public class repairApp_Controller<Json> {
	private 
	ObjectMapper mapper=new ObjectMapper();
	private final fix_Repair_JPAController fix_Repair_jPAController;
    private ObjectMapper orMapper=new ObjectMapper();
    
	@Autowired
	public repairApp_Controller(fix_Repair_JPAController fix_Repair_JPAController) {

		this.fix_Repair_jPAController=fix_Repair_JPAController;
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
	public  String insert_ReportRepair(@RequestBody String content) {   //新增報修申請/結果
		   System.out.println("123123"+content);
		   try {
			report_Repair data=mapper.readValue(content, report_Repair.class);
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
	
	
//	@GetMapping("repairApp_Controller/postFixRepair") 
//	public String postFixRepair() { //完成報修/預時報修
//		  return fix_Repair_jPAController.accept_Repair(null, null, null, null);
//	}
//	
//	
//	@GetMapping("repairApp_Controller/accept_Repair") 
//	public String accept_Repair() { //接收報修
//		  return fix_Repair_jPAController.accept_Repair(null, null, null, null);
//	}
	
	
	
	
	@GetMapping("repairApp_Controller/loadRepairProcessList") 
	public List<report_Repair> loadRepairProcessList(@RequestParam String content) { //帶出報修狀態
		  return fix_Repair_jPAController.load_RepairStatus();
		
	}
	
	public String extend_Repair() {  //延長報修
		
	  return "S";
	}
}
