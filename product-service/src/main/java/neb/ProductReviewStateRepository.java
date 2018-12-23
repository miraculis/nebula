package neb;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

interface ProductReviewStateRepository extends CrudRepository<ProductReviewState, UUID> {
}
