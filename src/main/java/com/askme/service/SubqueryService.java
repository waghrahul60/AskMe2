package com.askme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.askme.dto.SubqueryDto;
import com.askme.exceptions.askmeException;
import com.askme.mapper.SubqueryMapper;
import com.askme.model.Subquery;
import com.askme.repository.SubqueryRepository;

import static java.util.stream.Collectors.toList;

@Service
public class SubqueryService {
	
	@Autowired
	private SubqueryRepository subqueryRepository;
	
	@Autowired
	private SubqueryMapper subqueryMapper;
	
	@Transactional
    public SubqueryDto save(SubqueryDto subqueryDto) {
        Subquery save = subqueryRepository.save(subqueryMapper.mapDtoToSubquery(subqueryDto));
        subqueryDto.setId(save.getId());
        return subqueryDto;
    }

    @Transactional
    public List<SubqueryDto> getAll() {
        return subqueryRepository.findAll()
                .stream()
                .map(subqueryMapper::mapSubqueryToDto)
                .collect(toList());
    }

    @Transactional
	public SubqueryDto getSubquery(Long id) {
        Subquery subquery = subqueryRepository.findById(id)
                .orElseThrow(() -> new askmeException("No subquery found with ID - " + id));
        return subqueryMapper.mapSubqueryToDto(subquery);
    }	
}
