package az.doshabcatering.doshabcatering.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("public")
@RequiredArgsConstructor
public class PublicController {

    @GetMapping
    public ResponseEntity<?> login() {
        return new ResponseEntity<>("Hello no Secured!",HttpStatus.OK);
    }

}
