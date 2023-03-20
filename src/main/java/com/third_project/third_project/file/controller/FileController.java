//package com.third_project.third_project.file.controller;
//
//import com.third_project.third_project.file.response.BasicResponse;
//import com.third_project.third_project.utilities.ResponseMessageUtils;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@Tag(name = "파일 다운로드", description = "이미지 및 동영상 파일 다운로드")
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api")
//public class FileController {
//    @Operation(summary = "파일 다운로드", description = "type에 업로드 된 uri 파일을 다운로드",
//    responses = {
//            @ApiResponse(responseCode = "200", description = ResponseMessageUtils.TRUE),
//            @ApiResponse(responseCode = "400", description = ResponseMessageUtils.FALSE,
//            content = @Content(schema = @Schema(implementation = BasicResponse.class)))
//    })
//    @GetMapping("/download/{type}/{uri}")
//    public ResponseEntity<Resource> downloadImgFile(
//            @Parameter(description = "업로드 된 곳(game : 게임, individual : 개인, main : 메인페이지)", example = "game") @PathVariable String type,
//            @Parameter(description = "파일 uri", example = "member_649902000") @PathVariable String uri,
//            HttpServletRequest request
//    ) throws Exception {
//        String filename = fileService.downloadImgFile(type, uri).getFilename();
//        Path folderLocation = fileService.downloadImgFile(type, uri).getFolderLocation();
//        String[] split = filename.split("\\.");
//        String ext = split[split.length - 1];
//        String exportName = uri + "." + ext;
//        Path targetFile = folderLocation.resolve(filename);
//
//        Resource r = null;
//        try {
//            r = new UrlResource(targetFile.toUri());
//        }
//        catch (Exception e) {
//            throw new InvalidInputException("파일 다운로드 실패");
//        }
//
//        String contentType = null;
//        try {
//            contentType = request.getServletContext().getMimeType(r.getFile().getAbsolutePath());
//            if (contentType==null) {
//                contentType = "application/octet-stream";
//            }
//        }
//        catch (Exception e) {
//            throw new InvalidInputException("파일 다운로드 실패");
//        }
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=\"" + URLEncoder.encode(exportName, "UTF-8") + "\"")
//                .body(r);
//    }
//}
