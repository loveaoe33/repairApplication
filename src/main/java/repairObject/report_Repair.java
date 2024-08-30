package repairObject;

import java.sql.Date;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "report_repair")
public class report_Repair {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   private String Report_Account;
   private String Report_Depart;
   private String Report_Machine;
   private String Report_Date;
   private String Report_Class;
   private String Report_Describe;
   private String Report_Remark;
   private String Report_Key;
   private String Report_Number;
   private String Report_Status;
   
}
