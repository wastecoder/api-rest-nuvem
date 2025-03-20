package dio.api.services;

import dio.api.models.User;

public interface UserService {
    User findById(Long id);

    User create(User userToCreate);
}
