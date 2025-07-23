package org.polimi.nexbuy.utils.validation.regex;

/**
 * Classe di utilità contenente modelli regex per la validazione dei dati.
 * Ogni modello è progettato per una convalida specifica del campo.
 *
 *  <p>
 *
 *  I modelli includono:
 *  {@link #NAME_PATTERN}: Convalida nome e cognome
 *  {@link #EMAIL_PATTERN}: Convalida indirizzi email basati su un formato standard.
 *  {@link #TAX_CODE_PATTERN}: Verifica la validità dei codici fiscali italiani utilizzando un modello regex specifico.
 *  {@link #PASSWORD_PATTERN}: Impone criteri per le password, inclusi maiuscole, minuscole, cifre e caratteri speciali, con una lunghezza minima di 8 caratteri.
 *  {@link #COMMON_PASSWORDS_PATTERN}: Rileva password comuni e facilmente indovinabili.
 *  {@link #TEL_PATTERN}: Convalida i numeri di telefono in base a un formato standard.
 *  {@link #CITY_NAME_PATTERN}: Garantisce che il nome della città sia valido, consentendo spazi e trattini.
 *  {@link #DOMAIN_NAME_PATTERN}: Convalida i nomi di dominio in base a un formato standard.
 *  {@link #IVA_PATTERN}: Verifica la validità dei numeri di partita IVA italiani utilizzando un modello regex specifico.
 *  {@link #UNIQUE_CODE_PATTERN}: Convalida i codici unici utilizzando un modello regex specifico.
 *  {@link #RECIPIENT_CODE_PATTERN}: Convalida i codici destinatari utilizzando un modello regex specifico.
 *  Questa classe funge da posizione centrale per i modelli regex, promuovendo l'organizzazione del codice e la riusabilità nella logica di convalida.
 */
public class RegexPatterns {

    // Regex pattern for name and surname validation
    public static final String NAME_PATTERN = "^[A-Z][a-zA-Z ]{2,29}$";

    // Regex pattern for email validation
    public static final String EMAIL_PATTERN = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";

    // Regex pattern for tax code validation
    public static final String TAX_CODE_PATTERN = "^[A-Z]{6}\\d{2}[ABCDEHLMPRST]\\d{2}[A-Z]\\d{3}[A-Z]$";

    // Regex pattern for password validation
    public static final String PASSWORD_PATTERN = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*\\-._]).{8,}$";

    // Regex pattern for detecting common passwords
    public static final String COMMON_PASSWORDS_PATTERN = ".*(?i:123|1234|12345|123456|1234567|12345678|123456789|password|";

    // Regex pattern for telephone number validation
    public static final String TEL_PATTERN = "^\\+39\\s\\d{10}$";

    // Regex pattern for city name validation
    public static final String CITY_NAME_PATTERN = "^[a-zA-ZàèéìíòóùúüçÇ\\s'-]{1,50}$";

    // Regex pattern for domain name validation
    public static final String DOMAIN_NAME_PATTERN = "^(https?:\\/\\/)?(www\\.)?[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}(\\/\\S*)?$";

    // Regex pattern for IVA validation
    public static final String IVA_PATTERN = "^\\d{11}$";

    // Regex pattern for unique code validation
    public static final String UNIQUE_CODE_PATTERN = "^\\d{6}$";

    // Regex pattern for recipient code validation
    public static final String RECIPIENT_CODE_PATTERN = "^\\d{7}$";

}
