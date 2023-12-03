package com.rlgino.CardsService.infrastructure.bus.events;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.rlgino.CardsService.domain.Card;
import com.rlgino.CardsService.domain.events.CardCreatedEvent;
import org.springframework.stereotype.Component;

@Component("cardCreatorNotification")
public class AWSSQSNotificator implements CardCreatedEvent {

    final String QUEUE_NAME = "cards-service-queue";

    @Override
    public void Send(Card card) {
        try {
            final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
            GetQueueUrlResult queueUrl = sqs.getQueueUrl(QUEUE_NAME);

            SendMessageRequest send_msg_request = new SendMessageRequest()
                    .withQueueUrl(queueUrl.getQueueUrl())
                    .withMessageBody(card.toString())
                    .withDelaySeconds(5);
            sqs.sendMessage(send_msg_request);
        } catch (Exception e) {
            System.out.println("Ignore error probably for bad connection");
        }
    }
}
