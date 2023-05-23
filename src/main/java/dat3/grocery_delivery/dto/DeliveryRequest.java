package dat3.grocery_delivery.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryRequest {
  @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
  LocalDate deliveryDate;

  String fromWareHouse;
  String destination;
}
