package com.example.springDemo;

import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcArtistDao implements ArtistDao {

    private final DataSource dataSource;

    public JdbcArtistDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Artist> getAll() {
        List<Artist> artists = new ArrayList<>();
        String sql = "SELECT id, name, album_title, song_title FROM Artists";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Artist artist = new Artist(
                        rs.getString("name"),
                        rs.getString("album_title"),
                        rs.getString("song_title")
                );
                // if you later add an id field to Artist, set it here
                artists.add(artist);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return artists;
    }

    @Override
    public Artist getById(int id) {
        // sql query
        String sql = "SELECT id, name, album_title, song_title FROM Artists WHERE id = ?";
        // try catch block to open connection
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            // set parameter
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Artist artist = new Artist(
                            rs.getString("name"),
                            rs.getString("album_title"),
                            rs.getString("song_title")
                    );
                    // if you later add an id field to Artist, set it here
                    return artist;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public int insert(Artist artist) {

        // sql query
        String sql = "INSERT INTO Artists (name, album_title, song_title) VALUES (?, ?, ?)";
        // try catch block to open connection
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            // set parameters
            ps.setString(1, artist.getName());
            ps.setString(2, artist.getAlbumTitle());
            ps.setString(3, artist.getSongTitle());
// execute + return
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(int id, Artist artist) {

        // sql query
        String sql = "UPDATE Artists SET name = ?, album_title = ?, song_title = ? WHERE id = ?";
        // try catch block to open connection
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            // set parameters
            ps.setString(1, artist.getName());
            ps.setString(2, artist.getAlbumTitle());
            ps.setString(3, artist.getSongTitle());
            ps.setInt(4, id);
// execute + return
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(int id) {
        // sql query
        String sql = "DELETE FROM Artists WHERE id = ?";
        // try catch block to open connection
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            // set parameters
            ps.setInt(1, id);
// execute + return
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO: implement getById, insert, update, delete - done
}
