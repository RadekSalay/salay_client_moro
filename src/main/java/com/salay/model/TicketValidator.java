package com.salay.model;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Radek Salay on 3.11.2016.
 */
public class TicketValidator implements Validator {
    private final Pattern PATTERN_TO_FIND_TAGS_IN_TICKET = Pattern.compile("<.*>.*<.*>");
    private final static Logger log = Logger.getLogger(TicketValidator.class);


    @Override
    public boolean supports(Class<?> clazz) {
        return Ticket.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Matcher matcher = PATTERN_TO_FIND_TAGS_IN_TICKET.matcher(((Ticket) target).getFirstName());
        if (matcher.find()){
            errors.rejectValue("firstName","firstName","In the firstName, was found malicious code");
            log.warn("Malicious code found in firstName "+((Ticket) target).toString());
        }
        matcher = PATTERN_TO_FIND_TAGS_IN_TICKET.matcher(((Ticket) target).getLastName());
        if (matcher.find()){
            errors.rejectValue("lastName","lastName","In the lastName, was found malicious code");
            log.warn("Malicious code found in lastName"+((Ticket) target).toString());

        }
        matcher = PATTERN_TO_FIND_TAGS_IN_TICKET.matcher(((Ticket) target).getEmail());
        if (matcher.find()){
            errors.rejectValue("email","email","In the email, was found malicious code");
            log.warn("Malicious code found in email "+((Ticket) target).toString());

        }

    }
}
