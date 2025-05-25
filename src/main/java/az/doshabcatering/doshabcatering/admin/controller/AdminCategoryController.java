package az.doshabcatering.doshabcatering.admin.controller;

import az.doshabcatering.doshabcatering.admin.service.AdminCategoryService;
import az.doshabcatering.doshabcatering.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/category")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
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
