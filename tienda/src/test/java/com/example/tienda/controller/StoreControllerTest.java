package com.example.tienda.controller;

import com.example.tienda.model.User;
import com.example.tienda.service.AddressService;
import com.example.tienda.service.RoleService;
import com.example.tienda.service.UserRoleService;
import com.example.tienda.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class StoreControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private AddressService addressService;

    @Mock
    private RoleService roleService;

    @Mock
    private UserRoleService userRoleService;

    @InjectMocks
    private StoreController storeController;

    private MockMvc mockMvc;

    @SuppressWarnings("deprecation")
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(storeController).build();
    }

    @Test
    public void testRegisterUser() throws Exception {
        User user = new User();  // Create a user instance as needed
        user.setId(1L);

        when(userService.save(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/store/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(user.getId().intValue()));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> users = new ArrayList<>();  // Create users list as needed

        when(userService.findAll()).thenReturn(users);

        mockMvc.perform(get("/store/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray());
    }

    // Add tests for other controller methods here...

}
