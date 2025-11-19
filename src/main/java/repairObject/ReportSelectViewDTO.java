package repairObject;

import java.time.LocalDateTime;

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
public class ReportSelectViewDTO {
    public String Report_Account;
    public String Report_Name;
    public String Report_Depart;
    public String Report_Machine;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd HH:mm:ss")
    public LocalDateTime Report_Date;
    
    public String Report_Class;
    public String Report_Describe;
    public String Report_Remark;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd HH:mm:ss")
    public LocalDateTime Report_Accept_Date;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd HH:mm:ss")
    public LocalDateTime Report_Esti_Date;
    
    public String Report_Status;
    public Boolean Report_Fix_Delay;
    
    public String Fix_Accept_Account; 
    public String Fix_Account_Name; 
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd HH:mm:ss")
    public LocalDateTime Fix_Est_Date; 
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd HH:mm:ss")
    public LocalDateTime Fix_Finish_Date;
    public String Fix_Remark; 
    public Integer Fix_Level; 
    
}
