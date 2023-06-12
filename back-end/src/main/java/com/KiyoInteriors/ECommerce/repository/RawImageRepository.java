package com.KiyoInteriors.ECommerce.repository;

import com.KiyoInteriors.ECommerce.entity.RawImage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RawImageRepository extends MongoRepository<RawImage, String> {

}
