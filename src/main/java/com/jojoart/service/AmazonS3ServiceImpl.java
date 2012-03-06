package com.jojoart.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.jojoart.domain.ImageVersion;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 05/03/2012
 * Time: 20:20
 * To change this template use File | Settings | File Templates.
 */

@Service
public class AmazonS3ServiceImpl implements AmazonS3Service {

    private AmazonS3Client s3Client;
    private String bucketName;

    public AmazonS3ServiceImpl() {
    }

    public AmazonS3ServiceImpl(AmazonS3Client s3Client, String bucketName) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
    }

    @Override
    public byte[] getImage(String id) throws IOException {

        S3Object s3Object = s3Client.getObject(bucketName, id);

        return IOUtils.toByteArray(s3Object.getObjectContent());

    }

    @Override
    public void persistImage(ImageVersion imageVersion) {

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageVersion.getImageBlob());

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(imageVersion.getImageBlob().length);

        s3Client.putObject(new PutObjectRequest(
                bucketName,
                String.valueOf(imageVersion.getId()),
                byteArrayInputStream,
                metadata)
        );

    }

}
