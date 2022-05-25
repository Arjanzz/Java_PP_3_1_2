package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> findAll() {
        return entityManager.createQuery("FROM Role", Role.class)
                .getResultList();
    }

    @Override
    public List<Role> findByName(List<String> name) {
        TypedQuery<Role> query = entityManager
                .createQuery("select r from Role r WHERE r.name in (:name)", Role.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public void saveRole(Role role) {
        entityManager.persist(role);
    }
}
