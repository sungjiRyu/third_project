package com.third_project.third_project.member;

import com.third_project.third_project.entity.MemberImgEntity;
import com.third_project.third_project.entity.MemberInfoEntity;
import com.third_project.third_project.member.VO.*;
import com.third_project.third_project.repository.*;
import com.third_project.third_project.utilities.AESAlgorithm;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberInfoRepository miRepo;
    private final GenInfoRepository giRepo;

    private final ExStatusRepository esRepo;
    private final MemberImgRepository mimgRepo;
    @Value("${file.image.exercise.member}")   String member_image_path;




    // 가입 필수 정보
    public MemberJoinResponseVO joinMember(MemberJoinVO data) {
        if(miRepo.countByMiId(data.getId()) >= 1) {
            MemberJoinResponseVO responseVO = MemberJoinResponseVO.builder()
                    .status(false)
                    .message("이미 존재하는 ID 입니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
            return responseVO;
        }
        else if(!(data.getPwd().equals(data.getConfirmpwd()))) {
            MemberJoinResponseVO responseVO = MemberJoinResponseVO.builder()
                    .status(false)
                    .message("비밀번호와 확인비밀번호가 일치하지 않습니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
            return responseVO;
        }
        else if(miRepo.countByMiNickname(data.getNickname()) >= 1) {
            MemberJoinResponseVO responseVO = MemberJoinResponseVO.builder()
                    .status(false)
                    .message("이미 존재하는 닉네임 입니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
            return responseVO;
        }
        try{
            String encPwd = AESAlgorithm.Encrypt(data.getPwd());
            MemberInfoEntity miEntity = MemberInfoEntity.builder()
                    .miId(data.getId())
                    .miPwd(encPwd)
                    .miNickname(data.getNickname())
                    .build();
                    miRepo.save(miEntity);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        MemberJoinResponseVO responseVO = MemberJoinResponseVO.builder()
                .status(true)
                .message("가입되었습니다.")
                .code(HttpStatus.OK)
                .build();
        return responseVO;
    }


    // 가입 후 추가정보 ( 입력 / 수정 )
    public MemberAddInfoResponseVO addInfo(Long seq, MemberAddInfoVO data) {
        Optional<MemberInfoEntity> findseq = miRepo.findById(seq);
        if(!(findseq.isPresent())) {
            MemberAddInfoResponseVO responseVO = MemberAddInfoResponseVO.builder()
                    .status(false)
                    .message("존재하지 않는 seq 입니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
            return responseVO;
        }
//        else if(miRepo.countByMiNickname(data.getNickname()) >= 1) {
//            MemberAddInfoResponseVO responseVO = MemberAddInfoResponseVO.builder()
//                    .status(false)
//                    .message("이미 존재하는 닉네임 입니다.")
//                    .code(HttpStatus.BAD_REQUEST)
//                    .build();
//            return responseVO;
//        }
        else {
            MemberInfoEntity miEntity = miRepo.findByMiSeq(seq);
//            miEntity.setMiNickname(data.getNickname());
            miEntity.setMiTall(data.getTall());
            miEntity.setMiWeight(data.getWeight());
            miEntity.setMiClassNum(data.getClassNum());
            miEntity.setGen(giRepo.findByGiSeq(data.getGiSeq()));
            miEntity.setExStatus(esRepo.findByEsSeq(data.getEsSeq()));
            miRepo.save(miEntity);
            MemberAddInfoResponseVO responseVO = MemberAddInfoResponseVO.builder()
                    .status(true)
                    .message("정보가 수정되었습니다.")
                    .code(HttpStatus.OK)
                    .build();
            return responseVO;
        }
    }


    // 개인정보 수정 ( 비밀번호, 이미지 업로드 )
    public MemberUpdateResponseVO updateMember(Long seq, MemberUpdateVO data) {
        Optional<MemberInfoEntity> findseq = miRepo.findById(seq);
        if(!(findseq.isPresent())) {
            MemberUpdateResponseVO responseVO = MemberUpdateResponseVO.builder()
                    .status(false)
                    .message("존재하지 않는 seq 입니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
            return responseVO;
        }
        else if(!(data.getPwd().equals(data.getConfirmpwd()))) {

            MemberUpdateResponseVO responseVO = MemberUpdateResponseVO.builder()
                    .status(false)
                    .message("비밀번호와 확인비밀번호가 일치하지 않습니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
            return responseVO;
        }

        else {
            try{
                String encPwd = AESAlgorithm.Encrypt(data.getPwd());
                MemberInfoEntity miEntity = miRepo.findByMiSeq(seq);
                    miEntity.setMiPwd(encPwd);

                miRepo.save(miEntity);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            MemberUpdateResponseVO responseVO = MemberUpdateResponseVO.builder()
                    .status(true)
                    .message("변경 하였습니다.")
                    .code(HttpStatus.OK)
                    .build();
            return responseVO;
        }
    }

    // 회원 탈퇴
    @Transactional
    public MemberDeleteResponseVO deleteMember(Long seq, MemberDeleteVO data) {
        Optional<MemberInfoEntity> findseq = miRepo.findById(seq);
        if(!(findseq.isPresent())) {
            MemberDeleteResponseVO responseVO = MemberDeleteResponseVO.builder()
                    .status(false)
                    .message("존재하지 않는 seq 입니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
            return responseVO;
        }
        else {
            MemberInfoEntity entity = miRepo.findByMiSeq(seq);
            String pwd= entity.getMiPwd();
            if(!(data.getPwd().equals(pwd))) {
                MemberDeleteResponseVO responseVO = MemberDeleteResponseVO.builder()
                        .status(false)
                        .message("비밀번호를 확인하세요.")
                        .code(HttpStatus.BAD_REQUEST)
                        .build();
                return responseVO;
            }
            else {
                miRepo.deleteByMiSeq(seq);
                MemberDeleteResponseVO responseVO = MemberDeleteResponseVO.builder()
                        .status(true)
                        .message("삭제되었습니다.")
                        .code(HttpStatus.OK)
                        .build();
                return responseVO;
            }
        }
    }
    // 회원 정보 조회
    public MemberSearchResponseVO searchMember (Long seq) {
        MemberInfoEntity miEntity = miRepo.findByMiSeq(seq);
        if(miEntity == null) {
            MemberSearchResponseVO responseVO = MemberSearchResponseVO.builder()
                    .status(false)
                    .message("존재하지 않는 seq 입니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
            return responseVO;
        }
//            MemberSearchVO searchVO = new MemberSearchVO();
            MemberSearchVO searchVO = MemberSearchVO.builder()
                    .id(miEntity.getMiId())
                    .tall(miEntity.getMiTall())
                    .weight(miEntity.getMiWeight())
                    .nickname(miEntity.getMiNickname())
                    .classnum(miEntity.getMiClassNum())
                    .gen(miEntity.getGen().getGiStatus())
                    .type(miEntity.getExStatus().getEsType())
                    .mimg(miEntity.getMimg().getMimgUrl())
                    .build();

            MemberSearchResponseVO responseVO = MemberSearchResponseVO.builder()
                    .info(searchVO)
                    .status(true)
                    .message("조회 완료")
                    .code(HttpStatus.OK)
                    .build();
                    return responseVO;

    }
    // 이미지 업로드
    @Transactional
    public MemberImgResponseVO addMemberImg (Long seq, MultipartFile file) {
        Optional<MemberInfoEntity> findseq = miRepo.findById(seq);

        if(!(findseq.isPresent())) {
            MemberImgResponseVO responseVO = MemberImgResponseVO.builder()
                    .status(false)
                    .message("존재하지 않는 seq 입니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
            return responseVO;
        }
        else {
            MemberInfoEntity member = miRepo.findByMiSeq(seq);
            MemberImgEntity img = member.getMimg();
            String Url = img.getMimgUrl();

            if( img.getMimgSeq() == 1 ) {
                Path folderLocation = null;
                folderLocation = Paths.get(member_image_path);

                String saveFilename = "";
                String orginFileName = file.getOriginalFilename();

                String[]split = orginFileName.split("\\.");

                String firstname = "";  //split[0] + "_";
                String ext = split[split.length -1];
                for(int i=0; i<split.length; i++) {
                    if(i != split.length - 1)
                        firstname += split[i];
                }


                Calendar c = Calendar.getInstance();
                saveFilename += firstname + c.getTimeInMillis() + "." + ext;
                firstname = firstname + member.getMiSeq();
                Path targetFile = folderLocation.resolve(saveFilename);

                try {
                    Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                MemberImgEntity newImg = MemberImgEntity.builder()
                        .mimgName(saveFilename)
                        .mimgUrl(firstname)
                        .build();
                mimgRepo.save(newImg);

                MemberInfoEntity miEntity = miRepo.findByMiSeq(seq);
                miEntity.setMimg(newImg);
                miRepo.save(miEntity);


                MemberImgResponseVO responseVO = MemberImgResponseVO.builder()
                        //.mimgUrl(saveFilename)
                        .status(true)
                        .message("이미지가 변경되었습니다.")
                        .code(HttpStatus.ACCEPTED)
                        .build();
                return responseVO;
            }
            else {

            mimgRepo.deleteByMimgUrl(Url);

                Path folderLocation = null;
                folderLocation = Paths.get(member_image_path);

                String saveFilename = "";
                String orginFileName = file.getOriginalFilename();

                String[]split = orginFileName.split("\\.");

                String firstname = "";  //split[0] + "_";
                String ext = split[split.length -1];
                for(int i=0; i<split.length; i++) {
                    if(i != split.length - 1)
                        firstname += split[i];
                }
//                Long MemSeq = member.getMiSeq();

                Calendar c = Calendar.getInstance();
                saveFilename += firstname + c.getTimeInMillis() + "." + ext;
                firstname = firstname + member.getMiSeq();
                Path targetFile = folderLocation.resolve(saveFilename);

                try {
                    Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                MemberImgEntity newImg = MemberImgEntity.builder()
                                .mimgName(saveFilename)
                                .mimgUrl(firstname)
                                .build();
                mimgRepo.save(newImg);

                MemberInfoEntity miEntity = miRepo.findByMiSeq(seq);
                    miEntity.setMimg(newImg);
                    miRepo.save(miEntity);


                MemberImgResponseVO responseVO = MemberImgResponseVO.builder()
                        //.mimgUrl(saveFilename)
                        .status(true)
                        .message("이미지가 변경되었습니다.")
                        .code(HttpStatus.ACCEPTED)
                        .build();
                return responseVO;
            }
        }
    }

    // login
    public MemberLoginResponseVO login (MemberLoginVO LoginVO) throws Exception {

//        Long findSeq = miRepo.findByMiSeq(LoginVO.getId());
//        MemberInfoEntity miEntity = miRepo.findByMiId(LoginVO.getId());
//            Long searchSeq = miEntity.getMiSeq();
//        MemberInfoEntity miEntity2 = miRepo.findByMiPwd(AESAlgorithm.Encrypt(LoginVO.getPwd()));
//            Long searchSeq2 = miEntity2.getMiSeq();
        MemberInfoEntity miEntity = miRepo.findByMiIdAndMiPwd(LoginVO.getId(), AESAlgorithm.Encrypt(LoginVO.getPwd()));

        if ( miEntity == null) {
            MemberLoginResponseVO responseVO = MemberLoginResponseVO.builder()
                    //.mimgUrl(saveFilename)
                    .status(false)
                    .message("Id / Pwd 를 확인하세요.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
            return responseVO;
        }
        MemberLoginResponseVO responseVO = MemberLoginResponseVO.builder()
                //.mimgUrl(saveFilename)
                .status(true)
                .message("로그인 성공 하였습니다.")
                .code(HttpStatus.ACCEPTED)
                .build();
        return responseVO;
        }

    // logout
    public MemberLogoutResponseVO logout() {
        MemberLogoutResponseVO responseVO = MemberLogoutResponseVO.builder()
                //.mimgUrl(saveFilename)
                .status(true)
                .message("로그아웃 완료.")
                .code(HttpStatus.ACCEPTED)
                .build();
        return responseVO;
    }
}