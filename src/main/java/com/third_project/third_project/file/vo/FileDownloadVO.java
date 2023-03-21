package com.third_project.third_project.file.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.nio.file.Path;

@Data
@AllArgsConstructor
public class FileDownloadVO {
    Path folderLocation;
    String filename;
}
