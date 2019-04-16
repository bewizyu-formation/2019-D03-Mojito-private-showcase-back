package fr.formation.artiste;

import fr.formation.hello.HelloController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * The type Artiste service.
 */
@Service
public class ArtisteService {

    private ArtisteRepository artisteRepository;

    /**
     * The Logger.
     */
    Logger logger = LoggerFactory.getLogger(HelloController.class);


    /**
     * Instantiates a new Artist service.
     *
     * @param artisteRepository the artiste repository
     */
    @Autowired
    public ArtisteService(ArtisteRepository artisteRepository) {
        this.artisteRepository = artisteRepository;

    }

    /**
     * Call service to Add a new artist
     *
     * @param  username
     * @param namedArtist
     * @param image
     * @param grade
     * @param longDescription
     * @param shortDescription
     * @param webSite
     * @param phoneNumber
     * @param password
     * @param email

     * @param nomVille
     * @param codeVille
     */
    public void addNewArtiste(String username, String namedArtist, String image,
                              double grade, String longDescription,
                              String shortDescription, String webSite,
                              String phoneNumber, String password,
                              String email,
                              String nomVille, String codeVille, String codeDept) {


        Artiste artiste = new Artiste();
        artiste.setUsername(username);
        artiste.setNamedArtist(namedArtist);
        artiste.setEmail(email);
        artiste.setPassword(password);

        artiste.setImage(image);
        artiste.setLongDescription(longDescription);
        artiste.setShortDescription(shortDescription);
        artiste.setPhoneNumber(phoneNumber);
        artiste.setWebSite(webSite);
        artiste.setGrade(grade);
        artiste.setNomVille(nomVille);
        artiste.setCodeVille(codeVille);
        artiste.setCodeDept(codeDept);


        artiste = artisteRepository.save(artiste);


    }

    /**
     * Get artist by Id
     * @param id
     * @return
     */
    public Artiste getArtiste(long id) {
        return artisteRepository.findById(id);


    }

    /**
     * Get artist by name
     *
     * @param nameArtist
     * @return
     */
    public Artiste getArtisteByName(String nameArtist) {
        return artisteRepository.findByNamedArtist(nameArtist);


    }

    /**
     * Get a list of artist by Dept
     * @param codeDept
     * @return
     */
    public List<Artiste> getArtisteByDept(String codeDept) {
        return artisteRepository.findByDepartmentChosen(codeDept);


    }

}