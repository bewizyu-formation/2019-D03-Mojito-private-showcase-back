package fr.formation;

import fr.formation.artiste.ArtisteService;
import fr.formation.security.SecurityConstants;
import fr.formation.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * This class configure the dataset at application start
 */
@Component
public class BoostrapData {


    private UserService userService;

    private ArtisteService artisteService;

    private PasswordEncoder passwordEncoder;

    /**
     * Instantiates a new Boostrap data.
     *
     * @param userService     the user service
     * @param passwordEncoder the password encoder
     */
    @Autowired
    public BoostrapData(UserService userService, PasswordEncoder passwordEncoder, ArtisteService artisteService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.artisteService = artisteService;
    }

    /**
     * On start.
     */
    @EventListener(ContextRefreshedEvent.class)
    public void onStart() {

        userService.addNewUser(
                "admin",
                passwordEncoder.encode("admin"),
                "admin@digi.fr",
                SecurityConstants.ROLE_ADMIN
        );
        userService.addNewUser(
                "user",
                passwordEncoder.encode("user"),
                "user@digi.fr",
                SecurityConstants.ROLE_USER
        );


        artisteService.addNewArtiste(
                "Daft Punk",
                "https://static1.squarespace.com/static/5372c95be4b0875c414451cb/t/56942a2669a91a5371ee87a1/1452550710892/?format=750w",
                3,
                "Daft Punk, est un groupe de musique électronique français, originaire de Paris. Composé de Thomas Bangalter et Guy-Manuel de Homem-Christo, le groupe est actif depuis 1993",
                "Daft Punk, est un groupe de musique électronique français, originaire de Paris. Composé de Thomas Bangalter et Guy-Manuel de Homem-Christo, le groupe est actif depuis 1993",
                "https://daftpunk.com/",
                "303-504-650",
                "Fbjdff25",
                "admin@free.fr",
                new ArrayList<String>() {
                    {
                        add("01");
                        add("69");
                        add("75");
                    }  }
               , "Amiens",
                "01"


        );
    }


}
