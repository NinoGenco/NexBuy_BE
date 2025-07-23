package org.polimi.nexbuy.utils.validation.validator;
import org.polimi.nexbuy.exception.customExceptions.InvalidDataException;
import org.polimi.nexbuy.model.User;
import org.polimi.nexbuy.utils.validation.regex.RegexPatterns;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * Classe per la validazione dei dati relativi alla registrazione di un utente
 */
@SuppressWarnings("All")
@Component
public class UserRegistrationValidator {

    public void validateRegistration(User user) throws InvalidDataException {

        validateName(normalizeName(user.getFirstName()));
        validateSurname(normalizeSurname(user.getLastName()));
        validateEmail(user.getEmail());
        validatePassword(user.getFirstName(), user.getLastName(), user.getPassword());

    }

    public void validateUpdate(User user) throws InvalidDataException {

        validateName(normalizeName(user.getFirstName()));
        validateSurname(normalizeSurname(user.getLastName()));
        validateEmail(user.getEmail());

    }

    public void validateUpdatePassword(User user, String password) throws InvalidDataException {
        validatePassword(user.getFirstName(), user.getLastName(), password);
    }

    private void validateName(String name) throws InvalidDataException {

        if (!name.matches(RegexPatterns.NAME_PATTERN)) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'nome' deve avere una lunghezza compresa tra 3 e 30 caratteri, il primo carattere deve essere MAIUSCOLO e non può contenere numeri e/o caratteri speciali!");
        }

    }

    public static String normalizeName(String name) throws InvalidDataException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'nome' non può essere vuoto!");
        }

        String trimmedName = name.trim().replaceAll("\\s+", " ");
        String firstLetter = trimmedName.substring(0, 1).toUpperCase();
        String restOfName = trimmedName.length() > 1 ? trimmedName.substring(1).toLowerCase() : "";
        return firstLetter + restOfName;
    }

    private void validateSurname(String surname) throws InvalidDataException {

        if (!surname.matches(RegexPatterns.NAME_PATTERN)) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'cognome' deve avere una lunghezza compresa tra 3 e 30 caratteri, il primo carattere deve essere MAIUSCOLO e non può contenere numeri e/o caratteri speciali!");
        }

    }

    public static String normalizeSurname(String surname) throws InvalidDataException {
        if (surname == null || surname.trim().isEmpty()) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'cognome' non può essere vuoto!");
        }

        String trimmedSurname = surname.trim().replaceAll("\\s+", "");
        String firstLetter = trimmedSurname.substring(0, 1).toUpperCase();
        String restOfSurname = trimmedSurname.length() > 1 ? trimmedSurname.substring(1).toLowerCase() : "";
        return firstLetter + restOfSurname;

    }

    private void validateEmail(String email) throws InvalidDataException {

        if (email == null || email.trim().isEmpty()) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'email' non può essere vuoto!");
        }
        if (!email.matches(RegexPatterns.EMAIL_PATTERN)) {
            throw new InvalidDataException("Errore durante la registrazione: il campo 'email' non è nel formato corretto!");
        }

    }

    private void validatePassword(String name, String surname, String password) throws InvalidDataException {

        if (password == null || password.trim().isEmpty()) {
            throw new InvalidDataException("Errore durante la registrazione: la password non può essere vuota!");
        }

        if (!password.matches(RegexPatterns.PASSWORD_PATTERN)) {
            throw new InvalidDataException("Errore durante la registrazione: la password deve seguire i seguenti criteri:\n" +
                    "- Almeno una lettera MAIUSCOLA\n" +
                    "- Almeno una lettera minuscola\n" +
                    "- Almeno un numero\n" +
                    "- Almeno un carattere speciale\n" +
                    "- Lunghezza minima di 8 caratteri\n"
            );
        }

        if (password.matches(RegexPatterns.COMMON_PASSWORDS_PATTERN + Pattern.quote(name) + "|" + Pattern.quote(surname) + ").*")) {
            throw new InvalidDataException("Errore durante la registrazione: la password non può contenere sequenze comuni o informazioni personali!");
        }

    }

}
