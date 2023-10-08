package com.example.retailbanking.controller;

import com.example.retailbanking.model.Account;
import com.example.retailbanking.model.Recipient;
import com.example.retailbanking.model.SavingsAccount;
import com.example.retailbanking.model.User;
import com.example.retailbanking.service.TransactionService;
import com.example.retailbanking.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/transfer")
public class TransferController {
    private TransactionService transactionService;
    private UserService userService;


    @RequestMapping(value = "/betweenAccounts", method = RequestMethod.GET)
    public String betweenAccount(Model model){

        model.addAttribute("transferFrom ", "");
        model.addAttribute("transferTo ", "");
        model.addAttribute("amount ","");

        return "betweenAccounts";
    }

    @RequestMapping(value = "/betweenAccounts", method = RequestMethod.POST)
    public String betweenAccountsPost(@ModelAttribute ("Transfer From ") String TransferFrom,
                                      @ModelAttribute ("Transfer To ") String TransferTo,
                                      @ModelAttribute ("Amount ") String amount,
                                      Principal principal){
        User user = userService.findByUsername(principal.getName());
        Account account = user.getAccount();
        SavingsAccount savingsAccount = user.getSavingsAccount();
        transactionService.betweenAccountsTransfer(TransferFrom, TransferTo, amount, account, savingsAccount);

        return "redirect:/userFront";
    }

    @RequestMapping(value = "/recipient", method = RequestMethod.GET)
    public String recipient(Model model, Principal principal){
        List<Recipient> recipientsList = transactionService.findRecipientList(principal);

        Recipient recipient = new Recipient();

        model.addAttribute("recipientList", recipientsList);
        model.addAttribute("recipient", recipient);

        return "recipient";
    }

    @RequestMapping(value = "/recipient/save", method = RequestMethod.POST)
    public String recipientPost(@ModelAttribute ("recipient") Recipient recipient, Principal principal){
        User user = userService.findByUsername(principal.getName());
        recipient.setUser(user);
        transactionService.saveRecipient(recipient);

        return "redirect:/transfer/recipient";
    }

    @RequestMapping(value = "/recipient/edit", method = RequestMethod.GET)
    public String recipientEdit(@RequestParam(value = "recipientName")String recipientName, Model model, Principal principal){
        Recipient recipient = transactionService.findRecipientByName(recipientName);
        List<Recipient> recipientList = transactionService.findRecipientList(principal);

        model.addAttribute("recipientList", recipientList);
        model.addAttribute("recipient", recipient);

        return "recipient";
    }

    @RequestMapping(value = "/recipient/edit", method = RequestMethod.GET)
    @Transactional
    public String recipientDelete(@RequestParam(value = "recipientName")String recipientName, Model model, Principal principal){
        transactionService.deleteRecipientByName(recipientName);

        List<Recipient> recipientList = transactionService.findRecipientList(principal);

        Recipient recipient = new Recipient();
        model.addAttribute("recipient", recipient);
        model.addAttribute("recipientList", recipientList);

        return "recipient";
    }



}
