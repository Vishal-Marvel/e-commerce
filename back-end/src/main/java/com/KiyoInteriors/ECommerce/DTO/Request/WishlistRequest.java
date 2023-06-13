package com.KiyoInteriors.ECommerce.DTO.Request;

import lombok.Data;
/**
 * A class representing a Wishlist request.
 * This class encapsulates the information required to add a product to the wishlist.
*/
/**
 * The ID of the product to be added to the wishlist.
 */
@Data
public class WishlistRequest {
    private String productId;
}
