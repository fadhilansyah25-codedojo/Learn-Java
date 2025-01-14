package com.pembekalan.xsisacademy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pembekalan.xsisacademy.dto.request.UserRequestDto;
import com.pembekalan.xsisacademy.dto.response.UserResponseDto;
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

        List<UserResponseDto> userResponseDtos = userService.getAllUsers();

        ModelAndView view = new ModelAndView("user/index");
        view.addObject("users", userResponseDtos);

        String title = "User Page";
        view.addObject("title", title);

        return view;
    }

    @GetMapping("/form")
    public ModelAndView userForm() {
        ModelAndView view = new ModelAndView("user/form");
        UserResponseDto userResponseDto = new UserResponseDto();

        view.addObject("user", userResponseDto);

        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute UserRequestDto userRequestDto, BindingResult result) {
        if (!result.hasErrors()) {
            try {
                userService.saveUser(userRequestDto);
            } catch (Exception e) {
                System.out.println("error: " + e.getMessage());
            }
        }
        return new ModelAndView("redirect:/user");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editUser(@PathVariable Integer id) {
        ModelAndView view = new ModelAndView("user/form");

        UserResponseDto userResponseDto = userService.getUserById(id);

        view.addObject("user", userResponseDto);

        return view;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);

        return new ModelAndView("redirect:/user");
    }

}
