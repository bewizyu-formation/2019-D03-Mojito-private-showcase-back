package fr.formation.artiste;

import fr.formation.hello.HelloController;
import fr.formation.user.User;
import fr.formation.user.UserException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * The type Artiste service.
 */
@Service
public class ArtisteService {

    private ArtisteRepository artisteRepository;

    private PasswordEncoder passwordEncoder;

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
    public ArtisteService(ArtisteRepository artisteRepository, PasswordEncoder passwordEncoder) {
        this.artisteRepository = artisteRepository;
        this.passwordEncoder = passwordEncoder;

    }

    /**
     * Call service to Add a new artist
     *
     * @param username
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
    public Artiste addNewArtiste(String username, String namedArtist, String image,
                                 double grade, String longDescription,
                                 String shortDescription, String webSite,
                                 String phoneNumber, String password,
                                 String email,
                                 String nomVille, String codeVille, String codeDept) throws ArtisteException {
        try {

            Artiste artiste = new Artiste();
            artiste.setUsername(username);
            artiste.setNamedArtist(namedArtist);
            artiste.setEmail(email);
            artiste.setPassword(passwordEncoder.encode(password));

            artiste.setImage(image);
            artiste.setLongDescription(longDescription);
            artiste.setShortDescription(shortDescription);
            artiste.setPhoneNumber(phoneNumber);
            artiste.setWebSite(webSite);
            artiste.setGrade(grade);
            artiste.setNomVille(nomVille);
            artiste.setCodeVille(codeVille);
            artiste.setCodeDept(codeDept);
            if(isUsernameExist(username)){
                throw new ArtisteException("Identifiant déjà existant");
            }

            if (!checkPassword(password)) {
                throw new ArtisteException("Le format du mot de passe est invalide");
            }
            return artisteRepository.save(artiste);

        } catch (ConstraintViolationException e) {
            throw new ArtisteException("Identifiant déjà existant");
        } catch (ArtisteException e) {
            throw e;
        } catch (Exception e) {
            throw new ArtisteException("Une erreur inconnue est survenue");
        }


    }

    public boolean isUsernameExist(String username) {

       Artiste artiste = artisteRepository.findByUsername(username);
        if (artiste != null && artiste.getUsername().equals(username)){
            return true;
        }
        return false;
    }


    /**
     * Get artist by Id
     *
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
     *
     * @param codeDept
     * @return
     */
    public List<Artiste> getArtisteByDept(String codeDept) {
        return artisteRepository.findByDepartmentChosen(codeDept);


    }

    /***
     * check password
     * @param password
     * @return
     */
    public boolean checkPassword(String password) {
        int min = 8;
        int digit = 0;
        int upCount = 0;
        int loCount = 0;
        if (password.length() >= min) {
            for (int i = 0; i < password.length(); i++) {
                char c = password.charAt(i);
                if (Character.isUpperCase(c)) {
                    upCount++;
                }
                if (Character.isLowerCase(c)) {
                    loCount++;
                }
                if (Character.isDigit(c)) {
                    digit++;
                }
            }
            if (loCount >= 1 && upCount >= 1 && digit >= 1) {
                logger.info("Passwod Correct ");
                return true;
            } else {
                logger.info("Passwod incorrect => LowerCase : " + loCount + ", UpperCase : " + upCount + ", Digit : " + digit);
            }
        } else {
            logger.info("Passwod incorrect => Lenght : " + password.length());
        }

        return false;
    }

}