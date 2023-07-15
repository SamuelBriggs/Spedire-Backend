package com.spedire.Spedire.services;

import com.spedire.Spedire.exceptions.SpedireException;

public interface CloudService {
    String upload(byte[] image) throws SpedireException;
}