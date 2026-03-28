package com.kafka.transform;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class TransformingData {

    public static void main(String[] args) {
        SpringApplication.run(TransformingData.class, args);
    }

    @Component
    public static class DataProcessor {

        private final RabbitTemplate rabbitTemplate;

        public DataProcessor(RabbitTemplate rabbitTemplate) {
            this.rabbitTemplate = rabbitTemplate;
        }

        @RabbitListener(queuesToDeclare = @Queue(name = "source_queue"))
        public void handleMessage(String value) {
            int res = 0;

            String[] fileData = value.split(",");
            String operator = fileData[2];

            switch (operator) {
                case "+":
                    res = Integer.parseInt(fileData[0]) + Integer.parseInt(fileData[1]);
                    break;
                case "-":
                    res = Integer.parseInt(fileData[0]) - Integer.parseInt(fileData[1]);
                    break;
                case "*":
                    res = Integer.parseInt(fileData[0]) * Integer.parseInt(fileData[1]);
                    break;
                case "/":
                    res = Integer.parseInt(fileData[0]) / Integer.parseInt(fileData[1]);
                    break;
                default:
                    // unsupported operator, keep result as 0
                    break;
            }

            String result = value + "," + res;
            rabbitTemplate.convertAndSend("target_queue", result);
        }
    }
}