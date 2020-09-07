package com.gc.api.dondevoy.model.common;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class EffectiveModel extends AbstractModel {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	private Date effectiveDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date expirationDate;

	public boolean isEffective(Date date) {
		return date != null && getEffectiveDate() != null && date.after(getEffectiveDate())
				&& (getExpirationDate() == null || date.before(getExpirationDate()));
	}
}
