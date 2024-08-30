package jpaConnection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repairObject.report_Repair;

@Repository
public interface repair_Report_JPAInterface extends JpaRepository<report_Repair, Long> {

}
