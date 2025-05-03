package az.doshabcatering.doshabcatering.admin.mealsService;

import az.doshabcatering.doshabcatering.entity.Category;
import az.doshabcatering.doshabcatering.entity.Meals;
import az.doshabcatering.doshabcatering.repository.MealsRepo;
import az.doshabcatering.doshabcatering.servise.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminMealsService {

    private final MealsRepo mealsRepo;
    private final CategoryService categoryService;

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/images";

    @Value("${urlApp}")
    private String url;

    public Meals save(MultipartFile file, String name, String ingredient, Double price, String category1) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Empty file");
        }

        Path storageDirectory = Path.of(UPLOAD_DIR);

        if (Files.notExists(storageDirectory)) {
            Files.createDirectories(storageDirectory);
        }

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path targetPath = storageDirectory.resolve(fileName);

        String imageUrl = url +"/uploads/images/" + fileName;

        Files.write(targetPath, file.getBytes());

        Category category = categoryService.findByName(category1);
        if (category == null) {
            throw new RuntimeException("Category not found");
        }

        Meals meals = new Meals();
        meals.setName(name);
        meals.setIngredients(ingredient);
        meals.setPrice(price);
        meals.setImageUrl(imageUrl);
        meals.setCategory(category);

        return mealsRepo.save(meals);
    }

    public ResponseEntity<?> delete(Integer id){
         mealsRepo.deleteById(id);
         return ResponseEntity.ok("uğurla silindi!");
    }
}

