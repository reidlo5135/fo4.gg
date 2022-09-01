package com.reidlo.fo4.user.application;

import com.reidlo.fo4.common.application.ResponseService;
import com.reidlo.fo4.common.application.RestFactoryService;
import com.reidlo.fo4.common.domain.SingleResult;
import com.reidlo.fo4.user.domain.User;
import com.reidlo.fo4.user.domain.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final RestFactoryService restFactoryService;
    private final ResponseService responseService;

    @Transactional(readOnly = true)
    public SingleResult<String> requestUserInfoByNickName(String nickname) {
        String result = restFactoryService.requestUserInfoByNickName(nickname);
        if(result.isEmpty()) throw new NullPointerException();

        return responseService.getSingleResult(result);
    }

    @Transactional(readOnly = true)
    public SingleResult<Map<String, String>> requestUserMaxDivisionByAccessId(String accessId) {
        List<Map<String, Object>> resultJSON = restFactoryService.requestUserMaxDivisionByAccessId(accessId);
        Map<String, String> result = new HashMap<>();

        for (Map<String, Object> map : resultJSON) {
            int matchType = (int) map.get("matchType");

            String division = map.get("division").toString();
            String achievementDate = map.get("achievementDate").toString().split("T")[0];

            switch (matchType) {
                case 50:
                    result.put("pvpDivision", division);
                    result.put("pvpDate", achievementDate);
                    break;
                case 52:
                    result.put("coachDivision", division);
                    result.put("coachDate", achievementDate);
                    break;
                default:
                    result.put("pvpDivision", null);
                    result.put("pvpDate", null);
                    result.put("coachDivision", null);
                    result.put("coachDate", null);
                    break;
            }
        }

        if(result.size() == 0) {
            result.put("pvpDivision", null);
            result.put("pvpDate", null);
            result.put("coachDivision", null);
            result.put("coachDate", null);
        }

        return responseService.getSingleResult(result);
    }

    @Transactional(readOnly = true)
    public SingleResult<Map<String, String>> requestDivisionJSON(int pvp, int coach) {
        List<Map<String, Object>> resultJSON = restFactoryService.requestDivisionJSON();
        Map<String, String> result = new HashMap<>();

        for (Map<String, Object> map : resultJSON) {
            if (pvp == 0) {
                result.put("pvpDivisionName", "기록이 존재하지 않습니다.");
            } else if (pvp == (int) map.get("divisionId")) {
                result.put("pvpDivisionName", map.get("divisionName").toString());
            }
            if (coach == 0) {
                result.put("coachDivisionName", "기록이 존재하지 않습니다.");
            } else if (coach == (int) map.get("divisionId")) {
                result.put("coachDivisionName", map.get("divisionName").toString());
            }
        }
        return responseService.getSingleResult(result);
    }

    @Transactional
    public SingleResult<Integer> registerUserInfo(User user) throws SQLException {
        List<User> userList = userInfoRepository.findByNickName(user.getNickname());

        user.setPvpImageUrl(restFactoryService.setDivisionImageUrl(user.getPvpDivisionName()));
        user.setCoachImageUrl(restFactoryService.setDivisionImageUrl(user.getCoachDivisionName()));

        if(userList.isEmpty()) {
            return responseService.getSingleResult(userInfoRepository.register(user));
        }

        return null;
    }

    @Transactional(readOnly = true)
    public List<User> findUserListByNickName(String nickname) throws SQLException {
        return userInfoRepository.findByNickName(nickname);
    }
}
