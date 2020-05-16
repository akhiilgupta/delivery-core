package org.delivery.core.controllers;

import javax.validation.Valid;
import org.delivery.core.requests.CreateDeliveryRequest;
import org.delivery.core.requests.UpdateDeliveryRequest;
import org.delivery.core.responses.CreateDeliveryResponse;
import org.delivery.core.responses.DeliveryStatusResponse;
import org.delivery.core.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/delivery")
public class DeliveryController {

  @Autowired
  private DeliveryService deliveryService;

  @PostMapping
  public CreateDeliveryResponse createDelivery(@Valid @RequestBody CreateDeliveryRequest request) {
    return deliveryService.createDelivery(request);
  }

  @GetMapping("/{delivery_id}")
  public DeliveryStatusResponse getStatus(
      @PathVariable(value = "delivery_id", required = true) final String deliveryId) {
    return deliveryService.getStatus(deliveryId);
  }

  @PutMapping("/{delivery_id}")
  public DeliveryStatusResponse updateDelivery(
      @PathVariable(value = "delivery_id", required = true) final String deliveryId,
      @Valid @RequestBody UpdateDeliveryRequest request) {
    return deliveryService.updateDelivery(deliveryId, request);
  }
}
