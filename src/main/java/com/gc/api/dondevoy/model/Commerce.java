package com.gc.api.dondevoy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.gc.api.dondevoy.model.common.EffectiveModel;
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
@Table(name = Constants.TABLE_PREFIX_GC + "COMMERCE")
public class Commerce extends EffectiveModel{

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 1, max = 100)
	@Column(length = 100, nullable = false)
	private String name;

}
