package dio.api.services;

import dio.api.models.User;
import dio.api.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User userToCreate) {
//        if (userToCreate.getId() != null && repository.existsById(userToCreate.getId())) {
//            throw new IllegalArgumentException("This User ID already exists.");
//        }
        if (repository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
            throw new IllegalArgumentException("This Account Number already exists.");
        }

        return repository.save(userToCreate);
    }
}
