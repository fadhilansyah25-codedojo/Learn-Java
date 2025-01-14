package com.pembekalan.xsisacademy.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pembekalan.xsisacademy.dto.response.UserResponseDto;
import com.pembekalan.xsisacademy.entity.User;
import com.pembekalan.xsisacademy.service.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("")
    public ModelAndView getAllUsers() {
        ModelMapper modelMapper = new ModelMapper();
        ModelAndView view = new ModelAndView("user/index");

        List<User> users = userService.getAllUsers();

        // List<UserRequestDto> userRequestDtos = new ArrayList<>();

        // for (User user : users) {
        // UserRequestDto userRequestDto = new UserRequestDto();

        // userRequestDto.setId(user.getId());
        // userRequestDto.setName(user.getName());
        // userRequestDto.setPhoneNumber(user.getPhoneNumber());
        // userRequestDto.setAddress(user.getAddress());

        // userRequestDtos.add(userRequestDto);
        // }

        List<UserResponseDto> userResponseDtos = users.stream()
                .map(user -> modelMapper.map(user, UserResponseDto.class)).collect(Collectors.toList());

        String title = "User Page";
        view.addObject("title", title);

        view.addObject("users", userResponseDtos);

        return view;
    }

    @GetMapping("/form")
    public ModelAndView userForm() {
        ModelAndView view = new ModelAndView("user/form");
        User user = new User();

        view.addObject("user", user);

        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute User user, BindingResult result) {
        if (!result.hasErrors()) {
            try {
                userService.saveUser(user);
            } catch (Exception e) {
                System.out.println("error: " + e.getMessage());
            }
        }
        return new ModelAndView("redirect:/user");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editUser(@PathVariable Integer id) {
        ModelAndView view = new ModelAndView("user/form");
        User user = userService.getUserById(id);
        view.addObject("user", user);

        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return new ModelAndView("redirect:/user");
    }

}
