package com.gc.api.dondevoy.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gc.api.dondevoy.model.common.AbstractModel;
import com.gc.api.dondevoy.util.Constants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = Constants.TABLE_PREFIX_GC + "USER")
public class User extends AbstractModel implements UserDetails {

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

	/*
	 * Spring Security Variable - password
	 */
	@JsonIgnore
	@Column(length = 100, nullable = false)
	private String password;
	
	@Size(max = 255)
	@Column(length = 255, nullable = true)
	private String email;

	/*
	 * Spring Security Variable - enabled
	 */
	@Default
	private boolean enabled = true;

	/*
	 * Spring Security Variable - account non expired
	 */
	@Default
	private boolean accountExpired = false;

	/*
	 * Spring Security Variable - account non locked
	 */
	@Default
	private boolean accountLocked = false;

	/*
	 * Spring Security Variable - credentials non expired
	 */
	@Default
	private boolean credentialsExpired = false;
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}