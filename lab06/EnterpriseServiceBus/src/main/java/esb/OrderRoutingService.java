package esb;

import org.springframework.messaging.Message;

import static org.apache.logging.log4j.util.StringBuilders.equalsIgnoreCase;

public class OrderRoutingService {

    public String routeOrder(Message<Order> message) {
        Order order = message.getPayload();

        if (order.getOrderType() == OrderType.INTERNATIONAL) {
            return "internationalchannel";
        } else if (order.getOrderType() == OrderType.DOMESTIC) {
            if (order.getAmount() > 175) {
                return "nextdaychannel";
            } else {
                return "normalchannel";
            }
        }
        // Default route (optional)
        return "normalchannel";
    }
}

