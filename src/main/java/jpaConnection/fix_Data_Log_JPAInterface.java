package jpaConnection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repairObject.fix_Data_log;


@Repository
public interface fix_Data_Log_JPAInterface extends JpaRepository<fix_Data_log, Long>{

}
