package com.sr.L.DShop.service.concrete;

import com.sr.L.DShop.entities.LdUser;
import com.sr.L.DShop.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LdUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    //handle username not found

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<LdUser> ldUser = userRepo.findByUsername(username);
        if(ldUser.isEmpty()){
            throw new UsernameNotFoundException("No such username");
        }
        return ldUser.get();
    }
}
