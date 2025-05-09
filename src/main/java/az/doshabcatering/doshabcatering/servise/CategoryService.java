package az.doshabcatering.doshabcatering.servise;

import az.doshabcatering.doshabcatering.entity.Category;
import az.doshabcatering.doshabcatering.repository.CategoryRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {

   CategoryRepo categoryRepo;

    public Category findByName(String name) {
        return categoryRepo.findByName(name).orElse(null);
    }

    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

}
