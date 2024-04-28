package com.example.tienda.controller;

import com.example.tienda.model.Address;
import com.example.tienda.model.Role;
import com.example.tienda.model.User;
import com.example.tienda.model.UserRole;
import com.example.tienda.service.AddressService;
import com.example.tienda.service.RoleService;
import com.example.tienda.service.UserRoleService;
import com.example.tienda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/store")
public class StoreController {

    private static final Logger log = LoggerFactory.getLogger(StoreController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("/register")
    public ResponseEntity<EntityModel<User>> registerUser(@Validated @RequestBody User user) {
        User savedUser = userService.save(user);
        EntityModel<User> entityModel = EntityModel.of(savedUser,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUserById(savedUser.getId()))
                        .withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers()).withRel("users"));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @GetMapping("/users")
    public CollectionModel<EntityModel<User>> getAllUsers() {
        List<User> users = userService.findAll();
        log.info("GET /users");
        log.info("Retornando todos los users");
        List<EntityModel<User>> usersResources = users.stream()
                .map(student -> EntityModel.of(student,
                        WebMvcLinkBuilder
                                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUserById(student.getId()))
                                .withSelfRel()))
                .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
        CollectionModel<EntityModel<User>> resources = CollectionModel.of(usersResources,
                linkTo.withRel("users"));

        return resources;
    }

    @GetMapping("/users/{id}")
    public CollectionModel<EntityModel<User>> getUserById(@PathVariable Long id) {
        try {
            Optional<User> user = userService.findById(id);
            List<Address> addresses = addressService.findAllByUserId(user.get().getId());
            Optional<UserRole> userRole = userRoleService.getUserRolesByUserId(addresses.get(0).getUser().getId());
            Optional<Role> role = roleService.findById(userRole.get().getRole().getId());

            addresses.forEach(address -> Hibernate.initialize(address.getUser()));

            List<EntityModel<User>> addressResources = user.stream()
                    .map(address -> EntityModel.of(address,
                            linkTo(methodOn(StoreController.class).getUserAddresses(id)).withRel("address"),
                            linkTo(methodOn(StoreController.class).getRoleById(role.get().getId())).withRel("role"),
                            linkTo(methodOn(StoreController.class).getUserById(id)).withRel("user")))
                    .collect(Collectors.toList());

            return CollectionModel.of(addressResources);
        } catch (Exception e) {
            log.error("Error with id: " + id);
            log.error("Error Message: " + e.getMessage());
            throw new TiendaNotFoundException("User not found with id: " + id);
        }
    }

    @GetMapping("/users/{id}/addresses")
    public CollectionModel<EntityModel<Address>> getUserAddresses(@PathVariable Long id) {

        try {
            Optional<User> user = userService.findById(id);
            List<Address> addresses = addressService.findAllByUserId(user.get().getId());
            Optional<UserRole> userRole = userRoleService.getUserRolesByUserId(addresses.get(0).getUser().getId());
            Optional<Role> role = roleService.findById(userRole.get().getRole().getId());

            addresses.forEach(address -> Hibernate.initialize(address.getUser()));

            List<EntityModel<Address>> addressResources = addresses.stream()
                    .map(address -> EntityModel.of(address,
                            linkTo(methodOn(StoreController.class).getUserAddresses(id)).withRel("address"),
                            linkTo(methodOn(StoreController.class).getRoleById(role.get().getId())).withRel("role"),
                            linkTo(methodOn(StoreController.class).getUserById(id)).withRel("user")))
                    .collect(Collectors.toList());

            return CollectionModel.of(addressResources);
        } catch (Exception e) {
            log.error("Error with id: " + id);
            log.error("Error Message: " + e.getMessage());
            throw new TiendaNotFoundException("User not found with id: " + id);
        }
    }

    @GetMapping("/users/{id}/role")
    public CollectionModel<EntityModel<Role>> getRoleById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        List<Address> addresses = addressService.findAllByUserId(user.get().getId());
        Optional<UserRole> userRole = userRoleService.getUserRolesByUserId(addresses.get(0).getUser().getId());
        Optional<Role> role = roleService.findById(userRole.get().getRole().getId());
        log.info("GET /Users/id/" + user.get().getId() + "/role");
        log.info("Retornando role id: " + role.get().getId());
        log.info("Retornando role isEmpty: " + userRole.isEmpty());

        addresses.forEach(address -> Hibernate.initialize(address.getUser()));

        List<EntityModel<Role>> addressResources = role.stream()
                .map(address -> EntityModel.of(address,
                        linkTo(methodOn(StoreController.class).getUserAddresses(id)).withSelfRel(),
                        linkTo(methodOn(StoreController.class).getRoleById(role.get().getId())).withSelfRel(),
                        linkTo(methodOn(StoreController.class).getUserById(id)).withRel("user")))
                .collect(Collectors.toList());
        return CollectionModel.of(addressResources);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<EntityModel<User>> updateUser(@PathVariable Long id, @Validated @RequestBody User newUser) {
        User updatedUser = userService.update(id, newUser);
        EntityModel<User> entityModel = EntityModel.of(updatedUser,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUserById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers()).withRel("users"));
        return ResponseEntity.ok(entityModel);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginDetails) {
        Optional<User> user = userService.findByUsername(loginDetails.getUsername());
        if (user.isPresent() && loginDetails.getPassword().equals(user.get().getPassword())) {
            return ResponseEntity.ok("User logged in successfully");
        }
        return ResponseEntity.badRequest().body("Invalid username or password");
    }
}
