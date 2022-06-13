package com.exfi.reporthandler.validation;

import com.exfi.reporthandler.exception.GroupNotValidException;
import com.exfi.reporthandler.exception.UserNotValidException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.keycloak.representations.AccessToken;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ValidationLayer implements IValidationLayer {

    private final String gatewayUrl = "http://localhost:8060/";

    private final KeycloakRestTemplate template;
    private final ObjectMapper objectMapper;

    public ValidationLayer(KeycloakRestTemplate template) {
        this.template = template;
        this.objectMapper = new ObjectMapper();
    }


    public boolean userIsValid() {
        AccessToken token = getCurrentAccessToken();
        String email = token.getEmail();

        if (email == null || email.equals("")) {
            String message = "Not found email for user: " + token.getPreferredUsername();
            throw new UserNotValidException(message);
        }

        String endpoint = gatewayUrl + "manager_user/getUsersByEmail?email=" + email;
        ResponseEntity<String> response = template.getForEntity(endpoint, String.class);

        String json = response.getBody();
        List<Map<String, Object>> users = convertJsonToMap(json);

        validateUserData(token, email, users);
        return false;
    }

    public boolean groupIdsIsValid() {
        String endpoint = gatewayUrl + "manager/getGroups?ids=1,2,3";
        ResponseEntity<String> response = template.getForEntity(endpoint, String.class);

        String json = response.getBody();
        List<Map<String, Object>> groups = convertJsonToMap(json);

        List<String> inactiveIds = getInactiveIds(groups);
        if (!inactiveIds.isEmpty()) {
            String message = "Current group ids have inactive status. " + String.join(",", inactiveIds);
            throw new GroupNotValidException(message);
        }

        return false;
    }

    private void validateUserData(AccessToken token, String email, List<Map<String, Object>> users) {
        if (users.size() == 0) {
            throw new UserNotValidException("Not found user with email: " + email);
        }
        if (users.size() != 1) {
            throw new UserNotValidException("Found multiple users with the same email: " + email);
        }

        String activityStatus = (String) users.get(0).get("activityStatus");
        if (activityStatus.equals("INACTIVE")) {
            throw new UserNotValidException("Current user is inactive. User: " + token.getPreferredUsername());
        }
    }

    private AccessToken getCurrentAccessToken() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        KeycloakPrincipal<?> principal = (KeycloakPrincipal<?>) authentication.getPrincipal();
        KeycloakSecurityContext keycloakSecurityContext = principal.getKeycloakSecurityContext();

        return keycloakSecurityContext.getToken();
    }

    private List<String> getInactiveIds(List<Map<String, Object>> groups) {
        return groups.stream()
                .filter(group -> group.get("activityStatus").equals("INACTIVE"))
                .map(group -> String.valueOf(group.get("id")))
                .collect(Collectors.toList());
    }

    private List<Map<String, Object>> convertJsonToMap(String json) {
        try {
            return objectMapper.readValue(json, List.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
