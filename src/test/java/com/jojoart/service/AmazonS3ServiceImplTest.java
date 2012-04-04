package com.jojoart.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.jojoart.domain.Image;
import com.jojoart.domain.ImageType;
import com.jojoart.domain.ImageVersion;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.internal.util.reflection.Whitebox.setInternalState;

@RunWith(MockitoJUnitRunner.class)
public class AmazonS3ServiceImplTest {

    private String bucketName = "test-jojoart";
    private String myAccessKeyID = System.getenv("AWS_ACCESS_KEY");
    private String mySecretKey = System.getenv("AWS_SECRET_ACCESS_KEY");
    private AmazonS3ServiceImpl amazonS3Service;

    @Before
    public void setUp() throws Exception {

        AWSCredentials myCredentials = new BasicAWSCredentials(myAccessKeyID, mySecretKey);
        AmazonS3Client mockS3Client = new AmazonS3Client(myCredentials);

        amazonS3Service = new AmazonS3ServiceImpl(mockS3Client, bucketName);

    }

    @Test
    @Ignore("amazon s3 integration test")
    public void insertImageAndGet() throws IOException {
        File testJpg = new ClassPathResource("image/sf.gif").getFile();

        byte[] expectedBytes = IOUtils.toByteArray(new FileInputStream(testJpg));

        ImageVersion imageVersion = new ImageVersion(expectedBytes, ImageType.NORMAL.name(), new Image(1l));
        imageVersion.setId(1l);

        amazonS3Service.persistImage(imageVersion);

        byte[] actualBytes = amazonS3Service.getImage("1");

        assertEquals(expectedBytes.length, actualBytes.length);
    }

}
