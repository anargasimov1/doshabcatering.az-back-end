package az.doshabcatering.doshabcatering.admin.categoryService;

import az.doshabcatering.doshabcatering.entity.Category;
import az.doshabcatering.doshabcatering.repository.CategoryRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AdminCategoryService {

    CategoryRepo categoryRepo;

    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    public ResponseEntity<?> delete(Integer id) {
        categoryRepo.deleteById(id);
        return ResponseEntity.ok("categoriya uğurla silindi!");
    }

}
