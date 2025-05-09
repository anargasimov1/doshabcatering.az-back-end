package az.doshabcatering.doshabcatering.controller;


import az.doshabcatering.doshabcatering.documents.Users;
import az.doshabcatering.doshabcatering.servise.ElasticService;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.elasticsearch.core.query.Order.Mode.min;

@RestController
@RequestMapping("/public/elastic")
@RequiredArgsConstructor
public class ElasticController {

    private final ElasticService elasticService;

    @GetMapping
    public Iterable<Users> findAll(String keyword) {
        return elasticService.findAll();
    }

    @PostMapping
    public Users save(@RequestBody Users users) {
        return elasticService.save(users);
    }

    @DeleteMapping
    public void delete() {
        elasticService.delete();
    }


}
