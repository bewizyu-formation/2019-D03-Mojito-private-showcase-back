package fr.formation.artiste;

import fr.formation.artiste.Artiste;
import fr.formation.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


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


    /**
     * Find artist by username
     *
     * @param username
     *
     * @return the Artist
     */
    public Artiste findByUsername(String username);


    /**
     * Get Artists by departement
     *
     * @param codeDept
     * @return
     */
    @Query("SELECT a FROM Artiste a  where a.codeDept=:codeDept")
    public List<Artiste> findByDepartmentChosen(@Param("codeDept") String codeDept);


}
