package com.KiyoInteriors.ECommerce.repository;

import com.KiyoInteriors.ECommerce.entity.DiscountCoupon;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
/**
 * A repository interface for managing discount coupons in a MongoDB database.
 * This interface extends the MongoRepository interface, providing CRUD operations and query methods for the DiscountCoupon entity.
 */
public interface DiscountCouponRepository extends MongoRepository<DiscountCoupon, String> {
    boolean existsByCouponCode(String coupon);
    Optional<DiscountCoupon> findByCouponCode(String coupon);
}
