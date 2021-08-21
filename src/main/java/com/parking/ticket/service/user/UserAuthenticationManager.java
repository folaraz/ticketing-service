package com.parking.ticket.service.user;

import com.parking.ticket.model.constants.Gender;
import com.parking.ticket.model.db.Role;
import com.parking.ticket.model.db.User;
import com.parking.ticket.payload.request.SignInRequest;
import com.parking.ticket.payload.request.SignUpRequest;
import com.parking.ticket.payload.response.JwtResponse;
import com.parking.ticket.payload.response.MessageResponse;
import com.parking.ticket.repository.RoleRepository;
import com.parking.ticket.repository.UserRepository;
import com.parking.ticket.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserAuthenticationManager {

    private static final Logger log = LoggerFactory.getLogger(UserAuthenticationManager.class);

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    public UserAuthenticationManager(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }


    public JwtResponse authenticateUserSignIn(SignInRequest signInRequest) {
        JwtResponse.JwtResponseBuilder builder = new JwtResponse.JwtResponseBuilder();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        builder.withToken(jwt)
                .withId(userDetails.getId())
                .withUsername(userDetails.getUsername())
                .withEmail(userDetails.getEmail())
                .withRoles(roles);
        return builder.build();
    }

    public MessageResponse authenticateUserSignUp(SignUpRequest signUpRequest) {
        String username = signUpRequest.getUsername();
        String password = signUpRequest.getPassword();
        String email = signUpRequest.getEmail();
        Date dateOfBirth = signUpRequest.getDateOfBirth();
        String firstName = signUpRequest.getFirstName();
        String lastName = signUpRequest.getLastName();
        Gender gender = signUpRequest.getGender();

        Set<String> strRoles = signUpRequest.getRole();

        if (userRepository.existsByUsername(username)) {
            log.error("Username '{}' already exists", username);
            return new MessageResponse("Username already exists", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(email)) {
            log.error("User with username {} has an email that already exits", username);
            return new MessageResponse("Email already exists", HttpStatus.BAD_REQUEST);
        }

        User.UserBuilder builder = new User.UserBuilder();
        builder.withUsername(username)
                .withEmail(email)
                .withDateOfBirth(dateOfBirth)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withGender(gender)
                .withPassword(encoder.encode(password));

        Set<Role> roles = new HashSet<>();

        if (ObjectUtils.isEmpty(strRoles)) {
            Role role = roleRepository.findByName(com.parking.ticket.model.constants.Role.USER)
                    .orElseThrow(() -> new RuntimeException("Error : Role not found."));
            roles.add(role);
        } else {
            strRoles.forEach(r -> {
                if ("admin".equals(r)) {
                    Role adminRole = roleRepository.findByName(com.parking.ticket.model.constants.Role.ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error : Role not found"));
                    roles.add(adminRole);
                } else {
                    Role userRole = roleRepository.findByName(com.parking.ticket.model.constants.Role.USER)
                            .orElseThrow(() -> new RuntimeException("Error : Role not found"));
                    roles.add(userRole);
                }
            });
        }
        builder.withRoles(roles);
        userRepository.save(builder.build());
        log.info("User with username {} has been registered successfully", username);
        return new MessageResponse("User Registered Successfully!", HttpStatus.OK);
    }

}
