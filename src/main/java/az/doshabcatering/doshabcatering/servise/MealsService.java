package az.doshabcatering.doshabcatering.servise;

import az.doshabcatering.doshabcatering.entity.Category;
import az.doshabcatering.doshabcatering.entity.Meals;
import az.doshabcatering.doshabcatering.repository.MealsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MealsService {

    private final MealsRepo mealsRepo;
    public final CategoryService categoryService;

    public List<Meals> findAll() {
        return mealsRepo.findAll();
    }

}
