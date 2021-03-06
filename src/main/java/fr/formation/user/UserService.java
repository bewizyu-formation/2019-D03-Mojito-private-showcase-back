package fr.formation.user;

import org.hibernate.exception.ConstraintViolationException;
import fr.formation.hello.HelloController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;

/**
 * The type User service.
 */
@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    private UserRoleRepository userRoleRepository;


    private PasswordEncoder passwordEncoder;


    /**
     * The Logger.
     */
    Logger logger = LoggerFactory.getLogger(HelloController.class);

    /**
     * Instantiates a new User service.
     *
     * @param userRepository     the user repository
     * @param userRoleRepository the user role repository
     */
    @Autowired
    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;


    }

    /**
     * transform a list of roles (as {@link String}) into a list of {@link GrantedAuthority}
     *
     * @param userRoles
     * @return
     */
    private static Collection<? extends GrantedAuthority> transformToAuthorities(List<String> userRoles) {
        String roles = StringUtils.collectionToCommaDelimitedString(userRoles);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            List<String> roles = userRoleRepository.findRoleByUserName(username);
            return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
                    transformToAuthorities(roles));
        } else {
            throw new UsernameNotFoundException("No user exists with username: " + username);
        }

    }

    /**
     * Add a new user with the user repository
     *
     * @param username the username
     * @param password the password
     * @param roles    the roles
     */
    public void addNewUser(String username, String password, String email, String... roles) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user = userRepository.save(user);

        for (String role : roles) {

            UserRole userRole = new UserRole();
            userRole.setRole(role);
            userRole.setUserId(user.getId());

            userRoleRepository.save(userRole);
        }

    }

    /**
     * create a new user
     *
     * @param username
     * @param password
     * @param email
     * @param nomVille
     * @param codeVille
     * @param codeDept
     */

    public User createNewUser(String username, String password, String email, String nomVille,
                              String codeVille, String codeDept) throws UserException{

        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setEmail(email);
            user.setNomVille(nomVille);
            user.setCodeVille(codeVille);
            user.setCodeDept(codeDept);
            if(isUsernameExist(username)){
                throw new UserException("Identifiant déjà existant");
            }
            if (!checkPassword(password)) {
                throw new UserException("Le format du mot de passe est invalide");
            }
            return userRepository.save(user);
        }
        catch (ConstraintViolationException e) {
            throw new UserException("Identifiant déjà existant");
        }catch (UserException e) {
            throw e;
        }catch (Exception e) {
            throw new UserException("Une erreur inconnue est survenue");
        }
    }

    public boolean isUsernameExist(String username) {

        User user = userRepository.findByUsername(username);
        if ((user != null) && (user.getUsername().equals(username)) ){
            return true;
        }
        return false;
    }

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
                logger.info("Passwod Correct " );
                return true;
            }
            else {
                logger.info("Passwod incorrect => LowerCase : " + loCount + ", UpperCase : "+ upCount +", Digit : " +digit );
            }
        }
        else {
            logger.info("Passwod incorrect => Lenght : "  + password.length() );
        }

        return false;
    }
}
