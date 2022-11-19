package com.cos.shareHere.web.dto.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscribeDto {
	private Integer id;
	private String username;
	private String profileImageUrl;
	private Integer subscribeState;
	private Integer equalUserState;

	public SubscribeDto(Object[] object) {
		this.id = (int) object[0];
		this.username = (String) object[1];
		this.profileImageUrl = (String) object[2];
		this.subscribeState = Integer.parseInt(String.valueOf(object[3]));
		this.equalUserState = Integer.parseInt(String.valueOf(object[4]));
	}
}
