package neb;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.UUID;

interface ProductReviewRepository extends CrudRepository<ProductReview, UUID> {
}