package jpaConnection;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import repairObject.fix_Repair;


@Repository
public interface fix_Repair_JPAInterface extends JpaRepository<fix_Repair, Long>{
	@Modifying
    @Transactional
 	@Query("Update report_Repair r SET r.Report_Status=?1, r.Report_Fix_Delay=?2 ,r.Report_Extend_Log_Number=?4 WHERE r.Report_Number=?3" )
	int UpdateRepairTable(String Status,Boolean DelayStatus,String Report_Number,String DelayNumber);

	@Modifying
    @Transactional
 	@Query("Select r FROM fix_Repair r WHERE MONTH(r.Fix_Finish_Date) = MONTH(CURRENT_DATE) AND YEAR(r.Fix_Finish_Date) = YEAR(CURRENT_DATE)" )
	List<fix_Repair> SelectFinishTable();
    
}
