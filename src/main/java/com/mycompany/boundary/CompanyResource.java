package com.mycompany.boundary;

import javax.ejb.Stateless;
import javax.ws.rs.Path;

import com.mycompany.control.CompanyService;
import com.mycompany.entity.Company;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by marianne on 05.12.14.
 */

@Path("/company")
@Stateless
public class CompanyResource {

    @Inject
    private CompanyService companyService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCompanies(@QueryParam("searchString") String searchString,
                                  @QueryParam("name") String name) {
        List<Company> companies;
        if (searchString != null) {
            companies = companyService.findCompanies(searchString);
        } else {
            companies = companyService.findAllCompanies();
        }
        return Response.ok(companies).build();
    }
/*
    @POST
    public Response saveCompany(@Context UriInfo uriInfo, Company company)
            throws URISyntaxException {
        companyService.saveCompany(company);
        return Response.created(
                new URI(uriInfo.getRequestUri() + "/" + company.getId())).build();
    }

    @GET
    @Path("/{companyId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCompanyById(@PathParam(value = "companyId") String companyId) {
        Company company = companyService.findCompanyById(Long
                .parseLong(companyId));

        if (company != null) {
            return Response.ok().entity(company).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{companyId}")
    public Response updateCompany(Company company) {
        companyService.updateCompany(company);
        return Response.status(Status.ACCEPTED).build();
    }

    @DELETE
    @Path("/{companyId}")
    public Response deleteCompany(@PathParam("companyId") Long companyId) {
        companyService.deleteCompany(companyId);
        return Response.ok().build();
    }
*/
}
