package com.KiyoInteriors.ECommerce.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileToMultipartFileConverter {

    public MultipartFile convert(File file) {
        return new CustomMultipartFile(file);
    }

    private static class CustomMultipartFile implements MultipartFile {

        private final File file;

        public CustomMultipartFile(File file) {
            this.file = file;
        }

        @Override
        public String getName() {
            return file.getName();
        }

        @Override
        public String getOriginalFilename() {
            return file.getName();
        }

        @Override
        public String getContentType() {
            // Adjust the content type according to your needs
            return "image/jpeg";
        }

        @Override
        public boolean isEmpty() {
            return file.length() == 0;
        }

        @Override
        public long getSize() {
            return file.length();
        }

        @Override
        public byte[] getBytes() throws IOException {
            try (InputStream inputStream = new FileInputStream(file)) {
                return inputStream.readAllBytes();
            }
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return new FileInputStream(file);
        }

        @Override
        public void transferTo(File dest) throws IOException, IllegalStateException {
            throw new UnsupportedOperationException("Transfer operation is not supported");
        }
    }
}
