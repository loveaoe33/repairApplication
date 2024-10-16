package jpaConnection;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import repairObject.fix_Repair;
import repairObject.report_Repair;





@Service
public class fix_Repair_JPAController {

	
	private final repair_Report_JPAInterface repair_Report_jPAInterface;
	private final fix_Repair_JPAInterface fix_Repair_jPAInterface;
	private final fix_Data_Log_JPAInterface fix_Data_Log_jPAInterface;
	ObjectMapper mapper=new ObjectMapper();
	
	@Autowired
	public fix_Repair_JPAController(fix_Repair_JPAInterface fix_Repair_jPAInterface,repair_Report_JPAInterface repair_Report_jPAInterface,fix_Data_Log_JPAInterface fix_Data_Log_jPAInterface) {
		this.repair_Report_jPAInterface=repair_Report_jPAInterface;		
		this.fix_Repair_jPAInterface=fix_Repair_jPAInterface;
		this.fix_Data_Log_jPAInterface=fix_Data_Log_jPAInterface;
	}
	
	public List<report_Repair> load_RepairStatus() {  //叫出報修紀錄
		List<report_Repair> result=repair_Report_jPAInterface.findAll();
		if(result.size()>0) {
			System.out.println(result.get(0));

			return result;
		}else {
			System.out.println("s"+result.get(0));

			return null;

		}
		
	}
	
	public List<report_Repair> load_DateRepairStatus() {  //叫出特定日期報修紀錄
		return null;
	}
	
	public String insert_repair_Report(report_Repair report_Repair) { //新增報修單 OK
		try {
			report_Repair result= repair_Report_jPAInterface.save(report_Repair);
			return "Sucess";
			
		}catch(DataAccessException e) {
			
			System.out.print(e);
            return "fail";
		}
		
	}
	
	public String delete_repair_Report(Long List_Id) {   //刪除報修單 OK
		try {
			
			repair_Report_jPAInterface.deleteById(List_Id);
			return "Sucess";
		}catch(DataAccessException e) {

			return "fail";
		}
		
	}
	
	public String accept_Repair(@RequestParam String content ) {  //接受報修單 OK
		try {
			JsonNode node=mapper.readTree(content);
			repair_Report_jPAInterface.acceptRepari( node.get("List_Number").asText(), Report_Accept_Emp, Report_Accept_Date, Report_Esti_Date);
			return "Sucess";
		}catch(DataAccessException e) {	
			System.out.print(e);
            return "fail";
		}
	}
	
	
	
	public String insert_fix_Repair(fix_Repair fix_repair) {   //新增報修紀錄
		fix_Repair result= fix_Repair_jPAInterface.save(fix_repair);
	   return "S";	
	}
	
	public String insert_fix_Repair_Delay(fix_Repair fix_repair) {   //新增預期報修紀錄
		fix_Repair result= fix_Repair_jPAInterface.save(fix_repair);
	   return "S";	
	}
	

	
	public String select_fix_Data_Log() {//調閱報表
		
		return "S";
	}
	
	
	
	
	
}
