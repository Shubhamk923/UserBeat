package com.blogbeat.entry.entities;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringExclude;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)


public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;											
	@Nonnull
	@NotBlank
	private String name;
	@NotNull
	@Email
	@Size(min = 6 , max = 30)
	private String email;
	@NotNull
//	@Size(min = 6 , max = 30 , message = "you can add password between 6-30 charchters")
//	@Pattern(regexp = "[a-zA-Z0-9]+$",message = "only alpha numeric value allow")
	@NotBlank
	private String password;
	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
	@Column(insertable = false)
	@ToStringExclude
	private List<Blog> blogs;
	
	@CreatedDate
	@Column(nullable = false , updatable = false)
	@JsonIgnore
	private LocalDateTime createdAt;
	
	@LastModifiedDate
	@Column(nullable = true , insertable = false)
	@JsonIgnore
	private LocalDateTime udpateAt;
	
	@ElementCollection
	private List<String> roles;
	
	
	
	
//	public User() {
//		
//	}
//
//	public User(int id, String name, String email, String password) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.email = email;
//		this.password = password;
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	@Override
//	public String toString() {
//		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
//	}
	
	
	

	
	
	
}
