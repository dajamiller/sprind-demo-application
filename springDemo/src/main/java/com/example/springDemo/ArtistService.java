package com.example.springDemo;

import org.springframework.stereotype.Service;
import java.util.List;

@Service //service class, handles business logic
public class ArtistService {

    private final ArtistDao artistDao;

    public ArtistService(ArtistDao artistDao) {
        this.artistDao = artistDao;
    }


    public Artist getFavoriteArtist() {
        List<Artist> all = artistDao.getAll();
        return all.isEmpty() ? null : all.get(0);
    }


    public List<Artist> getAllArtists() {
        return artistDao.getAll();
    }

    public Artist getArtistById(int id) {
        return artistDao.getById(id);
    }

    public int addArtist(Artist artist) {
        return artistDao.insert(artist);
    }

    public int updateArtist(int id, Artist artist) {
        return artistDao.update(id, artist);
    }

    public int deleteArtist(int id) {
        return artistDao.delete(id);
    }
}
    

