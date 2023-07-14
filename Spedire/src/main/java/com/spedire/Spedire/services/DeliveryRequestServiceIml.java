package com.spedire.Spedire.services;

import com.spedire.Spedire.dtos.request.DeliveryRequestDTO;
import com.spedire.Spedire.dtos.response.ApiResponse;
import com.spedire.Spedire.models.Request;
import com.spedire.Spedire.repositories.RequestRepository;
import com.spedire.Spedire.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeliveryRequestServiceIml implements DeliveryRequestService {
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final Request request;

    @Override
    public void saveDeliveryRequest(Request deliveryRequestDTO) {
        request.setDestination(deliveryRequestDTO.getDestination());
        request.setCurrentLocation(deliveryRequestDTO.getCurrentLocation());
        var carrier = userRepository.findByPhoneNumber(deliveryRequestDTO.getPhoneNumber());
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
