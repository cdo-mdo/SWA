package cs590de.speedservice;

import cs590de.speedservice.model.SensorRecord;
import cs590de.speedservice.model.SpeedingEvent;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.time.Duration;

@SpringBootApplication
@EnableKafkaStreams
public class SpeedServiceApplication {
    public static void main(String[] args) { SpringApplication.run(SpeedServiceApplication.class, args); }

    @Bean
    public KStream<String, SpeedingEvent> kStream(StreamsBuilder builder){
        Serde<SensorRecord> sensorSerde = new JsonSerde<>(SensorRecord.class);
        Serde<SpeedingEvent> speedingSerde = new JsonSerde<>(SpeedingEvent.class);

        KStream<String, SensorRecord> cam1 = builder.stream("cameratopic1",
                Consumed.with(Serdes.String(), sensorSerde)).selectKey((k, v)->v.getPlate());

        KStream<String, SensorRecord> cam2 = builder.stream("cameratopic2",
                Consumed.with(Serdes.String(), sensorSerde)).selectKey((k,v)->v.getPlate());

        KStream<String, SpeedingEvent> speeding = cam1.join(
                cam2,
                (r1, r2) -> {
                    int t1 = r1.getMinute()*60 + r1.getSecond();
                    int t2 = r2.getMinute()*60 + r2.getSecond();
                    int delta = t2 - t1; while (delta <= 0) delta += 60;
                    double mph = 0.5 / delta * 3600.0;
                    return new SpeedingEvent(r1.getPlate(), mph, t1, t2);
                },
                JoinWindows.ofTimeDifferenceWithNoGrace(Duration.ofMinutes(2)),
                StreamJoined.with(Serdes.String(), sensorSerde, sensorSerde)
        ).filter((plate, evt) -> evt.getSpeedMph() > 72.0);

        speeding.peek((plate, evt) -> System.out.printf("[Speed] %s -> %.2f mph%n", plate, evt.getSpeedMph()));
        speeding.to("speeding", Produced.with(Serdes.String(), speedingSerde));
        return speeding;
    }
}
