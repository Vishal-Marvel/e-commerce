package com.KiyoInteriors.ECommerce.repository;

import com.KiyoInteriors.ECommerce.entity.DiscountCoupon;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DiscountCouponRepository extends MongoRepository<DiscountCoupon, String> {
    boolean existsByCouponCode(String coupon);
    Optional<DiscountCoupon> findByCouponCode(String coupon);
}
