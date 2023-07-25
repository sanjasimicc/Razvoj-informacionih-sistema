package pozoriste.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import model.PozoristeRole;
import model.PozoristeUser;
import pozoriste.repository.PozoristeRoleRepository;
import pozoriste.repository.PozoristeUserRepository;

@Controller
@RequestMapping(value="/auth")
public class LoginController {

	@Autowired
	PozoristeUserRepository pur;
	
	@Autowired
	PozoristeRoleRepository proleRepo;
	

	
	@RequestMapping(value="loginPage", method=RequestMethod.GET) 
	public String loginPage() { 
		return "login";
	}
	
	@ModelAttribute
	public void getRoles(Model model) {
		List<PozoristeRole> roles=proleRepo.findAll();
		model.addAttribute("roles", roles);
		
	}
    @RequestMapping(value = "registerUser", method = RequestMethod.GET)
		public String newUser(Model model) {
		PozoristeUser u = new PozoristeUser();
		model.addAttribute("user", u);
		return "register";
	}
	 
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") PozoristeUser u) {
	    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    u.setPassword(passwordEncoder.encode(u.getPassword()));
		
	    PozoristeRole role = proleRepo.findById(1).get();
	    
		u.addRole(role);
		role.addUser(u);
	    
	    pur.save(u);
		System.out.println("SAVED");
	    return "login";
	}

}
