package cs590de.feeservice;

import cs590de.feeservice.model.OwnerEnrichedEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class FeeServiceApplication {
    public static void main(String[] args){ SpringApplication.run(FeeServiceApplication.class, args); }

    @KafkaListener(topics = "speeding.withOwner", groupId = "fee-svc")
    public void onOwnerEnriched(OwnerEnrichedEvent evt){
        double mph = evt.getSpeedMph();
        int fee = (mph > 90) ? 125 :
                (mph > 82) ? 80  :
                        (mph > 77) ? 45  :
                                (mph > 72) ? 25  : 0;

        System.out.printf("[Fee] %s | %s | %.2f mph | Fee: $%d | %s%n",
                evt.getLicensePlate(), evt.getOwnerName(), mph, fee, evt.getOwnerAddress());

    }
}

