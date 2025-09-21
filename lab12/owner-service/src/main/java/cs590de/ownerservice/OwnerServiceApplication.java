package cs590de.ownerservice;

import cs590de.ownerservice.model.OwnerEnrichedEvent;
import cs590de.ownerservice.model.OwnerInfo;
import cs590de.ownerservice.model.SpeedingEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Map;

@SpringBootApplication
public class OwnerServiceApplication {
    public static void main(String[] args){ SpringApplication.run(OwnerServiceApplication.class, args); }

    @Bean
    public Map<String, OwnerInfo> ownerDb(){
        return Map.of(
                "ABC123", new OwnerInfo("ABC123","Alice Johnson","123 Main St, Dallas, TX"),
                "XYZ999", new OwnerInfo("XYZ999","Bob Smith","42 Sunset Blvd, Plano, TX"),
                "VIZ101", new OwnerInfo("VIZ101","Vizio QA Fleet","1 TV Way, Irvine, CA")
        );
    }

    private final KafkaTemplate<String, Object> template;
    private final Map<String, OwnerInfo> db;

    public OwnerServiceApplication(KafkaTemplate<String, Object> template, Map<String, OwnerInfo> db){
        this.template = template; this.db = db;
    }

    @KafkaListener(topics = "speeding", groupId = "owner-svc")
    public void onSpeeding(SpeedingEvent evt){
        OwnerInfo info = db.getOrDefault(evt.getLicensePlate(),
                new OwnerInfo(evt.getLicensePlate(),"Unknown Owner","Unknown Address"));

        System.out.printf("[Owner] %s -> %s, %s%n",
                evt.getLicensePlate(), info.getName(), info.getAddress());

        OwnerEnrichedEvent out = new OwnerEnrichedEvent(
                evt.getLicensePlate(), evt.getSpeedMph(), info.getName(), info.getAddress());

        template.send("speeding.withOwner", evt.getLicensePlate(), out);
    }
}