package com.example.demo.service.impl;

import com.example.demo.service.QiniuUploadService;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;


/**
 * Created by Administrator on 2017/7/2.
 */
@Service
public class QiniuUploadServiceImpl implements QiniuUploadService {

    private static  final String ACCESS_KEY = "SMfrSeCPjLqtjTvmUr-QN0ZRm7rixAzqpAKdEXOR";
    private static final String SECRET_KEY = "aBcyVNrglFDsAZ5RcdUDLjTHFZnwpjq49GhSOqnN";
    private static final String BUCKET_NAME = "spring";
    private Auth auth;


    public QiniuUploadServiceImpl(){
        this.auth = Auth.create(ACCESS_KEY,SECRET_KEY);
    }

    @Override
    public String getUploadToken() {

        return auth.uploadToken(BUCKET_NAME);
    }
}
