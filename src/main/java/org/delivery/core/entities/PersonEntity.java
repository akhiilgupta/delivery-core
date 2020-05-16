package org.delivery.core.entities;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {

  @Builder.Default
  private String profileId = RandomStringUtils.randomNumeric(8);

  private String phone;

  private String email;

  @Builder.Default
  private List<String> deliveryIds = new ArrayList<>();

  private boolean active;

}
