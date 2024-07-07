package gift.controller;

import gift.model.dto.TokenResponseDto;
import gift.model.dto.MemberRequestDto;
import gift.repository.MemberDao;
import gift.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    private final MemberDao memberDao;
    private final AuthService authService;

    public MemberController(MemberDao memberDao, AuthService authService) {
        this.memberDao = memberDao;
        this.authService = authService;
    }

    @PostMapping("/members/register")
    public ResponseEntity<TokenResponseDto> register(
        @Valid @RequestBody MemberRequestDto memberRequestDto) {
        memberDao.insertMember(memberRequestDto.toEntity());
        TokenResponseDto tokenResponseDto = new TokenResponseDto(
            authService.getToken(memberRequestDto));
        return ResponseEntity.status(HttpStatus.OK).body(tokenResponseDto);
    }

    @PostMapping("/members/login")
    public ResponseEntity<TokenResponseDto> login(
        @Valid @RequestBody MemberRequestDto memberRequestDto) {
        TokenResponseDto tokenResponseDto = new TokenResponseDto(
            authService.getToken(memberRequestDto));
        return ResponseEntity.status(HttpStatus.OK).body(tokenResponseDto);
    }
}
