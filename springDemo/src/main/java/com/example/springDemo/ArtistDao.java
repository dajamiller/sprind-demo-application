package com.example.springDemo;

import java.util.List;

public interface ArtistDao {

    List<Artist> getAll();
    Artist getById(int id);
    int insert(Artist artist);
    int update(int id, Artist artist);
    int delete(int id);


}
