package idv.victor.service;

import idv.victor.entity.Dummy;
import idv.victor.web.auth.domain.dto.entity.User;
import idv.victor.repository.DummyRepository;
import idv.victor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DummyService {

    @Autowired
    private DummyRepository dummyRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Dummy> getDummyByUserid (Long user_id){
        Dummy dummy = new Dummy();
        Optional<User> userOptional = userRepository.findById(user_id);
        dummy.setUser(userOptional.get());
        return dummyRepository.findAll(Example.of(dummy));
    }
}
