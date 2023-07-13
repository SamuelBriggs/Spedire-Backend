package com.spedire.Spedire.services;

import com.spedire.Spedire.dtos.request.DeliveryRequest;
import com.spedire.Spedire.dtos.response.ApiResponse;
import com.spedire.Spedire.models.Request;
import com.spedire.Spedire.repositories.RequestRepository;
import com.spedire.Spedire.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeliveryRequestServiceIml implements DeliveryRequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final Request request;

    @Override
    public void save(DeliveryRequest deliveryRequest) {
        request.setDestination(deliveryRequest.getDestination());
        request.setCurrentLocation(deliveryRequest.getCurrentLocation());
        var carrier = userRepository.findByPhoneNumber(deliveryRequest.getPhoneNumber());
        request.setPhoneNumber(carrier.getPhoneNumber());
        requestRepository.save(request);
    }

    @Override
    public ApiResponse<?> findById(String id) {
        return null;
    }

    @Override
    public ApiResponse<?> getallRequests() {
        return null;
    }
}
