package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.PersonAlreadyExistsException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.UnderAgeException;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import lombok.extern.java.Log;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
@Log
public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort personPersistencePort) {
        this.userPersistencePort = personPersistencePort;
    }

    @Override
    public void saveUser(User user) {
        userPersistencePort.saveUser(user);
    }

    public void saveOwner(User user) {
        isOver18YearsOld(user.getBirthdate());
        Role role = new Role();
        role.setId(Constants.PROVIDER_ROLE_ID);
        user.setRole(role);
        userPersistencePort.saveUser(user);
    }

    public void isOver18YearsOld(Date birthdate){
        LocalDate now = LocalDate.now();
        int age = Period.between(birthdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), now).getYears();
        log.info("la edad es "+ age);
        if(age < 18) {
            throw new UnderAgeException();
        }
    }

}
