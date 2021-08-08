package com.parking.ticket.payload.response;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }



    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public static final class JwtResponseBuilder {
        private String token;
        private String type = "Bearer";
        private Long id;
        private String username;
        private String email;
        private List<String> roles;

        public JwtResponseBuilder() {
        }

        public static JwtResponseBuilder aJwtResponse() {
            return new JwtResponseBuilder();
        }

        public JwtResponseBuilder withToken(String token) {
            this.token = token;
            return this;
        }

        public JwtResponseBuilder withType(String type) {
            this.type = type;
            return this;
        }

        public JwtResponseBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public JwtResponseBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public JwtResponseBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public JwtResponseBuilder withRoles(List<String> roles) {
            this.roles = roles;
            return this;
        }

        public JwtResponse build() {
            JwtResponse jwtResponse = new JwtResponse(null, id, username, email, roles);
            jwtResponse.token = this.token;
            jwtResponse.type = this.type;
            return jwtResponse;
        }
    }
}
