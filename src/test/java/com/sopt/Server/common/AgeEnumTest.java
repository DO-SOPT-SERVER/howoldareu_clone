package com.sopt.Server.common;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AgeEnumTest {

    private static final int NINETEEN = 19;
    private static final int TWENTY_ONE = 21;
    private static final int TWENTY_NINE = 29;
    private static final int THIRTY_ONE = 31;
    private static final int THIRTY_NINE = 39;
    private static final int FORTY_ONE = 41;
    private static final int FORTY_NINE = 49;
    private static final int FIFTY_ONE = 51;

    private static final int NINE = 9;


    @Test
    @DisplayName("연령대로 원하는 정보를 가져올 수 있다.")
    void byAge() {
      // then
        Assertions.assertThat(AgeEnum.byAge(NINETEEN)).isEqualTo(AgeEnum.TEENAGER);
        Assertions.assertThat(AgeEnum.byAge(TWENTY_ONE)).isEqualTo(AgeEnum.TWENTIES);
        Assertions.assertThat(AgeEnum.byAge(TWENTY_NINE)).isEqualTo(AgeEnum.TWENTIES);
        Assertions.assertThat(AgeEnum.byAge(THIRTY_ONE)).isEqualTo(AgeEnum.THIRTIES);
        Assertions.assertThat(AgeEnum.byAge(THIRTY_NINE)).isEqualTo(AgeEnum.THIRTIES);
        Assertions.assertThat(AgeEnum.byAge(FORTY_ONE)).isEqualTo(AgeEnum.FORTIES);
        Assertions.assertThat(AgeEnum.byAge(FORTY_NINE)).isEqualTo(AgeEnum.FORTIES);
        Assertions.assertThat(AgeEnum.byAge(FIFTY_ONE)).isEqualTo(AgeEnum.FIFTIES);
    }
    
    @Test
    @DisplayName("10살 이하로 입력한 경우에 나이 정보를 가져올 수 없다.")
    void byAgeWithInvalidAge() {
        Assertions.assertThatThrownBy(
                () -> AgeEnum.byAge(NINE)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("나이가 10세 미만입니다.");
    
    }
}
