package org.codetime.gestioncoursetudiant.Service;

import org.codetime.gestioncoursetudiant.Entity.User;
import org.codetime.gestioncoursetudiant.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private final UserRepository repository;


    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
        
    }


    public User findUserById(Long id) {
        return repository.findById(id).orElse(new User());
    }

    public List<User> findAllByOrderByUsernameAsc() {
        return repository.findAllByOrderByUsernameAsc();
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public void saveUser(User user) {
        repository.save(user);
    }

    public void updateUser(User user) {
        repository.save(user);
    }

    public void deleteUserById(Long id) {
        repository.deleteById(id);
    }

    public void deleteAllUsers() {
        repository.deleteAll();
    }

    public List<User> findAllUsers() {
        return (List<User>) repository.findAll();
    }


    public User encryptPassword(User user) {
        String pwd = user.getPassword();
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        String hashPwd = bc.encode(pwd);
        user.setPassword(hashPwd);

        return user;
    }

    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User curruser = repository.findByUsername(username);
        if (curruser == null) {
            throw new UsernameNotFoundException("User not found");
        }

        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPassword(), true,
                true, true, true, AuthorityUtils.createAuthorityList(curruser.getRole()));


        System.out.println("ROLE: " + curruser.getRole());
        return user;
    }
}
