package com.linkedinlearning.jpa.repository;

import com.linkedinlearning.jpa.entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository {
    Optional<Company> save(Company company);
    Optional<Company> getCompanyById(Long id);
    Optional<Company> getCompanyByName(String CompanyName);
    void deleteCompany(Company company);
}
