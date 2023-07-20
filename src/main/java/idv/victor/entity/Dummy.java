package idv.victor.entity;

import idv.victor.web.auth.domain.entity.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "dummy")
@Data
public class Dummy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String item;

    private boolean status;
}
