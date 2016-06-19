package info.jonwarren.tasklogs.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import info.jonwarren.tasklogs.model.User;
import info.jonwarren.tasklogs.repository.UserRepository;

public class UserService {

    public static User getCurrentUser(UserRepository userRepository) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByName(username);
        if (user == null) {
            user = new User(username);
            userRepository.save(user);
        }

        return user;
    }

}
