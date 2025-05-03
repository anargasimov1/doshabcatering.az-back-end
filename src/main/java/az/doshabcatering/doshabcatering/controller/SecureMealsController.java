package az.doshabcatering.doshabcatering.controller;

import az.doshabcatering.doshabcatering.entity.Meals;
import az.doshabcatering.doshabcatering.servise.MealsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestControllerAdvice
@RequestMapping("/secure/meals")
@RequiredArgsConstructor
public class SecureMealsController {

    public final MealsService mealsService;

    @PostMapping("/{category}")
    public Meals save(@RequestParam MultipartFile file,
                      @RequestParam String name,
                      @RequestParam String ingredient,
                      @RequestParam Double prince,
                      @PathVariable String category) throws IOException {
        return mealsService.save(file,name,ingredient,prince,category);
    }
}
