package az.doshabcatering.doshabcatering.servise.appService;

import az.doshabcatering.doshabcatering.entity.Meals;
import az.doshabcatering.doshabcatering.repository.jpaRepo.MealsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealsService {

    private final MealsRepo mealsRepo;

    @Cacheable(value = "meals", key = "'all'")
    public List<Meals> findAll() {
        return mealsRepo.findAll();
    }

}
