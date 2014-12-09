package com.mycompany.control;

import com.mycompany.entity.Company;
import com.mycompany.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by marianne on 04.12.14.
 */
public class CompanyService {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveCompany(Company company) {
        entityManager.persist(company);
    }

    public List<Company> findAllCompanies() {
        TypedQuery<Company> query = entityManager.createQuery(
                "SELECT e FROM Company e WHERE e.id IN (SELECT MIN(e.id) FROM Company e GROUP BY e.name)", Company.class);
        List<Company> companies = (List<Company>) query.getResultList();
        for (int i = 0; i<companies.size(); ++i) {
            companies.get(i).setEmployees(numEmployees(companies.get(i).getName()));
        }
        return companies;
    }

    public Integer numEmployees(String companyName) {
        Query query = entityManager.createQuery(
                "SELECT count(e.name) from Company e where e.name = :companyName");
        query.setParameter("companyName", companyName);
        return ((Long) query.getSingleResult()).intValue();
    }

    public Company findCompanyById(Long id) {
        return entityManager.find(Company.class, id);
    }

    public void updateCompany(Company company) {
        entityManager.merge(company);
    }

    public List<Company> findCompanies(String searchString) {

        String[] searchTerms = new String[]{searchString};
        if (searchString.contains(", ")) {
            searchTerms = searchString.split(", ");
        } else if (searchString.contains("; ")) {
            searchTerms = searchString.split("; ");
        } else if (searchString.contains(",")) {
            searchTerms = searchString.split(",");
        } else if (searchString.contains(";")) {
            searchTerms = searchString.split(";");
        } else if (searchString.contains(" ")) {
            searchTerms = searchString.split(" ");
        }

        Map<String, String> paramMapping = new HashMap<String, String>();


        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT e FROM Company e where e.name LIKE ");

        for(int i=0; i<searchTerms.length; i++) {
            String paramName = "searchParam"+i;
            String paramValue = searchTerms[i];
            paramMapping.put(paramName, paramValue);

            queryBuilder.append(":").append(paramName);
            if (i != searchTerms.length-1)
                queryBuilder.append(" OR" );
        }

        TypedQuery<Company> query = entityManager.createQuery(queryBuilder.toString(),
                Company.class);


        for(String paramName : paramMapping.keySet()) {
            String paramValue = paramMapping.get(paramName);
            query.setParameter(paramName, "%"+paramValue+"%");
        }

        return query.getResultList();
    }

    public void deleteCompany(Long id) {
        Company company = findCompanyById(id);
        if (company != null) {
            entityManager.remove(company);
        }
    }

}
