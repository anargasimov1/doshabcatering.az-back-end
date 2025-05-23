package az.doshabcatering.doshabcatering.admin.controller;

import az.doshabcatering.doshabcatering.admin.service.AdminMealsService;
import az.doshabcatering.doshabcatering.entity.Meals;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/admin/meals")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AdminMealsController {

    private final AdminMealsService adminMealsService;

    @PostMapping("/{category}")
    public Meals saveMeals(@PathVariable String category,
                           @RequestParam MultipartFile file,
                           @RequestParam String name,
                           @RequestParam String ingredient,
                           @RequestParam Double prince) throws IOException {
        return adminMealsService.save(file, name, ingredient, prince, category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMeals(@PathVariable Integer id) {
        return adminMealsService.delete(id);
    }

}
