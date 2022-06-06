package com.askme.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.askme.dto.UserDto;

import com.askme.model.User;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
	
	@Mapping(target = "userId", source = "userId")
	public abstract UserDto mapToDto(User user);

}
