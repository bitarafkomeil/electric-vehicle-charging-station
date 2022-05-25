package com.dzm.assignment.service;

import com.dzm.assignment.data.model.Company;
import com.dzm.assignment.exception.NotFoundException;
import com.dzm.assignment.mapper.BaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
public abstract class BaseService<E, R extends org.springframework.data.jpa.repository.JpaRepository<E, Long>, CD, UD, D, M extends BaseMapper<CD, UD, D, E>> {

    protected final R repository;

    protected final M mapper;

    @Transactional
    public D create(CD createDto) {
        E entity = mapper.fromCreateDTO(createDto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Transactional
    public D update(UD updateDto) {
        E entity = mapper.fromUpdateDTO(updateDto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Transactional(readOnly = true)
    public D findOne(Long id) {
        D result = repository.findById(id)
                .map(mapper::toDto).orElseThrow(() -> new NotFoundException("Entity Id InCorrect"));
        return result;
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<D> findAll(Pageable pageable) {
        Page<D> res = repository.findAll(pageable).map(mapper::toDto);
        return res;
    }

    public List<Company> loadAllCompanyChild(Company ancestor, List<Company> allAncestors) {
        allAncestors.add(ancestor);
        if (!ancestor.getChildren().isEmpty()) {
            ancestor.getChildren().stream().forEach(company -> loadAllCompanyChild(company, allAncestors));
        }
        return allAncestors;
    }
}