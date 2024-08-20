package fr.aelion.atos24.cyber.repositories;

import fr.aelion.atos24.cyber.models.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    // JPA Query Methods
    // https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
    public User findByEmailAndPwd(String email, String pwd);

    // JPQL (JPA query language) => object/sql syntax
    // https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html#jpa.query-methods.at-query
    @Query("select u from User u where u.email = ?1")
    User findByEmailAddressWithJPAQuery(String emailAddress);

    // Native SQL
    // https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html#jpa.query-methods.at-query.native
    @Query(value = "select * from users as u where u.email = ?1", nativeQuery = true)
    User findByEmailAddressWithJPANativeQuery(String emailAddress);
}
