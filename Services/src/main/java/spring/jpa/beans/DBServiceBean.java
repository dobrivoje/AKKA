package spring.jpa.beans;

import ent.Click;
import ent.Users;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import spring.jpa.myAnnotations.EclipseLink_JPA;
import spring.jpa.services.IDBService;

@Component
@EclipseLink_JPA
public class DBServiceBean implements IDBService {

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

    @Override
    public List<Click> getAllUsersClicks(String userName) {
        return EM.createNamedQuery("Click.allUserClicks")
                .setParameter("userName", userName)
                .getResultList();
    }

    @Override
    public List<Click> getStatistics(long userID, Date from, Date to) {
        return EM.createNamedQuery("Click.allUserClicksOverPeriod")
                .setParameter("from", from)
                .setParameter("to", to)
                .setParameter("userID", userID)
                .getResultList();
    }

    @Override
    public List<Click> getStatistics(long userId, String from, String to) throws ParseException {

        Date f = new SimpleDateFormat("yyyy-MM-dd").parse(from);
        Date t = new SimpleDateFormat("yyyy-MM-dd").parse(to);

        return getStatistics(userId, f, t);
    }

    @Override
    @Transactional
    public void insertNewClick(long userID, Date date, String webAddress) {
        EM.persist(new Click(getUser(userID), date, webAddress));
    }
}
