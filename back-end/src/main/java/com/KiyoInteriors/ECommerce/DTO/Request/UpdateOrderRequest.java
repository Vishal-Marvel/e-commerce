package com.KiyoInteriors.ECommerce.DTO.Request;

import lombok.Data;
/**
 * A class representing an Update Order request.
 * This class encapsulates the information required to update the status of an order.
    * The ID of the order to be updated.
       * The updated status for the order.
 */
@Data
public class UpdateOrderRequest {
    private String orderId;
    private String updatedStatus;
}
