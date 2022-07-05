package idv.victor.entity;

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
