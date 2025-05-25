package az.doshabcatering.doshabcatering.controller;

import az.doshabcatering.doshabcatering.entity.Category;
import az.doshabcatering.doshabcatering.servise.appService.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("public/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<Category>  findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/find")
    public Category findByName(@RequestParam String name) {
        return categoryService.findByName(name);
    }

}
