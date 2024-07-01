package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dtos.SendEmailEventDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class SendEmailController {
    private KafkaTemplate<String, String> kafkaTemplate;
    private ObjectMapper objectMapper;

    public SendEmailController(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/{emailId}")
    public SendEmailEventDto sendEmail(@PathVariable("emailId") String emailId) {
        SendEmailEventDto sendEmailEventDto = new SendEmailEventDto();
        sendEmailEventDto.setTo(emailId);
        sendEmailEventDto.setSubject("Test Email");
        sendEmailEventDto.setBody("This is a test email");
        sendEmailEventDto.setFrom("koutilyabhatt@gmail.com");
        try {
            kafkaTemplate.send("sendEmail", objectMapper.writeValueAsString(sendEmailEventDto));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sendEmailEventDto;
    }
}
