package fr.formation.artiste;

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
     * @param namedArtist
     * @param image
     * @param grade
     * @param longDescription
     * @param shortDescription
     * @param webSite
     * @param phoneNumber
     * @param password
     * @param email
     * @param departmentChosen
     * @param nomVille
     * @param codeVille
     */
    public void addNewArtiste(String namedArtist, String image,
                              double grade, String longDescription,
                              String shortDescription, String webSite,
                              String phoneNumber, String password,
                              String email, List<String> departmentChosen,
                              String nomVille, String codeVille) {


        Artiste artiste = new Artiste();
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
        artiste.setDepartmentChosen(departmentChosen);

        artiste = artisteRepository.save(artiste);


    }
}