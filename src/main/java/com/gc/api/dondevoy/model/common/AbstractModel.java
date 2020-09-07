package com.gc.api.dondevoy.model.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class AbstractModel implements Serializable {

	private static final long serialVersionUID = -7421044974708720382L;

	@Id
	@SequenceGenerator(name = "SEQ", sequenceName = "SEQ", allocationSize = 50)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	@Version
	private long version = 1L;

	@PreUpdate
	private void preUpdate() {
		updatedDate = new Date();
	}

	@PrePersist
	private void prePersist() {
		if (createdDate == null) {
			createdDate = new Date();
			updatedDate = new Date();
		}
	}

}
