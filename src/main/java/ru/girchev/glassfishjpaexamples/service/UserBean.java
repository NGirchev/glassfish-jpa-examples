package ru.girchev.glassfishjpaexamples.service;

/**
 * @author Girchev N.A.
 * Date: 11.02.2019
 */
import ru.girchev.glassfishjpaexamples.domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserBean {

    // Будет инициализирован контейнером Glassfish
    // unitName = "TestPU" - это имя persistence-unit
    // EntityManager дает возможность выполнять CRUD запросы в БД
    @PersistenceContext(unitName = "TestPU")
    private EntityManagerFactory emf;

    // Добавляем User-а В базу данных
    public User add(User user){
        EntityManager em = emf.createEntityManager();
        return em.merge(user);
    }

    // Получаем пользователя по id
    public User get(long id){
        EntityManager em = emf.createEntityManager();
        return em.find(User.class, id);
    }

    // обновляем пользователя
    // если User которого мыпытаемся обновить нет,
    // то запишется он как новый
    public void update(User user){
        add(user);
    }

    // удаляем User по id
    public void delete(long id){
        EntityManager em = emf.createEntityManager();
        em.remove(get(id));
    }

    // Получаем все пользователей с БД
    public List<User> getAll(){
        EntityManager em = emf.createEntityManager();
//        TypedQuery<User> namedQuery = em.createNamedQuery("User.getAll", User.class);
//        return namedQuery.getResultList();
        return new ArrayList<User>();
    }

}
