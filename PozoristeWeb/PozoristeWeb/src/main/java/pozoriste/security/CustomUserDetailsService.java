package pozoriste.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pozoriste.repository.PozoristeUserRepository;

import model.PozoristeUser;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
 
    
    @Autowired
    private PozoristeUserRepository korisnikRepository;  
    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		PozoristeUser user = korisnikRepository.findByUsername(username);
		UserDetailsImpl userDetails =new UserDetailsImpl();
		userDetails.setUsername(user.getUsername());
		userDetails.setPassword(user.getPassword());
		userDetails.setRoles(user.getRoles());
		return userDetails;
		
    }
 
     
 


	
     
}
