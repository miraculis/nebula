package neb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class WCService {
    @Autowired
    private WordsChecker wc;

    @Autowired
    @Qualifier(value="ntfKafkaTemplate")
    private KafkaTemplate<String, NotifyClientMessage> ntfTmpl;

    @Autowired
    @Qualifier(value="prKafkaTemplate")
    private KafkaTemplate<String, ReviewProcessedMessage> prTmpl;

    @KafkaListener(id = "wc", topics = "wordscheck")
    public void listen(CheckReviewMessage msg) {
        NotifyClientMessage nmsg = null;
        ReviewProcessedMessage smsg = null;

        smsg = wc.containsBadWords(msg.getReview()) ?
                ReviewProcessedMessage.rejectedOf(msg.getPrId())
                : ReviewProcessedMessage.acceptedOf(msg.getPrId());
        nmsg = NotifyClientMessage.of(msg, smsg);

        prTmpl.send("pr-processed", smsg);
        ntfTmpl.send("email", nmsg);
    }

    public static void main(String[] args) {
        SpringApplication.run(WCService.class, args);
    }
}
