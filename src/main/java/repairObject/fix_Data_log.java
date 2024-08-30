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
@Table(name = "fix_date_log")
public class fix_Data_log {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	private String Log_Number;
	private Date Ori_Date;
	private Date Exten_Date;
	private String Exten_Reassion;
	
}
