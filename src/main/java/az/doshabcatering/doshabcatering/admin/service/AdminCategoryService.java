package az.doshabcatering.doshabcatering.admin.service;

import az.doshabcatering.doshabcatering.entity.Category;
import az.doshabcatering.doshabcatering.repository.jpaRepo.CategoryRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AdminCategoryService {

    CategoryRepo categoryRepo;

    @CacheEvict(value = "categories", key = "'all'")
    @CachePut(value = "categories", key = "#result.id")
    public Category save(Category category) {
        return categoryRepo.save(category);

    }

    @Caching(evict = {
            @CacheEvict(value = "categories", key = "'all'"),
            @CacheEvict(value = "categories", key = "#id")
    })
    public ResponseEntity<?> delete(Integer id) {
        categoryRepo.deleteById(id);
        return ResponseEntity.ok("categoriya uğurla silindi!");
    }

}
