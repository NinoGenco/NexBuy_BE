package org.polimi.nexbuy.service.implementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.polimi.nexbuy.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Classe Service per la gestione degli utenti.
 */
@SuppressWarnings("All")
@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
}
