package com.fitness.gateway.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

//    private final WebClient userServiceWebClient;
//
//    public boolean validateUser(String userId){
//       try{
//           return userServiceWebClient.get()
//                   .uri("http://USERSERVICES/api/users/{userId}/validate",userId)
//                   .retrieve()
//                   .bodyToMono(Boolean.class)
//                   .block();
//       }catch (WebClientException e){
//           e.printStackTrace();
//       }
//
//       return false;
//    }

    private final WebClient.Builder webClientBuilder;

    public Mono<Boolean> validateUser(String userId){

          return webClientBuilder.build()
                    .get()
                    .uri("http://USERSERVICES/api/users/{userId}/validate", userId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .onErrorResume(WebClientResponseException.class,e->{
                        if(e.getStatusCode() == HttpStatus.NOT_FOUND)
                            return Mono.error(new RuntimeException("user not found : "+userId));
                        else if(e.getStatusCode() == HttpStatus.BAD_REQUEST)
                            return Mono.error(new RuntimeException("Invalid : "+userId));

                        return Mono.error(new RuntimeException("Unexpected error : " + userId));

                    });




    }

    public Mono<Boolean> registerUser(RegisterRequest registerRequest) {
        log.info("calling user registration API");
        return webClientBuilder.build()
                .post()
                .uri("http://USERSERVICES/api/users/register")
                .bodyValue(registerRequest)
                .retrieve()
                .bodyToMono(Boolean.class)
                .onErrorResume(WebClientResponseException.class,e->{
                    if(e.getStatusCode() == HttpStatus.BAD_REQUEST)
                        return Mono.error(new RuntimeException("Bad request : "+e.getMessage()));

                    return Mono.error(new RuntimeException("Unexpected error : " + e.getMessage()));

                });

    }
}
