package com.example.accountservice.specification;

import com.example.accountservice.dto.AccountFilterDto;
import com.example.accountservice.entity.Account;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class AccountSpecificationBuilder {
    public Specification<Account> build(AccountFilterDto accountFilterDto) {
        return (root, criteriaQuery, criteriaBuilder) ->{
            List<Predicate> predicates = new ArrayList<Predicate>();
            if(accountFilterDto==null){
                return criteriaBuilder.conjunction();
            }
            if(accountFilterDto.getUserId()!=null){
                predicates.add(criteriaBuilder.equal(root.get("userId"), accountFilterDto.getUserId()));
            }
            if(accountFilterDto.getStatus()!=null){
                predicates.add(criteriaBuilder.equal(root.get("status"), accountFilterDto.getStatus()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
