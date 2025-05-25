package az.doshabcatering.doshabcatering.servise.appService;

import az.doshabcatering.doshabcatering.entity.Category;
import az.doshabcatering.doshabcatering.repository.jpaRepo.CategoryRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {

    CategoryRepo categoryRepo;

    @Cacheable(value = "categories", key = "#name")
    public Category findByName(String name) {
        return categoryRepo.findByName(name).orElse(null);
    }

    @Cacheable(value = "categories", key = "'all'")
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

}
