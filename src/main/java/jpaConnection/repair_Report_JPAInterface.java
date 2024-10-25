package jpaConnection;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import repairObject.report_Repair;

@Repository
public interface repair_Report_JPAInterface extends JpaRepository<report_Repair, Long> {
//	@Modifying 
//	@Transactional 
//	@Query("Update report_Repair r SET r.Report_Status='Process', r.Report_Accept_Emp=?2,r.Report_Accept_Date=?,r.Report_Esti_Date=?4 WHERE Report_Number=?1")
//	int acceptRepari(String List_Number,String Report_Accept_Emp,Date Report_Accept_Date,Date Report_Esti_Date);
	
	
    @Modifying
    @Transactional
 	@Query("Update report_Repair r SET r.Report_Status='Process', r.Report_Accept_Emp=?2,r.Report_Accept_Date=?3,r.Report_Esti_Date=?4 WHERE Report_Number=?1")
    int AcceptRepair(String List_Number,String Report_Accept_Emp,LocalDateTime acceptDate,LocalDateTime estiDate);
	
    
}
