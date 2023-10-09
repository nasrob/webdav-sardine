package com.example.webdavdemo.service;

import com.github.sardine.DavResource;
import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@NoArgsConstructor
public class SardineService {

    private final static String WEBDAV_URL = "http://localhost:8000/";
    private final static Sardine sardine = SardineFactory.begin();

    public List<DavResource> getAllResources(String path) throws IOException {
        return getDavResourceList(path);
    }

    public List<DavResource> getDirectories(String path) throws IOException {
        List<DavResource> resources = getDavResourceList(path);
        return resources.stream()
                .filter(DavResource::isDirectory)
                .toList();
    }

    public void addDirectory(String path) throws IOException {
        sardine.createDirectory(WEBDAV_URL + path);
    }

    public void copyToDir(String sourceUrl, String destUrl) throws IOException {
        sardine.copy(WEBDAV_URL + sourceUrl, WEBDAV_URL + destUrl, false);
    }

    private static List<DavResource> getDavResourceList(String path) throws IOException {
        return sardine.list(WEBDAV_URL + path);
    }


}
