package spring.jpa.beans;

import ent.Users;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import spring.jpa.services.IUserService;
import spring.jpa.myAnnotations.EclipseLink_JPA;

@Component
@EclipseLink_JPA
public class UserServiceBean implements IUserService {

    //<editor-fold defaultstate="collapsed" desc="infra">
    @PersistenceContext
    private EntityManager EM;

    public EntityManager getEM() {
        return EM;
    }

    public void setEM(EntityManager EM) {
        this.EM = EM;
    }
    //</editor-fold>

    @Override
    public List<Users> getAllUsers() {
        return EM.createNamedQuery("Users.findAll")
                .getResultList();
    }

    @Override
    public Users getUser(long ID) {
        Users u = (Users) EM.createNamedQuery("Users.findByIdu")
                .setParameter("idu", ID)
                .getSingleResult();

        // u.setClickList(withClicks ? clickService.getAllUsersClicks(u.getUsername()) : null);

        return u;
    }

    @Transactional
    @Override
    public void insertNewUser(String name, String surname, String username) {
        EM.persist(new Users(name, surname, username));
    }

}
