package repairObject;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Query;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FixSelectViewDTO {
	
 
	 // Fix_Accept_Account
    public String Fix_Accept_Account;
    
    // Fix_Account_Name
    public String Fix_Account_Name;
    
    // Report_Depart
    public String Report_Depart;
    
    // Report_Machine
    public String Report_Machine;
    
    // Report_Class
    public String Report_Class;
    
    // Report_Date
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd HH:mm:ss")
    public LocalDateTime Report_Date;
    
    // Report_Accept_Date
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd HH:mm:ss")
    public LocalDateTime Report_Accept_Date;
    
    // Fix_Est_Date
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd HH:mm:ss")
    public LocalDateTime Fix_Est_Date;
    
    // Fix_Finish_Date
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd HH:mm:ss")
    public LocalDateTime Fix_Finish_Date;
    
    // Fix_Status
	public String Fix_Status;

    // Fix_Remark
    public String Fix_Remark;
    
    // Fix_Level
    public Integer Fix_Level;
    
    // Fix_Draw
    public String Fix_Draw;
    
    // Report_Fix_Delay
    public Boolean Report_Fix_Delay;
}
