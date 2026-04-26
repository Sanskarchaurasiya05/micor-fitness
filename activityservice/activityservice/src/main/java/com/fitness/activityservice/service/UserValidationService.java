package com.fitness.activityservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserValidationService {

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

    public boolean validateUser(String userId){
        try{
            Boolean response = webClientBuilder.build()
                    .get()
                    .uri("http://USERSERVICES/api/users/{userId}/validate", userId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();

            return response != null && response;

        } catch (WebClientException e){
            e.printStackTrace();
        }

        return false;
    }

}
