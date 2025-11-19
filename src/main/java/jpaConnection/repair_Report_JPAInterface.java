package jpaConnection;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import repairObject.fix_Repair;
import repairObject.report_Repair;

@Repository
public interface repair_Report_JPAInterface extends JpaRepository<report_Repair, Long> {
//	@Modifying 
//	@Transactional 
//	@Query("Update report_Repair r SET r.Report_Status='Process', r.Report_Accept_Emp=?2,r.Report_Accept_Date=?,r.Report_Esti_Date=?4 WHERE Report_Number=?1")
//	int acceptRepari(String List_Number,String Report_Accept_Emp,Date Report_Accept_Date,Date Report_Esti_Date);
	
	
    @Modifying
    @Transactional
 	@Query("Update report_Repair r SET r.Report_Status=?1, r.Report_Accept_Emp=?3,r.Report_Accept_Date=?4,r.Report_Esti_Date=?5 WHERE Report_Number=?2")
    int AcceptRepair(String AcceptTtpe,String List_Number,String Report_Accept_Emp,LocalDateTime acceptDate,LocalDateTime estiDate);
	
	@Modifying
    @Transactional
	@Query("SELECT r FROM report_Repair r  WHERE MONTH(r.Report_Date) = MONTH(CURRENT_DATE) AND YEAR(r.Report_Date) = YEAR(CURRENT_DATE) ORDER BY r.Report_Date DESC")
	List<report_Repair> findAllOrderBy();
	
    @Transactional
	@Query("SELECT r FROM report_Repair r  WHERE r.Report_Date BETWEEN ?1 AND ?2 ORDER BY r.Report_Date DESC")
	List<report_Repair> findDateOrderBy(LocalDateTime start,LocalDateTime end);

}
