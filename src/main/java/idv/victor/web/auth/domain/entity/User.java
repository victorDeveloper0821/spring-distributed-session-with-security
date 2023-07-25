package idv.victor.web.auth.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 登入帳號
     */
    @Column(name = "userid", nullable = false)
    private String userName;

    /**
     * 登入密碼
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * 名字
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * 姓氏
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    /**
     * 身分別
     */
    @Column(name = "memberType", nullable = false)
    private Short memberType;

}
