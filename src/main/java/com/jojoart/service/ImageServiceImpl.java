package com.jojoart.service;

import com.jojoart.dao.ImageDao;
import com.jojoart.dao.ImageVersionDao;
import com.jojoart.domain.Image;
import com.jojoart.domain.ImageType;
import com.jojoart.domain.ImageVersion;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.imgscalr.Scalr.resize;

/**
 * Created by IntelliJ IDEA.
 * User: charlieivie
 * Date: 14/12/2011
 * Time: 19:21
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ImageServiceImpl implements ImageService {

    private ImageDao imageDao;
    private ImageVersionDao imageVersionDao;

    @Transactional
    public void resizeAndStoreImage(Image image, MultipartFile multipartFile) throws IOException {

        if (image.getId() > 0) {
            update(image, multipartFile);
        } else {
            insert(image, multipartFile);
        }

    }

    private void update(Image image, MultipartFile multipartFile) throws IOException {

        imageDao.update(image);
        if (multipartFile!=null && !multipartFile.isEmpty()) {
            image.setMimeType(multipartFile.getContentType());
            persistImageVersions(image, multipartFile);
        }
    }

    private void insert(Image image, MultipartFile multipartFile) throws IOException {

        if (multipartFile!=null && multipartFile.isEmpty()) {
            throw new FileNotFoundException("Multipart file cannot be null");
        }
        image.setMimeType(multipartFile.getContentType());

        imageDao.create(image);
        persistImageVersions(image, multipartFile);
    }

    private void persistImageVersions(Image image, MultipartFile multipartFile) throws IOException {

        BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
        List<ImageVersion> imageVersions = imageVersionDao.getAllImageVersions(image);

        for (ImageVersion imageVersion : imageVersions) {
            imageVersionDao.delete(imageVersion);
        }

        for (ImageType imageType : ImageType.values()) {
            byte[] bytes = getResizedBytes(bufferedImage, multipartFile, imageType.getMaxSize());
            imageVersionDao.create(new ImageVersion(bytes, imageType.toString(), image));
        }
    }

    protected byte[] getResizedBytes(BufferedImage bufferedImage, MultipartFile multipartFile, int maxSize) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());

        if (bufferedImage.getWidth() > maxSize || bufferedImage.getHeight() > maxSize) {
            ImageIO.write(resize(bufferedImage, maxSize), extension, baos);
        } else {
            ImageIO.write(bufferedImage, extension, baos);
        }

        return baos.toByteArray();
    }

    @Autowired
    public void setImageDao(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    @Autowired
    public void setImageVersionDao(ImageVersionDao imageVersionDao) {
        this.imageVersionDao = imageVersionDao;
    }
}
