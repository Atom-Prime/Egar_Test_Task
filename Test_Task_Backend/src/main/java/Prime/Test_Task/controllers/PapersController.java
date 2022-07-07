package Prime.Test_Task.controllers;

import Prime.Test_Task.models.Papers;
import Prime.Test_Task.repo.PapersRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/papers")
public class PapersController {
    private final PapersRepo papersRepo;

    @Autowired
    public PapersController(PapersRepo papersRepo) {
        this.papersRepo = papersRepo;
    }

    @GetMapping
    public List<Papers> list(){
        return papersRepo.findAll();
    }

    @PostMapping
    public Papers create(@RequestBody Papers paper){
        return papersRepo.save(paper);
    }

    @PutMapping("{id}")
    public Papers update(
            @PathVariable("id") Papers paperFromDb,
            @RequestBody Papers paper){
        BeanUtils.copyProperties(paper, paperFromDb, "id");
        return papersRepo.save(paperFromDb);
    }



}
