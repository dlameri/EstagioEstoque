package com.ideais.stock.domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="ADMINISTRADOR")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Admin {

	private static final Logger LOG = Logger.getLogger(Admin.class.getName());
	
	@Id
	@SequenceGenerator(name="admin_id", sequenceName="admin_id")
	@GeneratedValue(generator="admin_id", strategy=GenerationType.AUTO)
	@Column(name="CD_ADMINISTRADOR")
	private Long id;
	
	@Column(name="NM_NOME", nullable=false, unique=true)
	private String name;
	
	@Column(name="NM_PASSWORD", nullable=false)
	private String password;
	
	@Column(name="NM_EMAIL", nullable=false, unique=true)
	private String email;
	
	private String makeSecurePassword(String passwordToHash){
		String salt = "catalogo";
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) {
        	LOG.warn("Erro ao gerar algoritmo", e);
        }
        return generatedPassword;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = this.makeSecurePassword(password);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
