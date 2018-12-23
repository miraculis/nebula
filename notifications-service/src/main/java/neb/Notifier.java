package neb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class Notifier {
    private static final Logger log = LoggerFactory.getLogger(Notifier.class);

    private final static String acceptTemplate = "Dear Sir/Madam. Your review is accepted";
    private final static String rejectTemplate = "Sear Sir/Madam. Your review is rejected due to bad words";

    @Autowired
    private KafkaTemplate<String, ReviewProcessedMessage> prTmpl;

    @KafkaListener(id = "ntf", topics = "email")
    public void listen(NotifyClientMessage msg) {
        System.out.println("msg " + msg + " received");
        switch (msg.getAction()) {
            case ACCEPT:
                log.info("\""  +acceptTemplate + "\" sent to " + msg.getEmail());
                prTmpl.send("pr-processed", ReviewProcessedMessage.acceptedNtfOf(msg.getPrId()));
                break;
            case REJECT:
                log.info("\"" + rejectTemplate + "\" sent to " + msg.getEmail());
                prTmpl.send("pr-processed", ReviewProcessedMessage.rejectedNtfOf(msg.getPrId()));
                break;
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Notifier.class, args);
    }
}
