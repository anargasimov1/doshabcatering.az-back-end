package az.doshabcatering.doshabcatering.admin.categoryController;

import az.doshabcatering.doshabcatering.admin.categoryService.AdminCategoryService;
import az.doshabcatering.doshabcatering.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/category")
@RequiredArgsConstructor
public class AdminCategoryController {
    private final AdminCategoryService adminCategoryService;

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return adminCategoryService.save(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        return adminCategoryService.delete(id);
    }
}
