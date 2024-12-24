package com.example.ecom.controllers;

import com.example.ecom.dtos.*;
import com.example.ecom.exceptions.ProductNotFoundException;
import com.example.ecom.exceptions.UnAuthorizedAccessException;
import com.example.ecom.exceptions.UserNotFoundException;
import com.example.ecom.models.Inventory;
import com.example.ecom.services.InventoryService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryController {


    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public CreateOrUpdateResponseDto createOrUpdateInventory(CreateOrUpdateRequestDto requestDto) {
        CreateOrUpdateResponseDto createOrUpdateResponseDto = new CreateOrUpdateResponseDto();
        try {
            Inventory inventory = inventoryService.createOrUpdateInventory(requestDto.getUserId(),
                    requestDto.getProductId(),
                    requestDto.getQuantity());

            createOrUpdateResponseDto.setInventory(inventory);
            createOrUpdateResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (ProductNotFoundException | UserNotFoundException | UnAuthorizedAccessException e) {
            createOrUpdateResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return createOrUpdateResponseDto;
    }

    public DeleteInventoryResponseDto deleteInventory(DeleteInventoryRequestDto requestDto) {
        DeleteInventoryResponseDto deleteInventoryResponseDto = new DeleteInventoryResponseDto();
        try {
            inventoryService.deleteInventory(requestDto.getUserId(), requestDto.getProductId());
            deleteInventoryResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (UserNotFoundException | UnAuthorizedAccessException | ProductNotFoundException e) {
            deleteInventoryResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return deleteInventoryResponseDto;
    }
}
