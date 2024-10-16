package jpaConnection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repairObject.fix_Repair;


@Repository
public interface fix_Repair_JPAInterface extends JpaRepository<fix_Repair, Long>{

}
