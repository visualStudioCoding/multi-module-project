package com.kdn.core.domain.wpi.equipment;

import java.io.Serializable;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TmRequestId implements Serializable {

	private int orderNo;
	private String tmNo;
}
