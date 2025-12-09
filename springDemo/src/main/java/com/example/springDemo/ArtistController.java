package com.example.springDemo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService service;

    public ArtistController(ArtistService service) {
        this.service = service;
    }

    // GET /artists
    @GetMapping
    public List<Artist> getAllArtists() {
        return service.getAllArtists();
    }

    // GET /artists/{id}
    @GetMapping("/{id}")
    public Artist getArtistById(@PathVariable int id) {
        return service.getArtistById(id);
    }

    // POST /artists
    @PostMapping
    public String addArtist(@RequestBody Artist artist) {
        int rows = service.addArtist(artist);
        return rows + " artist(s) inserted";
    }

    // PUT /artists/{id}
    @PutMapping("/{id}")
    public String updateArtist(@PathVariable int id, @RequestBody Artist artist) {
        int rows = service.updateArtist(id, artist);
        return rows + " artist(s) updated";
    }

    // DELETE /artists/{id}
    @DeleteMapping("/{id}")
    public String deleteArtist(@PathVariable int id) {
        int rows = service.deleteArtist(id);
        return rows + " artist(s) deleted";
    }
}