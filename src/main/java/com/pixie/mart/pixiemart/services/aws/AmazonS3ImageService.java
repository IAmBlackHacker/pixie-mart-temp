package com.pixie.mart.pixiemart.services.aws;

import com.amazonaws.services.s3.model.*;
import com.pixie.mart.pixiemart.constants.Constant;
import com.pixie.mart.pixiemart.exceptions.InvalidImageException;
import com.pixie.mart.pixiemart.utils.GeneralUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class AmazonS3ImageService extends AmazonClientService {
    private final List<String> validExtensions = Arrays.asList("jpeg", "jpg", "png");

    public String uploadToS3AndGetImageUrl(MultipartFile multipartFile) throws IOException {
        return uploadToS3AndGetImageUrl("", multipartFile);
    }

    public String uploadToS3AndGetImageUrl(String path, MultipartFile multipartFile) throws IOException {
        String extension = Objects.requireNonNull(FilenameUtils.getExtension(multipartFile.getOriginalFilename()))
                .toLowerCase();

        if (!validExtensions.contains(extension)) {
            log.warn("Invalid image extension {}", extension);
            throw new InvalidImageException();
        }

        return uploadMultipartFile(path, multipartFile);
    }

    public String uploadImageFromUrl(String path, URL url) throws IOException {
        String extension = Objects.requireNonNull(FilenameUtils.getExtension(url.getFile())).toLowerCase();
        if (!validExtensions.contains(extension)) {
            log.warn("Invalid image extension {}", extension);
            throw new InvalidImageException();
        }

        URLConnection con = url.openConnection();
        con.setRequestProperty("User-Agent", Constant.USER_AGENT);

        return uploadImageStream(path, con.getContentType(), con.getContentLengthLong(), con.getInputStream());
    }

    public void removeImageFromAmazon(String imageUrl) {
        getClient().deleteObject(new DeleteObjectRequest(getBucketName(), imageUrl));
    }

    private String uploadMultipartFile(String path, MultipartFile multipartFile) throws IOException {
        return uploadImageStream(path, multipartFile.getContentType(), multipartFile.getSize(),
                multipartFile.getInputStream());
    }

    private String uploadImageStream(String path, String contentType, long imageSize, InputStream inputStream) {
        ObjectMetadata data = new ObjectMetadata();
        data.setContentType(contentType);
        data.setContentLength(imageSize);

        String fileName = GeneralUtil.getRandomString();
        String filePathName = Paths.get(path, fileName).toString();

        PutObjectResult objectResult = getClient()
                .putObject(new PutObjectRequest(getBucketName(), filePathName, inputStream, data)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
        log.debug("File created {}", objectResult.getContentMd5());

        return fileName;
    }
}