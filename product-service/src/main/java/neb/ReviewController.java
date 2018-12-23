package neb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestController
@RequestMapping(path="api")
@SpringBootApplication
@EnableCassandraRepositories
public class ReviewController {
    private static class ErrorDetails {
        private String message;
        private String details;
        private Date timestamp;

        public ErrorDetails(String message, String details, Date timestamp) {
            this.message = message;
            this.details = details;
            this.timestamp = timestamp;
        }
    }

    @Autowired
    private ProductReviewRepository repository;

    @Autowired
    private ProductReviewStateRepository stateRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String, CheckReviewMessage> kafkaTemplate;

    public void sendMessage(CheckReviewMessage msg) {
        kafkaTemplate.send("wordscheck", msg);
    }

    @PostMapping("/reviews")
    @ResponseBody
    ReviewResponse newReview(@RequestBody Review newReview) {
        ProductReview pr = ProductReview.of(newReview);
        if (productRepository.existsById(pr.getProductId())) {
            repository.save(pr);
            sendMessage(pr.toCheckMsg());
            return ReviewResponse.of(pr);
        } else {
            return ReviewResponse.rejectOf(pr);
        }
    }

    @KafkaListener(id = "wc", topics = "pr-processed")
    public void listen(ReviewProcessedMessage msg) {
        stateRepository.save(ProductReviewState.of(msg));
    }


    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(RuntimeException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails("Internal server error",
                ex.getMessage(), new Date());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static void main(String[] args) {
        SpringApplication.run(ReviewController.class, args);
    }
}