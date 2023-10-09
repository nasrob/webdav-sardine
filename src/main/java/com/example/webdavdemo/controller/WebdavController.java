package com.example.webdavdemo.controller;

import com.example.webdavdemo.service.SardineService;
import com.github.sardine.DavResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/")
public class WebdavController {

    @Autowired
    private SardineService sardineService;

    @GetMapping("")
    public ResponseEntity<List<DavResource>> index(@RequestParam String path) throws IOException {
        return ResponseEntity.ok(sardineService.getAllResources(path));
    }

    @GetMapping("/directories")
    public ResponseEntity<List<DavResource>> getDirectories(@RequestParam String path) throws IOException {
        return ResponseEntity.ok(sardineService.getDirectories(path));
    }

    @PostMapping("")
    public ResponseEntity<?> createDirectory(@RequestBody String path) throws IOException {
        sardineService.addDirectory(path);
        return ResponseEntity.created(URI.create(path)).build();
    }

    @PutMapping("")
    public ResponseEntity<?> copyFiles(@RequestParam String source, @RequestParam String dest) throws IOException {
        sardineService.copyToDir(source, dest);
        return ResponseEntity.accepted().build();
    }

}
