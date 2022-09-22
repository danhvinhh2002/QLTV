package com.example.nodoapp.restcontroller;

import com.example.nodoapp.model.BanDoc;
import com.example.nodoapp.service.BanDocService;
import com.example.nodoapp.service.impl.BanDocImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ban-doc")
@CrossOrigin(origins = "http://localhost:4200")
public class BanDocRestController {
    @Autowired
    private final BanDocService banDocService;
    @Autowired
    public BanDocRestController(BanDocImpl banDocService) {
        this.banDocService = banDocService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<BanDoc>> findAll() {
        List<BanDoc> banDocList = banDocService.getAllBanDoc();
        if (banDocList.isEmpty()) {
            System.out.println("Không có bản ghi nào");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
        return new ResponseEntity<>(banDocList, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<BanDoc> create(@RequestBody BanDoc banDoc) {
        return new ResponseEntity<>(banDocService.create(banDoc), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<BanDoc> update(@RequestBody BanDoc banDoc) {
        return new ResponseEntity<>(banDocService.update(banDoc), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BanDoc> delete(@PathVariable Integer id) {
        banDocService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<BanDoc> findById(@PathVariable Integer id) {
        BanDoc banDoc = banDocService.getById(id);
        if (banDoc == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(banDoc, HttpStatus.OK);
    }

}
