package com.cos.security1.oauth;

import com.cos.security1.auth.PrincipalDetails;
import com.cos.security1.model.User;
import com.cos.security1.oauth.provider.FacebookUserInfo;
import com.cos.security1.oauth.provider.GoogleUserInfo;
import com.cos.security1.oauth.provider.OAuth2UserInfo;
import com.cos.security1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    /*SecurityConfig의 구글 로그인 부분 후처리*/
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("UserRequest : " + userRequest.getClientRegistration());
        System.out.println("getAccessToken : " + userRequest.getAccessToken().getTokenValue());
        //구글 로그인 버튼 클릭, 구글 로그인 창으로 이동, 로그인 진행시, 코드를 OAuth-Client로 리턴받는다.
        //Access토큰 요청, 까지가 userRequest
        //이걸로 userRequest정보로 회원 프로필을 받고, 현재 사이트 내에서 회원가입시 필요한 정보 가져오기.
        // loadUser 함수로 프로필을 받아온다.
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("getAttributes : " + oAuth2User.getAttributes());

        //회원가입 강제 진행
        OAuth2UserInfo oAuth2UserInfo = null;
        if(userRequest.getClientRegistration().getRegistrationId().equals("google")){
            System.out.println("구글 로그인 요청");
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        }
        else if(userRequest.getClientRegistration().getRegistrationId().equals("facebook")){
            System.out.println("페이스북 로그인 요청");
            oAuth2UserInfo = new FacebookUserInfo(oAuth2User.getAttributes());
        }
        else{
            System.out.println("우리는 구글과 페이스북만 지원해요.");
        }
        String provider = oAuth2UserInfo.getProvider();
        String providerId = oAuth2UserInfo.getProviderId();
        String email = oAuth2UserInfo.getEmail();
        String username = provider + "_" + providerId;
        String password = bCryptPasswordEncoder.encode("겟인데어");
        String role = "ROLE_USER";
        User userEntity = userRepository.findByUsername(username);
        if(userEntity == null){ //아이디 없음. 회원가입 진행
            userEntity = User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .role(role)
                    .providerId(providerId)
                    .provider(provider)
                    .build();
            userRepository.save(userEntity);
        }

        return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
    }
}
