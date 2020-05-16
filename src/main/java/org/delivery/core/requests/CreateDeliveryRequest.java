package org.delivery.core.requests;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CreateDeliveryRequest {

  @NotNull
  private String orderId;

  @NotNull
  private String address;
  
  private String phone;
}
