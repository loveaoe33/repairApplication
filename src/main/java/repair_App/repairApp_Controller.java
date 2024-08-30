package repair_App;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class repairApp_Controller {
	@GetMapping("/") 
	public String test2() {
		return "123";
	}
	
	
	public  String insert_ReportRepair() {   //新增報修申請
		  return "S";

	}
	
	public String insert_FixRepair() {  //新增維修結果
		  return "S";

	}
	
	public String load_RepairStatus() { //帶出報修狀態
		  return "S";

		
	}
	
	public String extend_Repair() {  //延長報修
		
	  return "S";
	}
}
