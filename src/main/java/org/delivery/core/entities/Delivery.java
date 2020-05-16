package org.delivery.core.entities;

import java.time.LocalDateTime;
import org.delivery.core.enums.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class Delivery {

  private String deliveryId;

  private String orderId;

  private String deliveryAddress;

  private String deliveredBy;

  private DeliveryStatus status;

  private LocalDateTime createdAt;

  public Delivery(DeliveryEntity delivery) {
    this.deliveryId = delivery.getDeliveryId();
    this.deliveredBy = delivery.getDeliveredBy();
    this.orderId = delivery.getOrderId();
    this.deliveryAddress = delivery.getDeliveryAddress();
    this.status = delivery.getStatus();
    this.createdAt = delivery.getCreatedAt();
  }
}
