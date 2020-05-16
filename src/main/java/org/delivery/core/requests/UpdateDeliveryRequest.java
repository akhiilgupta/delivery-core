package org.delivery.core.requests;

import org.delivery.core.enums.DeliveryStatus;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UpdateDeliveryRequest {

  private String profileId;

  private DeliveryStatus status;

}
