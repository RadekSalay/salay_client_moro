package com.salay.controller;

import com.salay.model.Ticket;
import com.salay.model.TicketValidator;
import com.salay.remote.RemoteManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Radek Salay on 3.11.2016.
 */
@Controller
public class ReserveController {
    private final static Logger log = Logger.getLogger(ReserveController.class);
    private ArrayList<Ticket> ticketArrayList = new ArrayList<>();

    @Autowired
    private RemoteManager client;


    @RequestMapping("/")
    public String index() {
        return "redirect: reserveForm";
    }

    @RequestMapping(value = "/reserve", method = RequestMethod.GET)
    public String formInit(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "reserveForm";
    }

    @RequestMapping(value = "/reserve", method = RequestMethod.POST)
    public String formSend(@Valid @ModelAttribute("ticket") Ticket ticket, BindingResult result, Model model) {
        new TicketValidator().validate(ticket, result);// nested validation of ticket attributes

        if (result.hasErrors()) {
            return "reserveForm";
        }
        try {
            log.info("Send " + ticket.toString() + " to server");

            if (client.saveOnServer(ticket))// send ticket to server
            {
                log.info("Ticket " + ticket.toString() + "was written on server");
                ticketArrayList.add(ticket);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("HISTORY ");
                for (Ticket ticketFromHistory : ticketArrayList) {
                    stringBuilder.append("<br/>").append(ticketFromHistory.toString() + "  ").append(LocalDateTime.now().
                            format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss")));
                }
                model.addAttribute("history", stringBuilder.toString());

                model.addAttribute("result", "Ticket was successfully send on server");
            } else {
                model.addAttribute("result", "Server can't write Ticket");
                log.error("Server can't write Ticket");
            }

        } catch (Exception e) {
            log.error("Error with ticket " + ticket.toString() + " " + new Date().toString(), e);
            model.addAttribute("result", "When we sent a ticket, an error occurred");
        }
        return "redirect: result";
    }
}
