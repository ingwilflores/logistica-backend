package com.logistica.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.logistica.config.JwtTokenUtil;
import com.logistica.entity.Role;
import com.logistica.entity.User;
import com.logistica.model.CustomResponse;
import com.logistica.model.ERole;
import com.logistica.model.JwtRequest;
import com.logistica.model.JwtResponse;
import com.logistica.model.UserRequest;
import com.logistica.repository.RoleRepository;
import com.logistica.repository.UserRepository;
import com.logistica.service.impl.UserDetailsImpl;
import java.util.HashSet;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class JwtAuthenticationController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Autowired
	UserDetailsService jwtInMemoryUserDetailsService;

        @Autowired
        UserRepository userRepository;
    
        @Autowired
        RoleRepository roleRepository;
        
        @Autowired
        PasswordEncoder passwordEncoder;
        
        public static final String MSG_ROL = "Rol no encontrado";
        
	//@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
//	public ResponseEntity<?> generateAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
//			throws Exception {
//
//		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
//
//		final UserDetails userDetails = jwtInMemoryUserDetailsService
//				.loadUserByUsername(authenticationRequest.getUsername());
//
//		final String token = jwtTokenUtil.generateToken(userDetails);
//
//		return ResponseEntity.ok(new JwtResponse(token));
//	}
//
//	private void authenticate(String username, String password) throws Exception {
//		Objects.requireNonNull(username);
//		Objects.requireNonNull(password);
//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//		} catch (DisabledException e) {
//			throw new Exception("USER_DISABLED", e);
//		} catch (BadCredentialsException e) {
//			throw new Exception("INVALID_CREDENTIALS", e);
//		}
//	}
        
        
        //@PostMapping("/login")
        //@PostMapping("/authenticate")
        @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
        public ResponseEntity<JwtResponse> authenticateUser(@RequestBody JwtRequest loginRequest) {
//        public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody JwtRequest loginRequest) {
             System.out.println("-------"+loginRequest.getUsername());
             System.out.println("-------"+loginRequest.getPassword());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            System.out.println("----2---"+authentication.getName());
            System.out.println("----2---"+loginRequest.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenUtil.generateJwtToken(authentication);
            System.out.println("--jwt-----"+jwt);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new JwtResponse(jwt));
            /*return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles));*/
        }
        
        @PostMapping("/registroUsuario")
    public ResponseEntity<CustomResponse> registerUser(@Valid @RequestBody UserRequest userRequest) {
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new CustomResponse("Error: nombre de usuario ya utilizado!"));
        }
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new CustomResponse("Error: este Email ya fue utilizado!"));
        }
        // Create new user's account
        User user = new User(userRequest.getUsername(),
                userRequest.getEmail(),
                passwordEncoder.encode(userRequest.getPassword()));
        Set<String> userRoles = userRequest.getRoles();
        Set<Role> roles = new HashSet<>();
        if (userRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
            roles.add(userRole);
        } else {
            userRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException(MSG_ROL));
                        roles.add(adminRole);
                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException(MSG_ROL));
                        roles.add(modRole);
                        break;
                    case "cliente":
                        Role cliente = roleRepository.findByName(ERole.ROLE_CLIENTE)
                                .orElseThrow(() -> new RuntimeException(MSG_ROL));
                        roles.add(cliente);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException(MSG_ROL));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new CustomResponse("usuario creado exitosamente!"));
    }

}
