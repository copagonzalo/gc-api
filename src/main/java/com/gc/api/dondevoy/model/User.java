package com.gc.api.dondevoy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.gc.api.dondevoy.model.common.AbstractModel;
import com.gc.api.dondevoy.util.Constants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = Constants.TABLE_PREFIX_GC + "USER")
public class User extends AbstractModel {

	private static final long serialVersionUID = 1706141451776207932L;
	
	/*
	 * First Name
	 */
	@NotNull
	@Size(max = 100)
	@Column(length = 100, nullable=false)
	private String firstName;

	/*
	 * Last Name
	 */
	@NotNull
	@Size(max = 100)
	@Column(length = 100, nullable=false)
	private String lastName;
	
	
	/*
	 * Username (may be email or username - this needs to be flexible for all
	 * clients)
	 */
	@Size(max = 255)
	@Column(length = 255, nullable = false)
	private String username;

	@Size(max = 255)
	@Column(length = 255, nullable = true)
	private String email;
	

	
	/**
	 * Get Full Name
	 * 
	 * @return firstName + " " + lastName
	 */
	public String getFullName() {
		StringBuffer sb = new StringBuffer();
		if (getFirstName() != null) {
			sb.append(getFirstName());
		}
		if (getLastName() != null) {
			sb.append(" ");
			sb.append(getLastName());
		}
		return sb.toString().trim();
	}
}