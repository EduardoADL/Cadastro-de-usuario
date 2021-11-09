package com.example.prova.User;

import com.example.prova.config.ModelMapperConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapperConf modelMapper;

    public List<UserDTO> findAll () {
        List<User> result = userRepository.findAll();
        return result.stream().map(UserDTO::from).collect(Collectors.toList());
    }

    public UserDTO create (UserForm obj) {
        User user = User.from(obj);
        return UserDTO.from(userRepository.save(user));
    }

    public UserDTO findById (Long id) {
        Optional<User> obj = userRepository.findById(id);
        return UserDTO.from(obj.get());
    }

    public UserDTO update (Long id, UserForm user) {
        User user1 = userRepository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
        modelMapper.modelMapper().map(user, user1);
        return UserDTO.from(userRepository.save(user1));
    }

    public void delete (Long idUser) {
        findById(idUser);
        try {
            userRepository.deleteById(idUser);
        } catch (DataAccessException e) {
            throw new DataIntegrityViolationException("Objeto não pode ser deletado!!!");
        }

    }

}