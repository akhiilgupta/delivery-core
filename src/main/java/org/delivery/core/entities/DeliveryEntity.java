package org.delivery.core.entities;

import java.time.LocalDateTime;
import org.apache.commons.lang3.RandomStringUtils;
import org.delivery.core.enums.DeliveryStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class DeliveryEntity {

  @Builder.Default
  private String deliveryId = RandomStringUtils.randomNumeric(8);

  private String orderId;

  private String deliveryAddress;

  private String deliveredBy;

  @Builder.Default
  private DeliveryStatus status = DeliveryStatus.ACCEPTED;

  private LocalDateTime createdAt;

}
