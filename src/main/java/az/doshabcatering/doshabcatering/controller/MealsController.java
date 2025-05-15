package az.doshabcatering.doshabcatering.controller;

import az.doshabcatering.doshabcatering.entity.Meals;
import az.doshabcatering.doshabcatering.servise.appService.MealsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("public/meals")
@RequiredArgsConstructor
public class MealsController {
    private final MealsService mealsService;

    @GetMapping
    public List<Meals> findAll() {
        return mealsService.findAll();
    }

}
