package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.MailAlreadyExistsException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.PersonAlreadyExistsException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepository;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserMysqlAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper personEntityMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(User user) {
        checkIfUserExists(user.getDniNumber(), user.getMail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(personEntityMapper.toEntity(user));
    }

    private void checkIfUserExists(String dniNumber, String mail) {
        if (userRepository.findByDniNumber(dniNumber).isPresent()) {
            throw new PersonAlreadyExistsException();
        }
        if (userRepository.existsByMail(mail)) {
            throw new MailAlreadyExistsException();
        }
    }
}
