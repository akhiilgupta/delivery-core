package org.delivery.core.service;

import java.util.Optional;
import org.delivery.core.constants.DeliveryConstants;
import org.delivery.core.entities.Delivery;
import org.delivery.core.entities.Person;
import org.delivery.core.enums.DeliveryStatus;
import org.delivery.core.requests.CreateDeliveryRequest;
import org.delivery.core.requests.UpdateDeliveryRequest;
import org.delivery.core.responses.CreateDeliveryResponse;
import org.delivery.core.responses.DeliveryStatusResponse;
import org.delivery.core.utility.DeliveryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryService {

  @Autowired
  private DeliveryHandler deliveryHandler;

  @Autowired
  private PersonHandler personHandler;

  /**
   * Create delivery from {@link CreateDeliveryRequest}. Checks whether an inactive delivery person
   * is present or not.
   * 
   * @param request
   * @return
   */

  public CreateDeliveryResponse createDelivery(CreateDeliveryRequest request) {
    Optional<Person> person = personHandler.getPersonByActive(true);

    if (person.isPresent()) {
      Delivery delivery = deliveryHandler.createDelivery(person.get().getProfileId(), request);
      person.get().getDeliveryIds().add(delivery.getDeliveryId());
      person.get().setActive(true);
      personHandler.updatePerson(person.get());
      return CreateDeliveryResponse.builder().deliveryId(delivery.getDeliveryId())
          .message(DeliveryConstants.SUCCESS).build();
    } else {
      return CreateDeliveryResponse.builder().message(DeliveryConstants.DELIVERY_PERSON_NOT_FOUND)
          .build();
    }
  }

  /**
   * Get status of the delivery using delivery id including time remaining and delivery status
   * 
   * @param deliveryId
   * @return
   */

  public DeliveryStatusResponse getStatus(String deliveryId) {
    Optional<Delivery> deliveryOptional = deliveryHandler.getDeliveryById(deliveryId);
    if (deliveryOptional.isPresent()) {
      Delivery delivery = deliveryOptional.get();

      return DeliveryStatusResponse.builder().message(DeliveryConstants.SUCCESS)
          .status(delivery.getStatus())
          .remainingTime(DeliveryUtils.remainingDeliveryTimeInMinutes(delivery.getDeliveryAddress(),
              delivery.getCreatedAt()))
          .build();
    }
    return DeliveryStatusResponse.builder().message(DeliveryConstants.DELIVERY_NOT_FOUND).build();
  }

  /**
   * Update status of the delivery delegated to a profileId. Fetches person from
   * {@link PersonHandler) and marks it inactive if delivery is completed. Also marks delivery as
   * complete
   * 
   * @param request
   * @return
   */

  public DeliveryStatusResponse updateDelivery(String deliveryId, UpdateDeliveryRequest request) {

    Optional<Delivery> deliveryOptional = deliveryHandler.getDeliveryById(deliveryId);
    if (deliveryOptional.isPresent()
        && canUpdateDeliveryStatus(deliveryOptional.get().getStatus(), request.getStatus())) {
      Delivery delivery = deliveryOptional.get();
      delivery.setStatus(request.getStatus());
      deliveryHandler.updateDelivery(delivery);

      DeliveryStatusResponse response = new DeliveryStatusResponse();

      Optional<Person> person = personHandler.getPersonById(request.getProfileId());
      if (person.isPresent() && DeliveryStatus.DELIVERED.equals(request.getStatus())) {
        person.get().setActive(false);
        personHandler.updatePerson(person.get());
        response.setRemainingTime(DeliveryUtils.remainingDeliveryTimeInMinutes(
            delivery.getDeliveryAddress(), delivery.getCreatedAt()));
      }

      response.setStatus(delivery.getStatus());
      response.setMessage(DeliveryConstants.SUCCESS);
      return response;
    } else {
      // return order not found message if order id not present in records
      return DeliveryStatusResponse.builder().message(DeliveryConstants.DELIVERY_NOT_FOUND).build();
    }
  }

  private boolean canUpdateDeliveryStatus(DeliveryStatus currentStatus, DeliveryStatus nextStatus) {

    // logic to update the status of the delivery based on current state and next state


    return true;
  }

}
