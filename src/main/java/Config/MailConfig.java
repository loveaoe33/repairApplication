package Config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration  
public class MailConfig {

	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender=new JavaMailSenderImpl();

        // 配置郵件服務器
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);  // 使用端口587以支持STARTTLS
        mailSender.setUsername("loveaoe33@gmail.com");  // 您的郵箱
        mailSender.setPassword("	");        // 郵箱密碼（如果啟用了兩步驟驗證，請使用應用專用密碼）

        // 設置郵件屬性，啟用STARTTLS加密
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");  // 啟用 STARTTLS
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com"); // 信任 gmail 服務器


        return mailSender;  // 返回配置好的 JavaMailSender 實例
		
	}
}
