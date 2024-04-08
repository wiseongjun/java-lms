package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.session.CoverImageMeta.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CoverImageMetaTest {

  @Test
  @DisplayName("정상 이미지 사이즈, width, height 를 입력한 경우" +
      "CoverImageDetailInfo 생성 테스트")
  void coverImageDetailImage_create_test() {
    CoverImageMeta coverImageMeta = new CoverImageMeta(IMAGE_MAX_MB, MIN_COVER_IMAGE_WIDTH, MIN_COVER_IMAGE_HEIGHT);
    assertThat(coverImageMeta.getImageSize()).isEqualTo(IMAGE_MAX_MB);
    assertThat(coverImageMeta.getImageWidth()).isEqualTo(MIN_COVER_IMAGE_WIDTH);
    assertThat(coverImageMeta.getImageHeight()).isEqualTo(MIN_COVER_IMAGE_HEIGHT);
  }

  @Test
  @DisplayName("이미지 사이즈 최대 사이즈를 넘긴 경우" +
      "exception 테스트")
  void coverImageDetailImage_exceed_max_cover_size_test() {
    int given = IMAGE_MAX_MB * 100;
    assertThatThrownBy(() -> new CoverImageMeta(IMAGE_MAX_MB * 100, MIN_COVER_IMAGE_WIDTH, MIN_COVER_IMAGE_HEIGHT))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(String.format(EXCEED_MAX_COVER_IMAGE_SIZE, given));
  }

  @Test
  @DisplayName("이미지 width 최소 사이즈를 못 넘긴 경우" +
      "exception 테스트")
  void coverImageDetailImage_invalid_image_width_test() {
    assertThatThrownBy(() -> new CoverImageMeta(IMAGE_MAX_MB, MIN_COVER_IMAGE_WIDTH - 10, MIN_COVER_IMAGE_HEIGHT))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(String.format(INVALID_COVER_IMAGE_WIDTH, MIN_COVER_IMAGE_WIDTH - 10));
  }

  @Test
  @DisplayName("이미지 height 최소 사이즈를 못 넘긴 경우" +
      "exception 테스트")
  void coverImageDetailImage_invalid_image_height_test() {
    assertThatThrownBy(() -> new CoverImageMeta(IMAGE_MAX_MB, MIN_COVER_IMAGE_WIDTH, MIN_COVER_IMAGE_HEIGHT - 10))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(String.format(INVALID_COVER_IMAGE_HEIGHT, MIN_COVER_IMAGE_HEIGHT - 10));
  }

  @Test
  @DisplayName("width와 height의 비율이 올바르지 않은 경우" +
      "exception 테스트")
  void coverImageDetailImage_invalid_image_ratio_test() {
    assertThatThrownBy(() -> new CoverImageMeta(IMAGE_MAX_MB, MIN_COVER_IMAGE_WIDTH, MIN_COVER_IMAGE_HEIGHT + 10))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining(String.format(INVALID_COVER_IMAGE_RATIO, MIN_COVER_IMAGE_WIDTH, MIN_COVER_IMAGE_HEIGHT + 10));
  }
}
