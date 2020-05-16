package org.delivery.core.entities;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Person {

  private String profileId;

  private String phone;

  private boolean active;

  private String email;

  private List<String> deliveryIds;

  public Person(PersonEntity person) {
    this.profileId = person.getProfileId();
    this.phone = person.getPhone();
    this.email = person.getEmail();
    this.active = person.isActive();
    this.deliveryIds = new ArrayList<>(person.getDeliveryIds());
  }
}
