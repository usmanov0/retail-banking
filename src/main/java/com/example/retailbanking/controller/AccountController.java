package com.example.retailbanking.controller;

import com.example.retailbanking.model.*;
import com.example.retailbanking.service.AccountService;
import com.example.retailbanking.service.TransactionService;
import com.example.retailbanking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {
    private UserService userService;
    private AccountService accountService;
    private TransactionService transactionService;

    @RequestMapping("/currentAccount")
    public String currentAccount(Model model, Principal principal){
        List<Transaction> transactionList = transactionService.findTransactionList(principal.getName());

        User user = userService.findByUsername(principal.getName());
        Account account = user.getAccount();

        model.addAttribute("currentAccount", account);
        model.addAttribute("TransactionList", transactionList);
        return "currentAccount";
    }

    @RequestMapping("/savingsAccount")
    public String savingsAccount(Model model, Principal principal){
        List<SavingsTransaction> savingsTransactionList = transactionService.findSavingTransactionList(principal.getName());

        User user = userService.findByUsername(principal.getName());
        SavingsAccount savingsAccount = user.getSavingsAccount();

        model.addAttribute("savingsAccount", savingsAccount);
        model.addAttribute("savingTransactiontList", savingsTransactionList);
        return "savingsAccount";
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.GET)
    public String deposit(Model model){
        model.addAttribute("accountType","");
        model.addAttribute("amount", "");

        return "deposit";
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public String depositPOST(@ModelAttribute("amount") String amount, @ModelAttribute("accountType") String accountType, Principal principal){

        accountService.deposit(accountType, Double.parseDouble(amount),principal);
        return "redirect:/userFront";
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.GET)
    public String withdraw(Model model){
        model.addAttribute("accountType", "");
        model.addAttribute("amount","");

        return "withdraw";
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public String withdrawPOST(@ModelAttribute("amount") String amount, @ModelAttribute("accountType")String accountType, Principal principal){

        accountService.withdraw(accountType, Double.parseDouble(amount), principal);
        return "redirect:/userFront";
    }





}
