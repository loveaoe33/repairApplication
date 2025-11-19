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
@Table(name = "fix_repair")
public class fix_Repair {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	public String Fix_Number;
	public String Fix_Accept_Account;
	public String Fix_Account_Name;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd HH:mm:ss")
	public LocalDateTime Fix_Est_Date;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd HH:mm:ss")
	public LocalDateTime Fix_Finish_Date;
	public String Fix_Status;
	public String Fix_Remark;
	public int Fix_Level;
	public String Fix_Draw;


	
}
