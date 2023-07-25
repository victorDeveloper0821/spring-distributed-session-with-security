package idv.victor.web.auth.service.impl;

import idv.victor.repository.UserRepository;
import idv.victor.web.auth.domain.entity.User;
import idv.victor.web.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 檢索 user 資訊
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * repository
     */
    @Autowired
    private UserRepository repository;

    /**
     * 以 userName 找 User
     *
     * @param username
     * @return
     */
    @Override
    public User findUserByUsername(String username) {
        return repository.findByUserName(username);
    }

    /**
     * 搜尋 user 是否有重複姓名
     *
     * @param firstName
     * @param lastName
     * @return
     */
    @Override
    public boolean findDuplicateName(String firstName, String lastName) {
        return repository.findDuplicateNameCount(firstName, lastName) > 1;
    }

    /**
     * Username 是否重複
     *
     * @param userName
     * @return
     */
    @Override
    public boolean findDuplicateUserName(String userName) {
        return repository.countByUserName(userName) > 1;
    }

    /**
     * 儲存 or 更新 user
     *
     * @param user
     * @return
     */
    @Override
    public User save(User user) {
        return repository.save(user);
    }
}
