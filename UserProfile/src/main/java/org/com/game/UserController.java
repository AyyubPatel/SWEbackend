package org.com.game;

import org.com.game.model.UserProfile;
import org.com.game.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	
	private UserService userService;

	@Autowired(required=true)
	@Qualifier(value="userService")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
    public String listUser(Model model) {
        model.addAttribute("user", new UserProfile());
        model.addAttribute("listUser", this.userService.getUserList());
        return "user";
    }
     
    
	@RequestMapping(value = "/user/{listId}", method = RequestMethod.GET)
    public String getUser(@PathVariable("userId") String userId, Model model) {
		model.addAttribute("user", this.userService.getUserById(userId));
        return "user";
    }
	@RequestMapping(value = "/user/{listId}", method = RequestMethod.GET)
    public String getOpponent(@PathVariable("userId") String userId, Model model) {
		model.addAttribute("user", this.userService.getOpponent(userId));
        return "user";
    }
	//For add and update person both
    @RequestMapping(value= "/user/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") UserProfile userProfile){
         
        if(userProfile.getUserId() == null){
            //new person, add it
            this.userService.addPlayer(userProfile);
        }else{
            //existing person, call update
            this.userService.updatePlayer(userProfile);
        }
         
        return "redirect:/user";
         
    }
     
    @RequestMapping("/remove/{userId}")
    public String removeUser(@PathVariable("userId") String userId){
         
        this.userService.removePlayer(userId);
        return "redirect:/user";
    }
    
    @RequestMapping("/edit/{userId}")
    public String editUser(@PathVariable("userId") String userId, Model model){
        model.addAttribute("user", this.userService.getUserById(userId));
        model.addAttribute("listUser", this.userService.getUserList());
        return "user";
    }
}
