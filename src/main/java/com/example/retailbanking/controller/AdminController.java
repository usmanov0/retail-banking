package com.example.retailbanking.controller;

import com.example.retailbanking.model.*;
import com.example.retailbanking.repository.RoleRepo;
import com.example.retailbanking.security.UserRole;
import com.example.retailbanking.service.TransactionService;
import com.example.retailbanking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private TransactionService transactionService;
    private RoleRepo roleRepo;


    @GetMapping("/panel")
    public String profile(Principal principal, Model model) {
        if (!principal.equals("Admin"))
            return "error";

        List<User> users = userService.findUserList();
        model.addAttribute("userList", users);

        return "adminPanel";
    }


    @PostMapping("/activateUser")
    public String profileActivator(@ModelAttribute("username") String username, Model model, Principal principal) {
        if (!principal.getName().equalsIgnoreCase("admin") || username.equalsIgnoreCase("admin")) {
            return "error";
        }

        User user = userService.findByUsername(username);
        user.setEnabled(!user.isEnabled());

        userService.saveUser(user);

        return "redirect:/admin/";
    }

    @PostMapping("/SelfUserActivator")
    public String UserSelfActivator(@ModelAttribute("username") String username, Model model, Principal principal) {
        if (!principal.getName().equals(username))
            return "error";

        User user = userService.findByUsername(username);
        user.setEnabled(!user.isEnabled());

        userService.saveUser(user);

        return "redirect:/";
    }

    @RequestMapping("/account")
    public String currentAccount(@ModelAttribute("username") String username, Model model, Principal principal) {
        if (!principal.getName().equalsIgnoreCase("Admin"))
            return "error";

        List<Transaction> transactionList = transactionService.findTransactionList(username);

        User user = userService.findByUsername(username);
        Account account = user.getAccount();

        model.addAttribute("account", account);
        model.addAttribute("transactionList", transactionList);

        return "account";
    }

    @RequestMapping("/savingAccount")
    public String savingsAccount(@ModelAttribute("username") String username, Model model, Principal principal) {
        if (!principal.getName().equalsIgnoreCase("Admin"))
            return "error";

        List<SavingsTransaction> savingsTransactionList = transactionService.findSavingTransactionList(username);

        User user = userService.findByUsername(username);
        SavingsAccount savingsAccount = user.getSavingsAccount();

        model.addAttribute("SavingsAccount", savingsAccount);
        model.addAttribute("SavingsTransactionList", savingsTransactionList);

        return "savingsAccount";
    }

    @GetMapping("/signup")
    public String signup(Model model, Principal principal){
        if (!principal.getName().equalsIgnoreCase("Admin"))
            return "error";

        User user = new User();
        model.addAttribute("user", user);

        return "signUpAdmin";
    }

    @PostMapping("signup")
    public String signupPost(@ModelAttribute("user") User user, Model model, Principal principal){
        if (!principal.getName().equalsIgnoreCase("Admin"))
            return "error";

        if (userService.checkUserExists(user.getUsername(), user.getEmail())){
            if (userService.checkEmailExists(user.getEmail())){
                model.addAttribute("EmailExists", true);
            }

            if (userService.checkUserNameExists(user.getUsername())){
                model.addAttribute("usernameExists", true);
            }
            return "adminSignup";

        }else {
            Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(new UserRole(user, roleRepo.findByName("ROLE_ADMIN")));
            userService.createUser(user,userRoles);

            model.addAttribute("errorSignup", true);
            model.addAttribute("successSignUp", true);
        }
        return "redirect:/";
    }

}