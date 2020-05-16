package org.delivery.core.responses;

import org.delivery.core.enums.DeliveryStatus;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DeliveryStatusResponse {

  private String message;

  private DeliveryStatus status;

  private int remainingTime;

}
