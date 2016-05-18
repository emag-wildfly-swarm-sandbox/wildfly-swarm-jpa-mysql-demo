package wildflyswarm.jpa_mysql;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * @author Ken Finnigan
 */
@Path("/")
@ApplicationScoped
public class EmployeeResource {

  @PersistenceContext
  EntityManager em;

  @GET
  @Produces("application/json")
  public List<Employee> get() {
    return em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
  }

}
