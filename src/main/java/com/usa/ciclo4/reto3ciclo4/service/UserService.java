package com.usa.ciclo4.reto3ciclo4.service;

import com.usa.ciclo4.reto3ciclo4.model.User;
import com.usa.ciclo4.reto3ciclo4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Sebastián
 */



/**
 * Class User Service
 */
@Service

public class UserService {
    /**
     *
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Con este metodo obtenemos los datos
     * @return
     */
    public List<User> getAll() {
        return userRepository.getAll();
    }

    /**
     * Con este metodo obtenemos los datos de usuario por id
     * @param id
     * @return
     */
    public Optional<User> getUser(int id) {
        return userRepository.getUser(id);
    }

    /**
     * Este metodo guarda la información del nuevo usuario
     * @param user
     * @return
     */
    public User save(User user) {
        if (user.getId() == null) {
            return user;
        } else {
            Optional<User> dbUser = userRepository.getUser(user.getId());
            if (dbUser.isEmpty()) {
                if (emailExists(user.getEmail()) == false) {
                    return userRepository.save(user);
                } else {
                    return user;
                }
            } else {
                return user;
            }
        }
    }

    /**
     * Con este metodo se actualiza la información del usuario
     * @param user
     * @return
     */
    public User update(User user) {
        if (user.getId() != null) {
            Optional<User> dbUser = userRepository.getUser(user.getId());
            if (!dbUser.isEmpty()) {
                if (user.getIdentification() != null) {
                    dbUser.get().setIdentification(user.getIdentification());
                }
                if (user.getName() != null) {
                    dbUser.get().setName(user.getName());
                }

                if (user.getBirthtDay() != null){
                    dbUser.get().setBirthtDay(user.getBirthtDay());
                }

                if (user.getMonthBirthtDay() != null){
                    dbUser.get().setMonthBirthtDay(user.getMonthBirthtDay());
                }
                if (user.getAddress() != null) {
                    dbUser.get().setAddress(user.getAddress());
                }
                if (user.getCellPhone() != null) {
                    dbUser.get().setCellPhone(user.getCellPhone());
                }
                if (user.getEmail() != null) {
                    dbUser.get().setEmail(user.getEmail());
                }
                if (user.getPassword() != null) {
                    dbUser.get().setPassword(user.getPassword());
                }
                if (user.getZone() != null) {
                    dbUser.get().setZone(user.getZone());
                }
                if (user.getType() != null) {
                    dbUser.get().setType(user.getType());
                }
                userRepository.update(dbUser.get());
                return dbUser.get();
            } else {
                return user;
            }
        }
        return user;
    }

    /**
     * Con este metodo se verifica si el email ya existe
     * @param email
     * @return
     */
    public boolean emailExists(String email) {
        return userRepository.emailExists(email);
    }

    /**
     * Con este metodo se eliminan los usuarios
     * @param userId
     * @return
     */
    public boolean delete(int userId) {
        Boolean userBoolean = getUser(userId).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
        return userBoolean;
    }

    /**
     * Con este metodo se verifica si un usuario es registrado por email y contraseña
     * @param email
     * @param password
     * @return
     */
    public User authenticateUser(String email, String password){
        Optional<User> user = userRepository.authenticateUser(email, password);
        if (user.isEmpty()){
            return new User();
        }
        return user.get();
    }
}
