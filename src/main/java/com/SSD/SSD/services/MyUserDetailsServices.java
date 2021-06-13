package com.SSD.SSD.services;

import com.SSD.SSD.model.MyUserDetails;

import com.SSD.SSD.model.Student;
import com.SSD.SSD.model.Users;
import com.SSD.SSD.repos.UserRepository;
import com.SSD.SSD.repos.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsServices implements UserDetailsService {

    @Autowired
    UsersRepository userRepository;

    @Autowired
    StudentServices studentServices;
    @Autowired
    ProfileService profileService;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Users> user  = userRepository.findUsersByEmail(s);

        //We are creating student
        if(profileService.getUserByEmail(s).getEmail().endsWith("1")){
            System.out.println("---------------------------------------------------------");
            System.out.println(profileService.getUserByEmail(s).getEmail() );
            throw new UsernameNotFoundException(s);

        }else{ System.out.println("----------------------WE ARE CREATING-----------------------------------");
            System.out.println(profileService.getUserByEmail(s).getEmail() );}

        return user.map(MyUserDetails::new).get();



    }






}
