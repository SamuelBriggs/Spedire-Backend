package com.spedire.Spedire.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.spedire.Spedire.exceptions.SpedireException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;;import static com.spedire.Spedire.utils.EmailConstants.CLOUDINARY_IMAGE_URL;


@Service
@AllArgsConstructor
@Slf4j
public class CloudinaryService implements CloudService{
    private final Cloudinary cloudinary;
    @Override
    public String upload(byte[] image) throws SpedireException {
        try {
            Map<?,?> uploadResult = cloudinary.uploader().upload(image, ObjectUtils.emptyMap());
            return (String) uploadResult.get(CLOUDINARY_IMAGE_URL);
        } catch (IOException e) {
            throw new SpedireException(e.getMessage());
        }
    }
}