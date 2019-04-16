package fr.formation.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * The type User.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "username",unique = true )
    private String username;


    @Column(name = "password", nullable = false)
    @NotNull
    @JsonIgnore
    @Length(min = 8)
    private String password;

    @Email(message = "Email should be valid")
    @Column(name = "email", unique = true, nullable = false)
    private String email;


    @Column(name = "nomVille")
    private String nomVille;

    @Column(name = "codeVille")
    private String codeVille;

    @Column(name = "codeDept")
    private String codeDept;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

	/**
	 * Sets username.
	 *
	 * @param username the username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
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
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

	/**
	 * Sets password.
	 *
	 * @param password the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}


    public String getEmail() {
        return email;
    }

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

    public String getCodeDept() {
        return codeDept;
    }

    public void setCodeDept(String codeDept) {
        this.codeDept = codeDept;
    }
}
