package az.doshabcatering.doshabcatering.admin.service;

import az.doshabcatering.doshabcatering.entity.Category;
import az.doshabcatering.doshabcatering.repository.jpaRepo.CategoryRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AdminCategoryService {

    CategoryRepo categoryRepo;

    public ResponseEntity<?> save(Category category) {
        categoryRepo.save(category);
        return ResponseEntity.ok("uğurla əlavə edildi!");
    }

    public ResponseEntity<?> delete(Integer id) {
        categoryRepo.deleteById(id);
        return ResponseEntity.ok("categoriya uğurla silindi!");
    }

}
