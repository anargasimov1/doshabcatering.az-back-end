package az.doshabcatering.doshabcatering.controller;

import az.doshabcatering.doshabcatering.entity.Category;
import az.doshabcatering.doshabcatering.servise.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("secure/category")
@RequiredArgsConstructor
public class SecureCategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public Category save(@RequestBody Category category) {
        return categoryService.save(category);
    }

}
