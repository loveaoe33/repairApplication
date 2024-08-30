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
@Table(name = "fix_repair")
public class fix_Repair {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String Fix_Number;
	private String Fix_Accept_Account;
	private Date Fix_Accept_Date;
	private Date Fix_Est_Date;
	private Date Fix_Finish_Date;
	private String Fix_Remark;
	private int Fix_Level;
	private String Fix_Key;
	private String Fix_Draw;

	private String Fix_Delay;

	private String Log_Number;


	
}
