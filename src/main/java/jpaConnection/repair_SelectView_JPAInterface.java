package jpaConnection;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import jakarta.transaction.Transactional;
import repairObject.FixSelectViewDTO;
import repairObject.ReportSelectViewDTO;
import repairObject.report_Repair;
import repairObject.fix_Repair;


public interface repair_SelectView_JPAInterface extends JpaRepository<report_Repair, Long>{
	@Transactional
    @Query("SELECT new repairObject.ReportSelectViewDTO(" +
            "rr.Report_Account, rr.Report_Name, rr.Report_Depart, rr.Report_Machine, " +
            "rr.Report_Date, rr.Report_Class, rr.Report_Describe, rr.Report_Remark, " +
            "rr.Report_Accept_Date, rr.Report_Esti_Date, rr.Report_Status, " +
            "rr.Report_Fix_Delay, fr.Fix_Accept_Account, fr.Fix_Account_Name, " +
            "fr.Fix_Est_Date, fr.Fix_Finish_Date, fr.Fix_Remark, fr.Fix_Level) " +
            "FROM report_Repair rr " +
            "LEFT JOIN fix_Repair fr ON rr.Report_Number = fr.Fix_Number " +
            "WHERE rr.Report_Account = ?1 " +
            "AND rr.Report_Date BETWEEN ?2 AND ?3")
    List<ReportSelectViewDTO> QueryReportHistory(String empAccount, LocalDateTime start, LocalDateTime end);
    
	
	
	@Transactional
	@Query("SELECT new repairObject.FixSelectViewDTO(" +
	        "r.Fix_Accept_Account, r.Fix_Account_Name, rr.Report_Depart, rr.Report_Machine, " +
	        "rr.Report_Class, rr.Report_Date, rr.Report_Accept_Date, r.Fix_Est_Date, " +
	        "r.Fix_Finish_Date,r.Fix_Status, r.Fix_Remark, r.Fix_Level, " +
	        "r.Fix_Draw, rr.Report_Fix_Delay) " +  // 這裡添加了逗號
	        "FROM fix_Repair r " +
	        "INNER JOIN report_Repair rr ON r.Fix_Number = rr.Report_Number " +
	        "WHERE r.Fix_Accept_Account = ?1 " +
	        "AND r.Fix_Finish_Date BETWEEN ?2 AND ?3")
    List<FixSelectViewDTO> QueryFixHistory(String empAccount, LocalDateTime start, LocalDateTime end);
	
	

    
}
