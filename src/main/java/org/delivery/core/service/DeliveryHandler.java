package org.delivery.core.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.delivery.core.entities.Delivery;
import org.delivery.core.entities.DeliveryEntity;
import org.delivery.core.requests.CreateDeliveryRequest;
import org.springframework.stereotype.Component;

@Component
public class DeliveryHandler {

  private static final List<DeliveryEntity> deliveriesList =
      Collections.synchronizedList(new ArrayList<>());

  /**
   * Get delivery optional by delivery id
   * 
   * @param deliveryId
   * @return
   */

  public Optional<Delivery> getDeliveryById(String deliveryId) {

    return deliveriesList.stream().filter(d -> d.getDeliveryId().equals(deliveryId)).findAny()
        .map(Delivery::new);
  }

  /**
   * Update delivery entity in deliveriesList
   * 
   * @param delivery
   * @return
   */

  public Optional<Object> updateDelivery(Delivery delivery) {
    for (DeliveryEntity deliveryEntity : deliveriesList) {
      if (deliveryEntity.getDeliveryId().equals(delivery.getDeliveryId())) {
        deliveryEntity.setStatus(delivery.getStatus());
        deliveryEntity.setDeliveredBy(delivery.getDeliveredBy());
        return Optional.of(delivery);
      }
    }
    return Optional.empty();
  }

  /**
   * Create Delivery from create delivery request
   * 
   * @param profileId
   * @param request
   * @return
   */

  public Delivery createDelivery(String profileId, CreateDeliveryRequest request) {
    DeliveryEntity deliveryEntity =
        DeliveryEntity.builder().createdAt(LocalDateTime.now()).deliveredBy(profileId)
            .deliveryAddress(request.getAddress()).orderId(request.getOrderId()).build();
    deliveriesList.add(deliveryEntity);
    return new Delivery(deliveryEntity);
  }



}
