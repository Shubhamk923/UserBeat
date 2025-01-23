package com.userbeat.entry.entities;



import java.time.LocalDateTime;

import org.apache.commons.lang3.builder.ToStringExclude;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Blog {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotBlank(message = "Title must not be null or blank")
	private String title;
	@NotBlank(message = "Title must not be null or blank")
	private String description;
	
	@CreatedDate
	@Column(nullable = false , updatable = false)
	@JsonIgnore
	private LocalDateTime createdAt;
	
	@LastModifiedDate
	@Column(nullable = true , insertable = false)
	@JsonIgnore
	private LocalDateTime udpateAt;
	
	@ManyToOne()
	@JoinColumn(name = "user_id")
	@JsonIgnore
	@ToStringExclude
	private User user;

}
