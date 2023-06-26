package ch.bbw.m183.vulnerapp.datamodel;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "blogs")
public class BlogEntity {

	@Id
	UUID id;

	@Column
	@CreationTimestamp
	LocalDateTime createdAt;

	@Column(columnDefinition = "text")
	@NotBlank(message = "The Title of your Blog is mandatory!")
	@Length(min = 3, max = 75, message = "Your Title has to be at least 3 characters long, and within 75 characters.")
	String title;

	@Column(columnDefinition = "text")
	@NotBlank(message = "The Body of your Blog is mandatory!")
	@Length(min = 3, max = 1000, message = "Your Body has to be at least 3 characters long, and within 1000 characters.")
	String body;
}
