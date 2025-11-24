package com.apirip.trukea.common.web;

import com.apirip.trukea.chat.service.ChatService;
import com.apirip.trukea.offer.service.OfferService;
import com.apirip.trukea.student.repo.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * ControllerAdvice global para agregar atributos comunes a todas las vistas
 */
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private final ChatService chatService;
    private final OfferService offerService;
    private final StudentRepository studentRepository;

    @ModelAttribute("unreadConversationsCount")
    public Long addUnreadConversationsCount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Solo calcula si el usuario está autenticado
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            return studentRepository.findByEmail(auth.getName())
                    .map(chatService::countUnreadConversations)
                    .orElse(0L);
        }

        return 0L;
    }

    @ModelAttribute("pendingOffersCount")
    public Integer addPendingOffersCount() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Solo calcula si el usuario está autenticado
        if (auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser")) {
            return studentRepository.findByEmail(auth.getName())
                    .map(student -> offerService.findPendingReceivedByOwner(student.getIdStudent()).size())
                    .orElse(0);
        }

        return 0;
    }
}
