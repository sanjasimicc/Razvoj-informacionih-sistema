package pozoriste.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import model.PozoristeRole;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;
	private String username;
	 private String password;
	 private Set<PozoristeRole> roles;
	
	 public UserDetailsImpl() {

	 }
	 
	 
	 @Override
	 public Collection<SimpleGrantedAuthority> getAuthorities() {
	    Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();      
	    for(PozoristeRole r:roles) {    
	    	authorities.add(new SimpleGrantedAuthority("ROLE_"+r.getName()));
	    }    
	    return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<PozoristeRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<PozoristeRole> roles) {
		this.roles = roles;
	}

}
