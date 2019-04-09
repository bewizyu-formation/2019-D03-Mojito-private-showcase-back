package fr.formation.artiste;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.formation.geo.model.Commune;
import fr.formation.geo.model.Departement;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * The type Artiste.
 */
@Entity
@Table(name = "artiste")
public class Artiste {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "namedArtist")
    @NotNull
    private String namedArtist;

    @Column(name = "image")
    private String image;

    @Column(name = "grade")
    private double grade;

    @Column(name = "longDescription")
    @Lob
    private String longDescription;

    @Column(name = "shortDescription",length=100000)
    private String shortDescription;

    @Column(name = "webSite")
    private String webSite;

    @Column(name = "phone")
    private String phoneNumber;

    @Column(name = "password")
    @JsonIgnore
    @NotNull
    @Length(min = 8)
    private String password;

    @Email(message = "Email should be valid")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @ElementCollection
    //@Column(name = "departmentChosen")
    @Column
    private List<String> departmentChosen;

    @Column(name = "nomVille")
    private String nomVille;

    @Column(name = "codeVille")
    private String codeVille;




    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }


    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }


    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return
     */
    public String getNamedArtist() {
        return namedArtist;
    }

    /**
     * Sets name
     *
     * @param namedArtist
     */
    public void setNamedArtist(String namedArtist) {
        this.namedArtist = namedArtist;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Gets Grade
     *
     * @return
     */
    public double getGrade() {
        return grade;
    }

    /**
     * Sets  grade
     *
     * @param grade
     */
    public void setGrade(double grade) {
        this.grade = grade;
    }

    /**
     * Gets Description
     *
     * @return
     */
    public String getLongDescription() {
        return longDescription;
    }

    /**
     * Sets Description
     *
     * @param longDescription
     */
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    /**
     * Get short description
     *
     * @return
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Sets short description
     *
     * @param shortDescription
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * Gets WebSite url
     *
     * @return
     */
    public String getWebSite() {
        return webSite;
    }

    /**
     * Sets Website Url
     *
     * @param webSite
     */
    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    /**
     * Gets Phone Number
     *
     * @return
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets Phone Number
     *
     * @param phoneNumbe
     */
    public void setPhoneNumber(String phoneNumbe) {
        this.phoneNumber = phoneNumbe;
    }

    /**
     * Gets Email
     *
     * @return
     */

    public String getEmail() {
        return email;
    }

    /**
     * Sets Email
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public String getCodeVille() {
        return codeVille;
    }

    public void setCodeVille(String codeVille) {
        this.codeVille = codeVille;
    }

    public List<String> getDepartmentChosen() {
        return departmentChosen;
    }

    public void setDepartmentChosen(List<String> departmentChosen) {
        this.departmentChosen = departmentChosen;
    }


}
