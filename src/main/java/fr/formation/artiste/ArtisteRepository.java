package fr.formation.artiste;

import fr.formation.artiste.Artiste;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * The interface User repository.
 */
public interface ArtisteRepository extends JpaRepository<Artiste, Long> {

    /**
     * Find artist by name.
     *
     * @param username the artist
     * @return the artist
     */
    public Artiste findByNamedArtist(String username);

    /**
     * Find Artist by  id
     *
     * @param id
     * @return the artist
     */
    public Artiste findById(long id);


}
