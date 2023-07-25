package idv.victor.web.auth.service;

import idv.victor.web.auth.domain.entity.User;

/**
 * 檢索 user 資料
 */
public interface UserService {
    User findUserByUsername(String username);

    boolean findDuplicateName(String firstName, String lastName);

    boolean findDuplicateUserName(String userName);

    User save(User user);
}
