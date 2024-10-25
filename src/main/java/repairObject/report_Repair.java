package repairObject;

import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Or GenerationType.SEQUENCE if using sequences
	private Long id;
	public String Report_Account;
	public String Report_Name;
	public String Report_Depart;
	public String Report_Machine;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd HH:mm:ss")
	public LocalDateTime  Report_Date;
	public String Report_Class;
	public String Report_Describe;
	public String Report_Remark;
	public String Report_Key;
	public String Report_Number;
	public String Report_Accept_Emp;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd HH:mm:ss")
	public LocalDateTime  Report_Accept_Date;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd HH:mm:ss")
	public LocalDateTime  Report_Esti_Date;
	public String Report_Status;
	public Boolean Report_Fix_Delay;
	public String Report_Extend_Log_Number;

}
