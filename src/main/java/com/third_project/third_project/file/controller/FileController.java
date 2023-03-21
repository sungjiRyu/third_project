package com.third_project.third_project.file.controller;

import com.third_project.third_project.file.exception.InvalidInputException;
import com.third_project.third_project.file.response.BasicResponse;
import com.third_project.third_project.file.service.FileService;
import com.third_project.third_project.utilities.ResponseMessageUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.nio.file.Path;

@Tag(name = "파일 다운로드", description = "이미지 및 동영상 파일 다운로드")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FileController {
    private final FileService fileService;
    @Operation(summary = "비디오 파일 다운로드", description = "type에 업로드 된 uri 파일을 다운로드",
    responses = {
            @ApiResponse(responseCode = "200", description = ResponseMessageUtils.TRUE),
            @ApiResponse(responseCode = "400", description = ResponseMessageUtils.FALSE,
            content = @Content(schema = @Schema(implementation = BasicResponse.class)))
    })
    @GetMapping("/download/video/{type}/{uri}")
    public ResponseEntity<Resource> downloadVideoFile(
            @Parameter(description = "업로드 된 곳(game: 게임기록)", example = "game") @PathVariable String type,
            @Parameter(description = "파일 uri", example = "jumprope") @PathVariable String uri,
            HttpServletRequest request
    ) throws Exception {
        String filename = fileService.downloadVideoFile(type, uri).getFilename();
        Path folderLocation = fileService.downloadVideoFile(type, uri).getFolderLocation();
        String[] split = filename.split("\\.");
        String ext = split[split.length - 1];
        String exportName = uri + "." + ext;
        Path targetFile = folderLocation.resolve(filename);

        Resource r = null;
        try {
            r = new UrlResource(targetFile.toUri());
        }
        catch (Exception e) {
            throw new InvalidInputException("파일 다운로드 실패");
        }

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(r.getFile().getAbsolutePath());
            if (contentType==null) {
                contentType = "application/octet-stream";
            }
        }
        catch (Exception e) {
            throw new InvalidInputException("파일 다운로드 실패");
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=\"" + URLEncoder.encode(exportName, "UTF-8") + "\"")
                .body(r);
    }


    @Operation(summary = "이미지 파일 다운로드", description = "type에 업로드 된 uri 파일을 다운로드",
    responses = {
            @ApiResponse(responseCode = "200", description = ResponseMessageUtils.TRUE),
            @ApiResponse(responseCode = "400", description = ResponseMessageUtils.FALSE,
            content = @Content(schema = @Schema(implementation = BasicResponse.class)))
    })
    @GetMapping("/download/img/{type}/{uri}")
    public ResponseEntity<Resource> downloadImgFile(
            @Parameter(description = "업로드 된 곳(member : 회원사진)", example = "member") @PathVariable String type,
            @Parameter(description = "파일 uri", example = "승지") @PathVariable String uri,
            HttpServletRequest request
    ) throws Exception {
        String filename = fileService.downloadImageFile(type, uri).getFilename();
        Path folderLocation = fileService.downloadImageFile(type, uri).getFolderLocation();
        String[] split = filename.split("\\.");
        String ext = split[split.length - 1];
        String exportName = uri + "." + ext;
        Path targetFile = folderLocation.resolve(filename);

        Resource r = null;
        try {
            r = new UrlResource(targetFile.toUri());
        }
        catch (Exception e) {
            throw new InvalidInputException("파일 다운로드 실패");
        }

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(r.getFile().getAbsolutePath());
            if (contentType==null) {
                contentType = "application/octet-stream";
            }
        }
        catch (Exception e) {
            throw new InvalidInputException("파일 다운로드 실패");
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=\"" + URLEncoder.encode(exportName, "UTF-8") + "\"")
                .body(r);
    }
}
