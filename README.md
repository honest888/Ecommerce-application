# Ecommerce Application
An e-commerce website is set up to serve a part or the whole process of buying and selling goods or providing services, from displaying and introducing goods and services to entering into contracts and providing services. service, payment and after-sale service.

## LINK DEMO
<div align='center'>

[Demo](https://jira-project.herokuapp.com)

</div>

## photo overview
<p align='center'>
<img src='pic/0.jpg'></img>
</p>

## VIDEO DEMO
<div align='center'>

[![IMAGE ALT TEXT HERE](https://img.youtube.com/vi/YXG24rEs8Q4/0.jpg)](https://youtu.be/YXG24rEs8Q4)

</div>

## API Token Refresh
```java
// Refresh token
    @GetMapping(TOKEN_VIEW + REFRESH_VIEW)
    public void refreshToken(HttpServletRequest request, HttpServletResponse response)
            throws StreamWriteException, DatabindException, IOException {
        var header = request.getHeader(AUTHORIZATION);
        // check token format in authorization header
        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            // get token from authorization header
            try {
                var refreshToken = header.substring(TOKEN_PREFIX.length());
                var algorithm = HMAC256(SECRET_KEY.getBytes());
                var user = userService.getUser(require(algorithm).build().verify(refreshToken).getSubject());
                var tokens = new HashMap<>();
                tokens.put(ACCESS_TOKEN_KEY,
                        create().withSubject(user.getEmail())
                                .withExpiresAt(new Date(currentTimeMillis() + EXPIRATION_TIME))
                                .withIssuer(request.getRequestURL().toString())
                                .withClaim(ROLE_CLAIM_KEY,
                                        singleton(new Role(ROLE_PREFIX + user.getRole().getName().toUpperCase()))
                                                .stream().map(Role::getName).collect(toList()))
                                .sign(algorithm));
                tokens.put(REFRESH_TOKEN_KEY, refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception e) {
                var errorMsg = e.getMessage();
                response.setHeader(ERROR_HEADER_KEY, errorMsg);
                response.setStatus(FORBIDDEN.value());
                var error = new HashMap<>();
                error.put(ERROR_MESSAGE_KEY, errorMsg);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
```

## Integration
<img src='pic/1.jpg' align='left' width='3%' height='3%'></img>
<div style='display:flex;'>

- Java JWT » 4.0.0

</div>
