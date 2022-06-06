package com.askme.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.askme.dto.SubqueryDto;
import com.askme.model.Post;
import com.askme.model.Subquery;



@Mapper(componentModel = "spring")
public interface SubqueryMapper {
	
	    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subquery.getPosts()))")
	    SubqueryDto mapSubqueryToDto(Subquery subquery);

	    default Integer mapPosts(List<Post> numberOfPosts) {
	        return numberOfPosts.size();
	    }

	    @InheritInverseConfiguration
	    @Mapping(target = "posts", ignore = true)
	    Subquery mapDtoToSubquery(SubqueryDto subqueryDto);
}
