package idv.victor.repository;

import idv.victor.web.auth.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    long countByUserName(@NonNull String userName);

    @Query("select count(u) from User u where u.firstName = :firstName and u.lastName = :lastName")
    Integer findDuplicateNameCount(@Param("firstName") String firstName, @Param("lastName") String lastName);

    User findByUserName(@NonNull String userName);
}
