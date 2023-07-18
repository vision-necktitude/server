package gcu.sp.visioinnecktitude.common.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 200 : 요청 성공
     */
    SUCCESS(true, HttpStatus.OK, "요청에 성공하였습니다."),


    /**
     * 2000 : Request, Response 오류
     */

    UNAUTHORIZED(false, HttpStatus.UNAUTHORIZED, "권한이 없는 유저의 접근입니다."),
    EXIST_NICKNAME(false,HttpStatus.CONFLICT,"이미 존재하는 닉네임 입니다."),
    EXIST_MEMBER_ID(false,HttpStatus.CONFLICT,"이미 존재하는 유저 아이디 입니다."),
    NOT_EXIST_ID_OR_PASSWORD(false,HttpStatus.CONFLICT,"아이디 또는 비밀번호를 잘못 입력했습니다."),
    /**
     * 500 :  Database, Server 오류
     */
    DATABASE_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR, "서버와의 연결에 실패하였습니다."),
    PASSWORD_ENCRYPTION_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR, "비밀번호 복호화에 실패하였습니다."),


    MODIFY_FAIL_USERNAME(false,HttpStatus.INTERNAL_SERVER_ERROR,"유저네임 수정 실패"),
    DELETE_FAIL_USERNAME(false,HttpStatus.INTERNAL_SERVER_ERROR,"유저 삭제 실패"),
    MODIFY_FAIL_MEMO(false,HttpStatus.INTERNAL_SERVER_ERROR,"메모 수정 실패"),

    UNEXPECTED_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR, "예상치 못한 에러가 발생했습니다.");


    private final boolean isSuccess;
    private final HttpStatus code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, HttpStatus code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
